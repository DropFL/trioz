package com.dropfl.platformer.entity;

import com.dropfl.platformer.collision.AxisAlignedBoundingBox;
import com.dropfl.platformer.collision.SquareToCircleCollider;
import res.ImageResource;

import java.awt.*;

public class Fireball extends PlayerInteractive implements AxisAlignedBoundingBox{

    public Fireball (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.width = 100;
        this.height = 100;
        this.collider = new SquareToCircleCollider();
        this.image = ImageResource.FIREBALL.getImageIcon().getImage().getScaledInstance(100,100, Image.SCALE_FAST);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }
}
