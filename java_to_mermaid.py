import os
import re
from dataclasses import dataclass, field
from typing import List, Optional, Dict, Set


JAVA_KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "record", "sealed",
    "permits", "non-sealed"
}

JAVA_BUILTIN_TYPES = {
    "byte", "short", "int", "long", "float", "double", "boolean", "char", "void",
    "Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character",
    "String", "Object", "Class", "Exception", "RuntimeException", "List", "ArrayList",
    "LinkedList", "Set", "HashSet", "Map", "HashMap", "TreeMap", "Collection",
    "Collections", "Optional", "Date", "LocalDate", "LocalDateTime", "BigDecimal",
    "HttpServlet", "HttpServletRequest", "HttpServletResponse", "ServletException",
    "Filter", "FilterChain", "ServletRequest", "ServletResponse"
}

MODIFIERS = {
    "public", "private", "protected", "static", "final", "abstract",
    "synchronized", "native", "transient", "volatile", "strictfp", "default"
}


@dataclass
class JavaField:
    name: str
    type_name: str
    modifiers: List[str] = field(default_factory=list)


@dataclass
class JavaMethod:
    name: str
    return_type: str
    parameters: List[str] = field(default_factory=list)
    modifiers: List[str] = field(default_factory=list)
    is_constructor: bool = False


@dataclass
class JavaType:
    name: str                  # full name if package exists
    simple_name: str
    package: str
    kind: str                  # class / interface / enum
    modifiers: List[str] = field(default_factory=list)
    extends: Optional[str] = None
    implements: List[str] = field(default_factory=list)
    fields: List[JavaField] = field(default_factory=list)
    methods: List[JavaMethod] = field(default_factory=list)
    references: Set[str] = field(default_factory=set)  # field/method refs


def remove_comments(code: str) -> str:
    code = re.sub(r'//.*', '', code)
    code = re.sub(r'/\*.*?\*/', '', code, flags=re.S)
    return code


def remove_strings(code: str) -> str:
    code = re.sub(r'"(?:\\.|[^"\\])*"', '""', code)
    code = re.sub(r"'(?:\\.|[^'\\])*'", "''", code)
    return code


def clean_code(code: str) -> str:
    return remove_strings(remove_comments(code))


def extract_package(code: str) -> str:
    m = re.search(r'\bpackage\s+([\w\.]+)\s*;', code)
    return m.group(1) if m else ""


def full_name(package: str, simple_name: str) -> str:
    return f"{package}.{simple_name}" if package else simple_name


def safe_mermaid_name(name: str) -> str:
    return name.replace(".", "_").replace("$", "_")


def visibility_symbol(modifiers: List[str]) -> str:
    if "public" in modifiers:
        return "+"
    if "private" in modifiers:
        return "-"
    if "protected" in modifiers:
        return "#"
    return "~"


def extract_type_names(type_str: str) -> Set[str]:
    """
    从类型字符串里提取可能的类名，例如:
    List<User> -> {"User"}
    Map<String, Station> -> {"Station"}
    Scooter[] -> {"Scooter"}
    """
    type_str = re.sub(r'@\w+(\([^)]*\))?\s*', '', type_str)
    tokens = re.findall(r'\b[A-Z][A-Za-z0-9_]*\b', type_str)
    return {t for t in tokens if t not in JAVA_BUILTIN_TYPES}


def split_params(param_str: str) -> List[str]:
    parts = []
    current = []
    depth = 0
    for ch in param_str:
        if ch == '<':
            depth += 1
        elif ch == '>':
            depth = max(0, depth - 1)
        elif ch == ',' and depth == 0:
            part = ''.join(current).strip()
            if part:
                parts.append(part)
            current = []
            continue
        current.append(ch)
    tail = ''.join(current).strip()
    if tail:
        parts.append(tail)
    return parts


def parse_param(param: str) -> Optional[tuple]:
    """
    把参数解析成 (name, type)
    例如:
    final List<User> users
    @Valid User user
    String... args
    """
    param = re.sub(r'@\w+(\([^)]*\))?\s*', '', param).strip()
    if not param:
        return None

    pieces = param.split()
    pieces = [p for p in pieces if p not in MODIFIERS]

    if len(pieces) < 2:
        return None

    name = pieces[-1]
    type_name = ' '.join(pieces[:-1]).replace("...", "...")
    return name, type_name


