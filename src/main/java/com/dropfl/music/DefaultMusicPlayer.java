package com.dropfl.music;

import com.google.common.base.Preconditions;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import res.SoundResource;

/**
 * {@code DefaultMusicPlayer} is a simple, unpausable version of
 * {@code MusicPlayer}. JLayer's {@code Player} is used to implement this class.
 */
public class DefaultMusicPlayer extends MusicPlayer
{
    private Player player;
    private boolean isPlaying;

    public DefaultMusicPlayer(SoundResource resource, boolean isLoop)
    {
        super(resource, isLoop);

        this.player = null;
        this.onPlay = () -> {
            try
            {
                do
                {
                    player = resource.createPlayer();
                    isPlaying = true;
                    player.play();
                } while (isPlaying && this.isLoop);
            }
            catch (JavaLayerException e)
            {
                // do nothing
            }
            finally
            {
                isPlaying = false;
            }
        };
        this.isPlaying = false;
    }

    public DefaultMusicPlayer(SoundResource resource)
    {
        this(resource, false);
    }

    @Override
    public int time()
    {
        if (isPlaying)
            return player.getPosition();
        else
            return 0;
    }

    @Override
    public boolean isPlaying()
    {
        return isPlaying;
    }

    @Override
    public void play()
    {
        Preconditions.checkState(!isPlaying);

        thread = new Thread(onPlay);
        thread.start();
    }

    @Override
    public void stop()
    {
        Preconditions.checkState(isPlaying);    

        isPlaying = false;
        player.close();
    }
}