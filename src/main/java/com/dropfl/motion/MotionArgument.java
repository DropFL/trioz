package com.dropfl.motion;

import java.util.HashMap;

public class MotionArgument {
    private HashMap<MotionFactor, Double> map;

    public MotionArgument () {
        map = new HashMap<>();
    }

    public double get (MotionFactor factor) {
        return map.get(factor);
    }
    
    public void set (MotionFactor factor, double value) {
        map.put(factor, value);
    }
}