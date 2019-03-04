package com.dropfl.platformer.entity;

import res.ImageResource;

import java.awt.*;

import static com.dropfl.platformer.collision.CollisionType.*;

public class Bullet extends PlayerInteractive {

    public Bullet (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.width = width;
        this.height = height;
        this.collider = OBB;
        this.image = ImageResource.FIRE.getImageIcon().getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }
}
