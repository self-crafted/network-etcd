package com.github.selfcrafted.networketcd.common.data;

import java.net.InetAddress;
import java.util.UUID;

/**
 * Represents server information useful for both backend servers and proxies
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
}
