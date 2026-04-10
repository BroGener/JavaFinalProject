package data.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    ID insert(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean deleteById(ID id) throws Exception;
    Optional<T> findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
}