def find_matching_brace(text: str, start_index: int) -> int:
    depth = 0
    for i in range(start_index, len(text)):
        if text[i] == '{':
            depth += 1
        elif text[i] == '}':
            depth -= 1
            if depth == 0:
                return i
    return -1


def find_top_level_types(code: str) -> List[dict]:
    pattern = re.compile(
        r'((?:public|protected|private|abstract|static|final|strictfp)\s+)*'
        r'(class|interface|enum)\s+([A-Za-z_]\w*)'
        r'(?:\s+extends\s+([A-Za-z_][\w<>\.,\s]*))?'
        r'(?:\s+implements\s+([A-Za-z_][\w<>\.,\s]*))?'
        r'\s*\{',
        re.M
    )

    results = []
    for m in pattern.finditer(code):
        brace_open = code.find('{', m.start())
        brace_close = find_matching_brace(code, brace_open)
        if brace_close == -1:
            continue

        modifiers = m.group(1).split() if m.group(1) else []
        kind = m.group(2)
        name = m.group(3)
        extends = m.group(4).strip() if m.group(4) else None
        impls = []
        if m.group(5):
            impls = [x.strip() for x in m.group(5).split(',') if x.strip()]

        body = code[brace_open + 1: brace_close]
        results.append({
            "modifiers": modifiers,
            "kind": kind,
            "name": name,
            "extends": extends,
            "implements": impls,
            "body": body
        })
    return results


def extract_members_from_body(body: str, class_name: str) -> tuple[List[JavaField], List[JavaMethod], Set[str]]:
    fields: List[JavaField] = []
    methods: List[JavaMethod] = []
    references: Set[str] = set()

    lines = body.splitlines()
    brace_depth = 0
    buffer = ""

    for raw_line in lines:
        line = raw_line.strip()
        if not line:
            continue

        open_count = line.count('{')
        close_count = line.count('}')

        if brace_depth == 0:
            buffer += " " + line

            if line.endswith(";") or line.endswith("{"):
                candidate = buffer.strip()
                buffer = ""

                # method / constructor
                method_match = re.match(
                    r'((?:public|protected|private|static|final|abstract|synchronized|default)\s+)*'
                    r'([A-Za-z_<>\[\],\.\?\s]+?)?\s*'
                    r'([A-Za-z_]\w*)\s*'
                    r'\(([^()]*)\)\s*'
                    r'(?:throws\s+[^{]+)?'
                    r'\{?$',
                    candidate
                )

                if method_match and "(" in candidate and not candidate.startswith(("if ", "for ", "while ", "switch ", "catch ")):
                    modifiers = method_match.group(1).split() if method_match.group(1) else []
                    return_or_ctor_part = (method_match.group(2) or "").strip()
                    method_name = method_match.group(3).strip()
                    params_raw = method_match.group(4).strip()

                    params_display = []
                    if params_raw:
                        for p in split_params(params_raw):
                            parsed = parse_param(p)
                            if parsed:
                                param_name, param_type = parsed
                                params_display.append(f"{param_name}: {param_type}")
                                references.update(extract_type_names(param_type))

                    if method_name == class_name:
                        methods.append(JavaMethod(
                            name=method_name,
                            return_type="",
                            parameters=params_display,
                            modifiers=modifiers,
                            is_constructor=True
                        ))
                    else:
                        return_type = return_or_ctor_part or "void"
                        methods.append(JavaMethod(
                            name=method_name,
                            return_type=return_type,
                            parameters=params_display,
                            modifiers=modifiers,
                            is_constructor=False
                        ))
                        references.update(extract_type_names(return_type))

                    continue

                # field
                field_match = re.match(
                    r'((?:public|protected|private|static|final|transient|volatile)\s+)*'
                    r'([A-Za-z_<>\[\],\.\?\s]+?)\s+'
                    r'([A-Za-z_]\w*)'
                    r'(?:\s*=\s*.+)?;$',
                    candidate
                )

                if field_match and "(" not in candidate:
                    modifiers = field_match.group(1).split() if field_match.group(1) else []
                    type_name = field_match.group(2).strip()
                    field_name = field_match.group(3).strip()
                    fields.append(JavaField(
                        name=field_name,
                        type_name=type_name,
                        modifiers=modifiers
                    ))
                    references.update(extract_type_names(type_name))

        brace_depth += open_count - close_count

    return fields, methods, references


