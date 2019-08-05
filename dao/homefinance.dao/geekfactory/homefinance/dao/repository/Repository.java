package geekfactory.homefinance.dao.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById (long id);
    Collection<T> findAll();
    boolean remove (long id);
    void save (T model);
    void update (T model,long idRow);
}
