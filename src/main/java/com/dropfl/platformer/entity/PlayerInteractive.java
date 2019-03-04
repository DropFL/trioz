package com.dropfl.platformer.entity;

import com.dropfl.platformer.collision.CollisionType;

public abstract class PlayerInteractive extends Entity {
    protected CollisionType collider;
    
    public abstract boolean interact (Player player);
    
    public boolean isCollided (Player player) {
        return collider.isCollided(this, player);
    }
}
