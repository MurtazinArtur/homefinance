package geekfactory.homefinance.service;

import java.util.Collection;
import java.util.Optional;

public interface ServiceCRUD<V, K> {
    Optional<V> findById(K id);

    Collection<V> findAll();

    boolean remove(K id);

    void save(V model);

    void update(V model, K idRow);
}
