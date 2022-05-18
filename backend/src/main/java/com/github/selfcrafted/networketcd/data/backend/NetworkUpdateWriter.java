package com.github.selfcrafted.networketcd.data.backend;

import com.github.selfcrafted.networketcd.data.BackendData;
import com.github.selfcrafted.networketcd.data.EtcdPaths;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.options.DeleteOption;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

public class NetworkUpdateWriter {
    private final Client ETCD;
    private final BackendData backendData;

    public NetworkUpdateWriter(List<URI> etcdEndpoints, BackendData backendData) {
        ETCD = Client.builder().endpoints(etcdEndpoints).build();
        this.backendData = backendData;
    }

    public void publish() {
        // TODO: 18.05.22 do it in transaction
        // ETCD.getKVClient().txn().If(new Cmp(ByteSequence.from(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString()).toString(), Charset.defaultCharset()), Cmp.Op.NOT_EQUAL, C)).Then().commit();
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.ADDRESS_PATH).toString(),
                backendData.address());
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.SPOKEN_PROTOCOL_VERSION_PATH).toString(),
                backendData.spokenProtocolVersion());
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.MINIMUM_PROTOCOL_VERSION_PATH).toString(),
                backendData.minimumProtocolVersion());
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.ONLINE_PLAYER_COUNT_PATH).toString(),
                backendData.onlinePlayerCount());
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.MAXIMUM_PLAYER_COUNT_PATH).toString(),
                backendData.maximumPlayerCount());
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.ITEM_REPRESENTATION_PATH).toString(),
                backendData.itemRepresentation());
    }

    public void delete() {
        ETCD.getKVClient().delete(
                ByteSequence.from(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString()).toString(),
                        Charset.defaultCharset()),
                DeleteOption.newBuilder().isPrefix(true).build());
    }

    public void updateItemRepresentation(String itemRepresentation) {
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.ITEM_REPRESENTATION_PATH).toString(),
                itemRepresentation);
    }

    public void updateMaximumPlayerCount(String maximumPlayerCount) {
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.MAXIMUM_PLAYER_COUNT_PATH).toString(),
                maximumPlayerCount);
    }

    public void updateOnlinePlayerCount(String onlinePlayerCount) {
        put(Path.of(EtcdPaths.ROOT_PATH, backendData.uuid().toString(), EtcdPaths.ONLINE_PLAYER_COUNT_PATH).toString(),
                onlinePlayerCount);
    }

    private void put(String key, Object value) {
        ETCD.getKVClient().put(ByteSequence.from(key, Charset.defaultCharset()),
                ByteSequence.from(value.toString(), Charset.defaultCharset()));
    }
}
