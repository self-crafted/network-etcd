package com.github.selfcrafted.networketcd.backend.data;

import com.github.selfcrafted.networketcd.common.data.BackendData;

/**
 * Represents server information useful for backend servers only
 */
public interface ServerData extends BackendData {
    /**
     * A representation of the server as an item for inventory GUIs
     * @return The item in Json or SNBT format
     */
    String itemRepresentation();
}
