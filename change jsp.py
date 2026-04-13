from pathlib import Path

# 这里改成你的项目 Web Pages 根目录
ROOT_DIR = Path(r"D:\OneDrive - Algonquin College\26S\NetBean\JavaFinalProject\src\main\webapp")

OLD_NAV = """<nav>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/register">Register</a>
    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/profile">Profile</a>
    <a href="${pageContext.request.contextPath}/scooters">Scooters</a>
    <a href="${pageContext.request.contextPath}/stations">Stations</a>
    <a href="${pageContext.request.contextPath}/maintenance">Maintenance</a>
    <a href="${pageContext.request.contextPath}/reports">Reports</a>
    <a href="${pageContext.request.contextPath}/monthly-statement">Monthly Statement</a>
    <a href="${pageContext.request.contextPath}/tracking">Tracking</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
</nav>"""

NEW_NAV = '<%@ include file="/common/navbar.jsp" %>'

def main():
    replaced_files = []
    skipped_files = []

    for jsp_file in ROOT_DIR.rglob("*.jsp"):
        try:
            content = jsp_file.read_text(encoding="utf-8")
        except UnicodeDecodeError:
            # 如果不是 utf-8，可以改成你项目实际编码，比如 gbk
            print(f"[跳过-编码无法读取] {jsp_file}")
            skipped_files.append(str(jsp_file))
            continue

        if OLD_NAV in content:
            new_content = content.replace(OLD_NAV, NEW_NAV)
            jsp_file.write_text(new_content, encoding="utf-8")
            replaced_files.append(str(jsp_file))
            print(f"[已替换] {jsp_file}")
        else:
            print(f"[未匹配] {jsp_file}")

    print("\n====== 处理完成 ======")
    print(f"成功替换文件数: {len(replaced_files)}")
    print(f"跳过文件数: {len(skipped_files)}")

    if replaced_files:
        print("\n已替换文件:")
        for f in replaced_files:
            print(f" - {f}")

if __name__ == "__main__":
    main()
