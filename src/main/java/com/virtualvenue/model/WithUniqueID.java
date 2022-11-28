package com.virtualvenue.model;

/**
 * Model part that has an unique ID with witch it is identified in the model graph
 */
public interface WithUniqueID<V> {
    /**
     * Globally Unique OBJECT ID
     *
     * @return OBJECT ID
     */
    String getId();

    /**
     * Globally Unique OBJECT ID
     *
     * @param id OBJECT ID
     */
    V setId(String id);
}
