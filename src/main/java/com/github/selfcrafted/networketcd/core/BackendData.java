package com.github.selfcrafted.networketcd.core;

import net.kyori.adventure.text.Component;

import java.net.SocketAddress;
import java.util.UUID;

/**
 * Represents server information used by both backend servers and proxies
 */
public interface BackendData {
    /**
     * The identifier of the backend server used by proxy and backend servers to refer to a specific server
     * @return The UUID of the server
     */
    UUID uuid();

    /**
     * The protocol version the server uses, so a proxy capable of translating can do so
     * @return The protocol version
     */
    int spokenProtocolVersion();

    /**
     * The minimum protocol version the proxy shall allow clients to use to connect to this server
     * @return The protocol version
     */
    int minimumProtocolVersion();

    /**
     * The number of players on this server
     * @return The number of players
     */
    int onlinePlayerCount();

    /**
     * The maximum number the proxy shall attempt to send to this server
     * @return The number of players
     */
    int maximumPlayerCount();

    /**
     * The address on which the backend server listens
     *
     * @return An InetAddress containing the address
     */
    SocketAddress address();

    /**
     * The name of the server to be displayed for players
     * @return The name as Component
     */
    Component displayName();

    /**
     * The item the server is displayed with in menus
     * @return The icon as string (probably namespace id) and amount
     */
    MenuIcon menuIcon();

    class Builder {
        private final UUID uuid;
        private SocketAddress address;
        private int spokenProtocolVersion;
        private int minimumProtocolVersion = -1;
        private int onlinePlayerCount = -1;
        private int maximumPlayerCount = -1;
        private Component displayName = null;
        private MenuIcon menuIcon = null;

        public Builder(SocketAddress address, int spokenProtocolVersion) {
            this.uuid = UUID.randomUUID();
            this.address = address;
            this.spokenProtocolVersion = spokenProtocolVersion;
        }

        public Builder address(SocketAddress address) {
            this.address = address;
            return this;
        }

        public Builder spokenProtocolVersion(int spokenProtocolVersion) {
            this.spokenProtocolVersion = spokenProtocolVersion;
            return this;
        }

        public Builder minimumProtocolVersion(int minimumProtocolVersion) {
            this.minimumProtocolVersion = minimumProtocolVersion;
            return this;
        }

        public Builder onlinePlayerCount(int onlinePlayerCount) {
            this.onlinePlayerCount = onlinePlayerCount;
            return this;
        }

        public Builder maximumPlayerCount(int maximumPlayerCount) {
            this.maximumPlayerCount = maximumPlayerCount;
            return this;
        }

        public Builder serverName(Component displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder menuIcon(MenuIcon menuIcon) {
            this.menuIcon = menuIcon;
            return this;
        }

        public BackendData build() {
            return new BackendDataImpl(
                    uuid,
                    address,
                    spokenProtocolVersion,
                    minimumProtocolVersion < 0 ? spokenProtocolVersion : minimumProtocolVersion,
                    onlinePlayerCount,
                    maximumPlayerCount,
                    displayName,
                    menuIcon);
        }
    }
}
