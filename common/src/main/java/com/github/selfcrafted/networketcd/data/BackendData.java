package com.github.selfcrafted.networketcd.data;

import java.net.InetAddress;
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
     * @return An InetAddress containing the address
     */
    InetAddress address();

    /**
     * A representation of the server as an item for inventory GUIs
     * @return The item in Json or SNBT format
     */
    String itemRepresentation();

    class Builder {
        private final UUID uuid;
        private InetAddress address;
        private int spokenProtocolVersion;
        private int minimumProtocolVersion = -1;
        private int onlinePlayerCount = -1;
        private int maximumPlayerCount = -1;
        private String itemRepresentation = null;

        public Builder(InetAddress address, int spokenProtocolVersion) {
            this.uuid = UUID.randomUUID();
            this.address = address;
            this.spokenProtocolVersion = spokenProtocolVersion;
        }

        public Builder address(InetAddress address) {
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

        public Builder itemRepresentation(String itemRepresentation) {
            this.itemRepresentation = itemRepresentation;
            return this;
        }

        public BackendData build() {
            return new BackendDataImpl(
                    uuid,
                    address,
                    itemRepresentation,
                    spokenProtocolVersion,
                    minimumProtocolVersion < 0 ? spokenProtocolVersion : minimumProtocolVersion,
                    onlinePlayerCount,
                    maximumPlayerCount);
        }
    }
}
