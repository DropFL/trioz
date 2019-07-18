package com.dropfl.platformer;

import com.dropfl.component.IDrawable;
import com.dropfl.key.Key;
import com.dropfl.key.KeyStatus;
import com.dropfl.platformer.entity.*;
import com.dropfl.util.Point;

import java.awt.Graphics2D;
import java.util.ArrayList;

public final class Engine implements IDrawable
{
    private double gravity = 1;
    private double speed = 1;
    private double time = 0;
    private boolean inputAvailable = true;

    // Entities
    private Player player;

    private ArrayList<Block> blocks;
    private ArrayList<PlayerInteractive> entities;
    private ArrayList<PlayerInteractive> removeEntities;

    public double gravity()
    {
        return gravity;
    }

    public void gravity(double gravity)
    {
        this.gravity = gravity;
    }

    public double speed()
    {
        return speed;
    }

    public void speed(double speed)
    {
        this.speed = speed;
    }

    public double time()
    {
        return time;
    }

    public void setInputAvailable(boolean inputAvailable)
    {
        this.inputAvailable = inputAvailable;
    }

    public Point playerOrigin()
    {
        return player.upperLeft();
    }

    public double playerWidth()
    {
        return player.width();
    }

    public double playerHeight()
    {
        return player.height();
    }

    public Point playerRightBelow()
    {
        return player.rightBelow();
    }

    public Point playerCenter()
    {
        return player.center();
    }

    public boolean isInputAvailable()
    {
        return inputAvailable;
    }

    public int getPlayerHp()
    {
        return player.getHp();
    }

    public int getPlayerSheilds()
    {
        return player.getShieldCount();
    }

    public ArrayList<Block> getBlocks()
    {
        return blocks;
    }

    public ArrayList<PlayerInteractive> getEntities()
    {
        return entities;
    }

    public Engine()
    {
        blocks = new ArrayList<>();
        entities = new ArrayList<>();
        removeEntities = new ArrayList<>();
        player = new Player();
    }

    public void tick()
    {
        if (isInputAvailable())
            handleInput();

        if (player.getShieldTime() > 0)
        {
            player.setShieldTime(player.getShieldTime() - 1);
        }
        else
        {
            player.shieldOff();
        }

        player.addX(speed * player.getSpeedX());
        player.addY(speed * player.getSpeedY());
        player.addSpeedY(speed * gravity);

        if (player.getHitDelay() > 0)
            player.setHitDelay(player.getHitDelay() - 1);

        for (PlayerInteractive e : blocks)
        {
            if (e.isCollided(player))
                e.interact(player);
        }
        for (PlayerInteractive e : entities)
        {
            if (e.isCollided(player) && e.interact(player))
                removeEntities.add(e);
        }

        entities.removeAll(removeEntities);
        removeEntities.clear();

        time += speed;
    }

    public void render(Graphics2D g)
    {
        for (PlayerInteractive entity : blocks)
            entity.render(g);

        for (PlayerInteractive entity : entities)
            entity.render(g);

        player.render(g);
    }

    private void handleInput()
    {
        if (KeyStatus.isKeyJustPressed(Key.SPACE))
        {
            if (player.getJumped() != 2)
            {
                player.setJumped(player.getJumped() + 1);
                player.setSpeedY(-Player.MAX_SPEED_Y);
                KeyStatus.setKeyProcessed(Key.SPACE);
            }
        }
        else if (!KeyStatus.isKeyPressed(Key.SPACE) && player.getSpeedY() < 0)
            player.setSpeedY(0);

        if (KeyStatus.isKeyPressed(Key.LEFT) ^ KeyStatus.isKeyPressed(Key.RIGHT))
            player.setSpeedX(KeyStatus.isKeyPressed(Key.LEFT) ? -8 : 8);
        else
            player.setSpeedX(0);

        if (KeyStatus.isKeyJustPressed(Key.S))
        {
            player.shieldOn();
            player.setShieldTime(30);
        }
    }
}
