package com.dropfl.motion;

import com.dropfl.platformer.*;
import com.dropfl.music.*;

/**
 * {@code MotionFactor} defines parameters for {@code Motion}.
 * 
 * @see #X
 * @see #Y
 * @see #PLAYER_X
 * @see #PLAYER_Y
 * @see #ENGINE_TIME
 * @see #REAL_TIME
 */
public enum MotionFactor {
    /**
     * {@code X} represents a x-value of a target entity.
     */
    X           ("x"),
    /**
     * {@code Y} represents a y-value of a target entity.
     */
    Y           ("y"),
    /**
     * {@code PLAYER_X} represents a x-value of the player.
     */
    PLAYER_X    ("px"),
    /**
     * {@code PLAYER_Y} represents a y-value of the player.
     */
    PLAYER_Y    ("py"),
    /**
     * {@code ENGINE_TIME} represents a virtual time elapsed in engine.
     * This factor's increment depends on {@link Engine#speed}.
     * For example, if {@code speed} is 0.2 constantly and (real) time passed 5 seconds,
     * {@code ENGINE_TIME} is increased by 1000(ms), not 5000.
     * 
     * @see Engine
     */
    ENGINE_TIME ("t"),
    /**
     * {@code REAL_TIME} represents the real time elapsed.
     * Actually, value of this factor is the position of current {@link MusicPlayer}.
     * Thus, when the game is paused, the {@code MusicPlayer} is also paused,
     * So {@code REAL_TIME} does not change until resume.
     * 
     * @see MusicPlayer#time()
     */
    REAL_TIME   ("rt");

    private final String key;

    MotionFactor (String key) {
        this.key = key;
    }

    /**
     * @return A string corresponds to the element. Every {@code key} is unique.
     */
    public String key () { return key; }
}