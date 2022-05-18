package com.github.selfcrafted.networketcd.data;

import io.etcd.jetcd.ByteSequence;

/**
 * The keys in etcd to store these values
 */
public class EtcdPaths {
    public static final String ROOT_PATH = "/newtworketcd/";
    public static final String ADDRESS_PATH = "address";
    public static final String SPOKEN_PROTOCOL_VERSION_PATH = "spoken_protocol_version";
    public static final String MINIMUM_PROTOCOL_VERSION_PATH = "minimum_protocol_version";
    public static final String ONLINE_PLAYER_COUNT_PATH = "online_player_count";
    public static final String MAXIMUM_PLAYER_COUNT_PATH = "maximum_player_count";
    public static final String ITEM_REPRESENTATION_PATH = "item_representation";

    public static ByteSequence getServerUuidFromPath(ByteSequence path) {
        var start = EtcdPaths.ROOT_PATH.length();
        return path.substring(start, start+36);
    }
}
