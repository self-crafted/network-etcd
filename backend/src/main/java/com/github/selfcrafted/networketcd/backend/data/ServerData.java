package com.github.selfcrafted.networketcd.backend.data;

import com.github.selfcrafted.networketcd.common.data.BackendData;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Represents server information useful for backend servers only
 */
public interface ServerData extends BackendData {
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

        public ServerData build() {
            return new ServerDataImpl(
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
