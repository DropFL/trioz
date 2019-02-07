package com.dropfl.platformer.entity;

public enum MotionFactor {
    X           ("x"),
    Y           ("y"),
    PLAYER_X    ("px"),
    PLAYER_Y    ("px"),
    ENGINE_TIME ("t"),
    REAL_TIME   ("rt");

    private final String key;

    MotionFactor (String key) {
        this.key = key;
    }

    public String key () { return key; }
}