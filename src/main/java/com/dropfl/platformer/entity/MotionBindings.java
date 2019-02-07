package com.dropfl.platformer.entity;

import javax.script.Bindings;
import javax.script.SimpleBindings;

public class MotionBindings {
    private Bindings bind;

    public MotionBindings () {
        bind = new SimpleBindings();
        for (MotionFactor factor : MotionFactor.values())
            bind.put(factor.key(), 0);
    }

    public double get (MotionFactor factor) {
        return (double) bind.get(factor.key());
    }
    
    public void set (MotionFactor factor, double value) {
        bind.put(factor.key(), value);
    }

    Bindings getBindings () {
        return bind;
    }
}