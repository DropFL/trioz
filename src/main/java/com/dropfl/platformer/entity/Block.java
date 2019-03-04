package com.dropfl.platformer.entity;

import com.dropfl.util.Point;

import res.ImageResource;

import java.awt.*;

import static com.dropfl.platformer.collision.Collider.*;

public class Block extends PlayerInteractive {
    
    public Block (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
        this.collider = AABB;
        this.image = ImageResource.BLOCK_1.getImageIcon().getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    @Override
    public boolean interact (Player p) {
        double spX = p.getSpeedX(),
               spY = p.getSpeedY();
        Point  rb  = p.rightBelow();
        
        double left = p.x(), right  = rb.x(),
               top  = p.y(), bottom = rb.y();
        
        // If the player is moved only vertically or horizontal area of this block covers the player's one,
        // it must be top-to-bottom collision.
        if ((x < left && right < x + width) || spX == 0) {
            if (spY > 0) {
                p.y(y - bottom + top);
                p.setJumped(0);
            } else {
                p.y(y + height);
                p.setSpeedY(0);
            }

            return false;
        }
        
        // If the player is moved only horizontally or vertical area of this block covers the player's one,
        // it must be left-to-right collision.
        if ((y < top && bottom < y + height) || spY == 0) {
            if (spX > 0) p.x(x - right + left);
            else p.x(x + width);

            return false;
        }
        
        double deltaX = (spX > 0) ? (x - right + spX) : (x + width - left + spX),
               deltaY = (spY > 0) ? (y - bottom + spY) : (y + height - top + spY);
        
        if (deltaX / spX > deltaY / spY) {
            // left-to-right collision.
            if (spX > 0) p.x(x - right + left);
            else p.x(x + width);
        } else {
            // top-to-bottom collision.
            if (spY > 0) {
                p.y(y - bottom + top);
                p.setJumped(0);
            }
            else p.y(y + height);
            
            p.setSpeedY(0);
        }
        return false;
    }

}
