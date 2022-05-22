package com.github.selfcrafted.networketcd.data;

import java.net.SocketAddress;
import java.util.UUID;

class BackendDataImpl implements BackendData {
    private final UUID uuid;
    private final SocketAddress address;
    private final int spokenProtocolVersion;

    private int minimumProtocolVersion = -1;
    private int onlinePlayerCount = -1;
    private int maximumPlayerCount = -1;
    private String itemRepresentation = null;

    public BackendDataImpl(UUID uuid, SocketAddress address, int spokenProtocolVersion) {
        this.uuid = uuid;
        this.address = address;
        this.spokenProtocolVersion = spokenProtocolVersion;
    }

    public BackendDataImpl(UUID uuid,
                           SocketAddress address,
                           int spokenProtocolVersion,
                           int minimumProtocolVersion,
                           int onlinePlayerCount,
                           int maximumPlayerCount,
                           String itemRepresentation) {
        this.uuid = uuid;
        this.address = address;
        this.spokenProtocolVersion = spokenProtocolVersion;
        this.minimumProtocolVersion = minimumProtocolVersion;
        this.onlinePlayerCount = onlinePlayerCount;
        this.maximumPlayerCount = maximumPlayerCount;
        this.itemRepresentation = itemRepresentation;
    }

    public void setMinimumProtocolVersion(int minimumProtocolVersion) {
        this.minimumProtocolVersion = minimumProtocolVersion;
    }

    public void setOnlinePlayerCount(int onlinePlayerCount) {
        this.onlinePlayerCount = onlinePlayerCount;
    }

    public void setMaximumPlayerCount(int maximumPlayerCount) {
        this.maximumPlayerCount = maximumPlayerCount;
    }

    public void setItemRepresentation(String itemRepresentation) {
        this.itemRepresentation = itemRepresentation;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public SocketAddress address() {
        return address;
    }

    @Override
    public int spokenProtocolVersion() {
        return spokenProtocolVersion;
    }

    @Override
    public int minimumProtocolVersion() {
        return minimumProtocolVersion;
    }

    @Override
    public int onlinePlayerCount() {
        return onlinePlayerCount;
    }

    @Override
    public int maximumPlayerCount() {
        return maximumPlayerCount;
    }

    @Override
    public String itemRepresentation() {
        return itemRepresentation;
    }
}
