package com.dropfl.platformer.entity;

import java.io.File;

public class EntityFactory {
    private EntityFactory () {}

    public static ImprovedEntity getEntity (File file) {
        ImprovedEntity entity = new ImprovedEntity();

        //TODO implement factory based on "entity.ts"

        return entity;
    }
}