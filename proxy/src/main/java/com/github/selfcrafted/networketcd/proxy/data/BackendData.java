package com.github.selfcrafted.networketcd.proxy.data;

import com.github.selfcrafted.networketcd.common.data.BaseData;

import java.net.InetAddress;

/**
 * Represents server information useful for proxies only
 */
public interface BackendData extends BaseData {
    /**
     * The address on which the backend server listens
     * @return An InetAddress containing the address
     */
    InetAddress address();
}
