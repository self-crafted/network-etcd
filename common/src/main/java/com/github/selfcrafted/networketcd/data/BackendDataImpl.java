package com.github.selfcrafted.networketcd.data;

import java.net.InetAddress;
import java.util.UUID;

record BackendDataImpl(UUID uuid,
                       InetAddress address,
                       String itemRepresentation,
                       int spokenProtocolVersion,
                       int minimumProtocolVersion,
                       int onlinePlayerCount,
                       int maximumPlayerCount) implements BackendData { }
