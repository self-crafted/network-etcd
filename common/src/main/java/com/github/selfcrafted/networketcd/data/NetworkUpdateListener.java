package com.github.selfcrafted.networketcd.data;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.WatchOption;
import io.etcd.jetcd.watch.WatchEvent;

import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class NetworkUpdateListener {
    private final Client ETCD;

    private Thread thread;

    private OnNewServer onNewServer = new OnNewServer() {};
    private OnServerDelete onServerDelete = new OnServerDelete() {};
    private OnPlayerCountUpdate onOnlinePlayerCountUpdate = new OnPlayerCountUpdate() {};
    private OnPlayerCountUpdate onMaxPlayerCountUpdate = new OnPlayerCountUpdate() {};
    private OnItemUpdate onItemRepresentationUpdate = new OnItemUpdate() {};

    public NetworkUpdateListener(List<URI> etcdEndpoints) {
        ETCD = Client.builder().endpoints(etcdEndpoints).build();
    }

    /**
     * Perform initial read from etcd and fire callback for each existing server
     */
    public void start() {
        this.thread = new Thread(() -> {
            // TODO: 16.05.22 watch on every "mutable" key and call the callbacks on changes
            var watch = ETCD.getWatchClient().watch(toByteSequence(EtcdPaths.ROOT_PATH),
                            WatchOption.newBuilder().isPrefix(true).withPrevKV(true).build(), watchResponse -> {
                        var serversToDelete = new HashSet<UUID>();
                        var serversToUpdateOnlinePlayers = new HashMap<UUID, Integer>();
                        var serversToUpdateMaximumPlayers = new HashMap<UUID, Integer>();
                        var serversToUpdateItemRepresentation = new HashMap<UUID, String>();
                        var serversToCreate = new HashMap<UUID, HashMap<String, String>>();
                        var events = watchResponse.getEvents();
                        for (WatchEvent event : events) {
                            switch (event.getEventType()) {
                                case PUT -> {
                                    // TODO: 18.05.22 find out if server got created or was updated
                                    var key = event.getPrevKV().getKey();
                                    var serverId = EtcdPaths.getServerUuidFromPath(key);
                                    serversToCreate.computeIfAbsent(UUID.fromString(serverId.toString(Charset.defaultCharset())), uuid -> new HashMap<>());
                                    var data = serversToCreate.get(UUID.fromString(serverId.toString(Charset.defaultCharset())));
                                    data.put(key.toString(Charset.defaultCharset()),
                                            event.getPrevKV().getValue().toString(Charset.defaultCharset()));
                                }
                                case DELETE -> {
                                    var serverId = EtcdPaths.getServerUuidFromPath(event.getPrevKV().getKey());
                                    serversToDelete.add(UUID.fromString(serverId.toString(Charset.defaultCharset())));
                                }
                                case UNRECOGNIZED -> {}
                            }
                        }
                        serversToDelete.forEach(serverId ->
                                onServerDelete.onServerDelete(serverId));
                        serversToUpdateOnlinePlayers.forEach((uuid, integer) ->
                                onOnlinePlayerCountUpdate.onUpdate(uuid, integer));
                        serversToUpdateMaximumPlayers.forEach((uuid, integer) ->
                                onMaxPlayerCountUpdate.onUpdate(uuid, integer));
                        serversToUpdateItemRepresentation.forEach((uuid, string) ->
                                onItemRepresentationUpdate.onUpdate(uuid, string));
                        serversToCreate.forEach((key, value) -> {
                            try {
                                onNewServer.onNewServer(BackendData.fromMap(key, value));
                            } catch (UnknownHostException e) {
                                System.err.println(e);
                            }
                        });
                    });

            // TODO: 16.05.22 discover every server form etcd
            var servers = ETCD.getKVClient()
                    .get(toByteSequence(EtcdPaths.ROOT_PATH), GetOption.newBuilder().isPrefix(true).build());

            // TODO: 16.05.22 get data for every server
        });
        thread.start();
    }

    public void stop() throws InterruptedException {
        this.thread.interrupt();
        thread.join();
    }

    /**
     * Set a callback to be executed when a new server is added to the network
     * @param onNewServer The callback to set
     */
    public void setOnNewServer(OnNewServer onNewServer) {
        this.onNewServer = onNewServer;
    }

    /**
     * Set a callback to be executed when a server updates its online player count
     * @param onOnlinePlayerCountUpdate The callback to set
     */
    public void setOnOnlinePlayerCountUpdate(OnPlayerCountUpdate onOnlinePlayerCountUpdate) {
        this.onOnlinePlayerCountUpdate = onOnlinePlayerCountUpdate;
    }

    /**
     * Set a callback to be executed when a server updates its max player count
     * @param onMaxPlayerCountUpdate The callback to set
     */
    public void setOnMaxPlayerCountUpdate(OnPlayerCountUpdate onMaxPlayerCountUpdate) {
        this.onMaxPlayerCountUpdate = onMaxPlayerCountUpdate;
    }

    /**
     * Set a callback to be executed when a server updates its item representation
     * @param onItemRepresentationUpdate The callback to set
     */
    public void setOnItemRepresentationUpdate(OnItemUpdate onItemRepresentationUpdate) {
        this.onItemRepresentationUpdate = onItemRepresentationUpdate;
    }

    /**
     * Set a callback to be executed when a server gets deleted
     * @param onServerDelete The callback to set
     */
    public void setOnServerDelete(OnServerDelete onServerDelete) {
        this.onServerDelete = onServerDelete;
    }

    private ByteSequence toByteSequence(String string) { return ByteSequence.from(string, Charset.defaultCharset()); }

    public interface OnNewServer { default void onNewServer(BackendData data) {} }
    public interface OnServerDelete { default void onServerDelete(UUID serverID) {} }
    public interface OnPlayerCountUpdate { default void onUpdate(UUID serverId, int PlayerCount) {} }
    public interface OnItemUpdate { default void onUpdate(UUID serverId, String itemRepresentation) {} }
}
