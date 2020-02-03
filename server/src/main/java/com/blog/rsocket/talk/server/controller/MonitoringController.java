package com.blog.rsocket.talk.server.controller;

import lombok.AllArgsConstructor;

public class MonitoringController {

    @AllArgsConstructor
    public static class AppStatus {
        private final int bufferSize;
        private final int bufferCount;
        private final int bufferDiscarded;
    }
}
