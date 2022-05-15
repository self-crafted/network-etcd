package com.github.selfcrafted.networketcd.backend.data;

import com.github.selfcrafted.networketcd.common.data.BaseData;

/**
 * Represents server information useful for backend servers only
 */
public interface ServerData extends BaseData {
    /**
     * A representation of the server as an item for inventory GUIs
     * @return The item in Json or SNBT format
     */
    String itemRepresentation();
}
