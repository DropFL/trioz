package com.dropfl.motion;

/**
 * {@code MotionFactor} defines parameters for {@code Motion}.
 * for more detailed description of each element, refer to its description.
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
     * This factor's increment depends on {@code Engine.speed}.
     * For example, if {@code speed} is 0.2 constantly and (real) time passed 5 seconds, {@code ENGINE_TIME} is increased by 1000(ms).
     */
    ENGINE_TIME ("t"),
    /**
     * {@code REAL_TIME} represents the real time elapsed.
     * actually, value of this factor is the position of current {@code MusicPlayer}.
     * thus, when the game is paused, the {@code MusicPlayer} is also paused, so {@code REAL_TIME} does not change until resume.
     */
    REAL_TIME   ("rt");

    private final String key;

    MotionFactor (String key) {
        this.key = key;
    }

    /**
     * @return a string corresponds to the element. every {@code key} is unique.
     */
    public String key () { return key; }
}