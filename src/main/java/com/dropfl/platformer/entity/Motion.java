package com.dropfl.platformer.entity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Motion {
    private ScriptEngine engine;
    private String formula;

    public Motion (String prerequisite, String formula) {
        this.formula = formula;
        
        this.engine = (new ScriptEngineManager()).getEngineByName("js");
        if (prerequisite != null)
            try { engine.eval(prerequisite); }
            catch (ScriptException e) { e.printStackTrace(); }
    }

    public double calculate (MotionBindings bind) {
        double result = 0;

        try { result = (double) engine.eval(formula, bind.getBindings()); }
        catch (ScriptException e) { e.printStackTrace(); }

        return result;
    }
}