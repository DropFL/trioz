package com.dropfl.motion;

import org.mariuszgromada.math.mxparser.Expression;

public class Motion {

    private Expression exp;

    public Motion (String[] prerequisite, String formula) {
        this.exp = new Expression(formula);
    }

    public double calculate (MotionBindings bind) {
        double result = 0;

        // TODO evaluate expression with bindings to calculate

        return result;
    }
}