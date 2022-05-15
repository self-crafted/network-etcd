package com.github.selfcrafted.networketcd.backend.data;

import java.net.InetAddress;
import java.util.UUID;

record ServerDataImpl(UUID uuid,
                      InetAddress address,
                      String itemRepresentation,
                      int spokenProtocolVersion,
                      int minimumProtocolVersion,
                      int onlinePlayerCount,
                      int maximumPlayerCount) implements ServerData { }
