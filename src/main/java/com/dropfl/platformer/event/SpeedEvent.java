package com.dropfl.platformer.event;

import com.dropfl.platformer.Engine;
import com.google.common.base.Preconditions;

import java.util.function.Function;

public class SpeedEvent extends TickEvent{
    
    private Engine engine;
    
    public SpeedEvent (int since, int duration, Function<Integer, Double[]> formula, Engine engine) {
        super(since, duration, formula);
        this.engine = engine;
    }
    
    @Override
    public void process (Double[] value) {
        Preconditions.checkArgument(value[0] >= 0);
        engine.speed(value[0]);
    }
    
    @Override
    public void finish () {
        engine.speed(1);
    }
}
