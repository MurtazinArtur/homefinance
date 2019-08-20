package geekfactory.homefinance.service;

import java.util.Collection;
import java.util.Optional;

public interface Service<V> {
    Optional<V> findById(long id);

    Collection<V> findAll();

    boolean remove(long id);

    void save(V model);

    void update(V model, long idRow);
}
