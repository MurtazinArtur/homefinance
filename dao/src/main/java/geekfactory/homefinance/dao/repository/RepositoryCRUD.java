package geekfactory.homefinance.dao.repository;

import java.util.Collection;
import java.util.Optional;

public interface RepositoryCRUD<T, V> {
    Optional<T> findByName(String name);
    Optional<T> findById (V id);
    Collection<T> findAll();
    boolean remove (V id);
    void save (T model);
    void update (T model);
}