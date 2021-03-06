package com.dropfl.platformer;

import com.dropfl.effect.ScreenEffect;
import com.dropfl.effect.ScreenEffectIterator;
import com.dropfl.music.MusicPlayer;
import com.dropfl.key.Key;
import com.dropfl.key.KeyStatus;
import com.dropfl.platformer.event.EventManager;

public class Synchronizer {
    
    private Engine engine;
    private MusicPlayer player;
    private ScreenEffectIterator effects;
    private EventManager eventManager;
    private int ticks;
    
    public Synchronizer (Engine engine, MusicPlayer player, ScreenEffectIterator effects) {
        this.engine = engine;
        this.player = player;
        this.effects = effects;

        eventManager = new EventManager(engine, effects);
        ticks = 0;
    }
    
    public void update () {
        while(ticks * 50 < player.time() * 3) {
            ticks ++;
            
            eventManager.update(ticks);
            engine.tick();
            ScreenEffect.setSeed(ticks);
        }
        
        
        if(KeyStatus.isKeyJustPressed(Key.DOWN)) {
            KeyStatus.setKeyProcessed(Key.DOWN);
            // something
        }
    }
    
    public int getTicks () {
        return ticks;
    }
    public Engine getEngine () {
        return engine;
    }
    public ScreenEffectIterator getEffects () {
        return effects;
    }
    
}

