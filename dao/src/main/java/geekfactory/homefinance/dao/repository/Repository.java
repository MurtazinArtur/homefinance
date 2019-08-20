package geekfactory.homefinance.dao.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById (Long id);
    Collection<T> findAll();
    boolean remove (Long id);
    void save (T model);
    void update (T model,Long idRow);
}
