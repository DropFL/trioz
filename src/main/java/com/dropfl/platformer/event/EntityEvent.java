package com.dropfl.platformer.event;

import com.dropfl.platformer.entity.PlayerInteractive;

import java.util.ArrayList;
import java.util.function.Function;

public class EntityEvent extends TickEvent {
    
    private PlayerInteractive entity;
    private ArrayList<PlayerInteractive> list;
    
    public EntityEvent (int since, int duration, Function<Integer, Double[]> formula,
                        ArrayList<PlayerInteractive> list, PlayerInteractive entity) {
        super(since, duration, formula);
        this.entity = entity;
        this.list = list;
    }
    
    @Override
    public void process (Double[] value) {
        entity.x(value[0]);
        entity.y(value[1]);
        entity.rotation(Math.toRadians(value[2]));
    }

    @Override
    public void start () {
        list.add(entity);
    }
    
    @Override
    public void finish () {
        if(list.contains(entity))
            list.remove(entity);
    }
}