def parse_java_file(file_path: str) -> List[JavaType]:
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            raw = f.read()
    except Exception as e:
        print(f"[WARN] Failed to read {file_path}: {e}")
        return []

    code = clean_code(raw)
    package = extract_package(code)
    type_defs = find_top_level_types(code)

    results: List[JavaType] = []
    for t in type_defs:
        jt = JavaType(
            name=full_name(package, t["name"]),
            simple_name=t["name"],
            package=package,
            kind=t["kind"],
            modifiers=t["modifiers"],
            extends=t["extends"],
            implements=t["implements"]
        )

        fields, methods, refs = extract_members_from_body(t["body"], t["name"])
        jt.fields = fields
        jt.methods = methods
        jt.references = refs
        results.append(jt)

    return results


def collect_java_types(root_dir: str) -> List[JavaType]:
    all_types: List[JavaType] = []
    java_file_count = 0

    for base, _, files in os.walk(root_dir):
        for file in files:
            if file.endswith(".java"):
                java_file_count += 1
                path = os.path.join(base, file)
                print(f"[SCAN] {path}")
                parsed = parse_java_file(path)
                print(f"[FOUND] {len(parsed)} type(s) in {file}")
                all_types.extend(parsed)

    print(f"[INFO] Total .java files: {java_file_count}")
    print(f"[INFO] Total parsed types: {len(all_types)}")
    return all_types


def simplify_type_name(type_name: str) -> str:
    type_name = type_name.strip()
    type_name = re.sub(r'\s+', ' ', type_name)
    return type_name


def generate_mermaid(types: List[JavaType]) -> str:
    lines = ["classDiagram"]
    simple_to_full: Dict[str, str] = {t.simple_name: t.name for t in types}
    name_map: Dict[str, str] = {t.name: safe_mermaid_name(t.name) for t in types}

    # classes
    for t in types:
        mermaid_id = name_map[t.name]
        lines.append(f'    class {mermaid_id}["{t.simple_name}"] {{')

        if t.kind == "interface":
            lines.append("        <<interface>>")
        elif t.kind == "enum":
            lines.append("        <<enum>>")

        for field in t.fields:
            vis = visibility_symbol(field.modifiers)
            lines.append(f"        {vis}{field.name}: {simplify_type_name(field.type_name)}")

        for method in t.methods:
            vis = visibility_symbol(method.modifiers)
            param_str = ", ".join(method.parameters)
            if method.is_constructor:
                lines.append(f"        {vis}{method.name}({param_str})")
            else:
                lines.append(f"        {vis}{method.name}({param_str}): {simplify_type_name(method.return_type)}")

        lines.append("    }")

    # inheritance / implements
    for t in types:
        child_id = name_map[t.name]

        if t.extends:
            parent_simple = re.sub(r'<.*?>', '', t.extends).split('.')[-1].strip()
            if parent_simple in simple_to_full:
                parent_id = name_map[simple_to_full[parent_simple]]
                lines.append(f"    {parent_id} <|-- {child_id}")

        for iface in t.implements:
            iface_simple = re.sub(r'<.*?>', '', iface).split('.')[-1].strip()
            if iface_simple in simple_to_full:
                iface_id = name_map[simple_to_full[iface_simple]]
                lines.append(f"    {iface_id} <|.. {child_id}")

    # references by field / method signature
    added_relations = set()
    for t in types:
        from_id = name_map[t.name]
        for ref in t.references:
            if ref in simple_to_full and ref != t.simple_name:
                to_id = name_map[simple_to_full[ref]]
                relation = f"{from_id} --> {to_id}"
                if relation not in added_relations:
                    lines.append(f"    {relation} : uses")
                    added_relations.add(relation)

    return "\n".join(lines)


def main():
    project_root = r"D:/OneDrive - Algonquin College/26S/NetBean/JavaFinalProject/src/main/java"
    output_file = "class_diagram.mmd"

    types = collect_java_types(project_root)
    mermaid_text = generate_mermaid(types)

    with open(output_file, "w", encoding="utf-8") as f:
        f.write(mermaid_text)

    print(f"[OK] Mermaid class diagram written to: {output_file}")
    print("[PREVIEW]")
    print(mermaid_text[:2000])


if __name__ == "__main__":
    main()
