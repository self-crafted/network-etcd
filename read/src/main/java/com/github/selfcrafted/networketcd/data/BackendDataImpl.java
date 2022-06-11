package com.github.selfcrafted.networketcd.data;

import net.kyori.adventure.text.Component;

import java.net.SocketAddress;
import java.util.UUID;

class BackendDataImpl implements BackendData {
    private final UUID uuid;
    private final SocketAddress address;
    private final int spokenProtocolVersion;

    private int minimumProtocolVersion = -1;
    private int onlinePlayerCount = -1;
    private int maximumPlayerCount = -1;
    private Component displayName = null;
    private MenuIcon menuIcon = null;

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
                           Component displayName,
                           MenuIcon menuIcon) {
        this.uuid = uuid;
        this.address = address;
        this.spokenProtocolVersion = spokenProtocolVersion;
        this.minimumProtocolVersion = minimumProtocolVersion;
        this.onlinePlayerCount = onlinePlayerCount;
        this.maximumPlayerCount = maximumPlayerCount;
        this.displayName = displayName;
        this.menuIcon = menuIcon;
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

    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
    }

    public void setMenuIcon(MenuIcon menuIcon) {
        this.menuIcon = menuIcon;
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
    public Component displayName() {
        return displayName;
    }

    @Override
    public MenuIcon menuIcon() {
        return menuIcon;
    }
}
