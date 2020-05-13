package ua.testing.repairagency.dao;

import ua.testing.repairagency.exception.PersistException;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    Optional<T> get(long id) throws PersistException;

    List<T> getAll() throws PersistException;

    void create(T object) throws PersistException;

    void update(T object) throws PersistException;

    void delete(T object) throws PersistException;


}
