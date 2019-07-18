package com.dropfl.music;

import res.SoundResource;

/**
 * {@code MusicPlayer} manages tasks for playing a sound. only one
 * {@code SoundResource} can be assigned to a {@code MusicPlayer} and it cannot
 * be changed.
 * 
 * <p>
 * <i>considering about resource switcher which only works when the music is
 * stopped.</i>
 * </p>
 */
public abstract class MusicPlayer
{
    protected boolean isLoop;
    protected final SoundResource resource;
    protected Thread thread;
    protected Runnable onPlay;

    /**
     * initializes this {@code MusicPlayer} with given resource and attribute.
     * 
     * @param resource target {@code SoundResource} to play.
     * @param isLoop   when {@code true}, this player will loop the music forever.
     */
    protected MusicPlayer(SoundResource resource, boolean isLoop)
    {
        this.resource = resource;
        this.isLoop = isLoop;
    }

    /**
     * starts this {@code MusicPlayer} object. This method will create new thread
     * and play music there.
     */
    public abstract void play();

    /**
     * stops this {@code MusicPlayer} object. This method will cause the created
     * thread on {@code play()} to terminate.
     */
    public abstract void stop();

    /**
     * @return current playing position of the music in milliseconds. If it was
     *         never played, this function will return 0.
     */
    public abstract int time();

    /**
     * @return {@code true} when this {@code MusicPlayer} is now playing.
     *         {@code false} otherwise.
     */
    public abstract boolean isPlaying();

    /**
     * @return total length of target music on this object, in milliseconds.
     */
    public int length()
    {
        return resource.length();
    }

    /**
     * @return {@code true} when this {@code MusicPlayer} will loop. {@code false}
     *         otherwise.
     */
    public boolean loop()
    {
        return isLoop;
    }

    /**
     * @return {@code SoundResource} that represents target music of this object.
     */
    public SoundResource resource()
    {
        return resource;
    }

    /**
     * sets loop state of this {@code MusicPlayer} to the given value.
     * 
     * @param isLoop if it is {@code true}, this object will loop the music.
     */
    public void loop(boolean isLoop)
    {
        this.isLoop = isLoop;
    }
}
