package ua.testing.repairagency.dao;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> {
    public PK getId();
}
