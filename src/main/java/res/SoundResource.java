package res;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public enum SoundResource
{
    THE_GHOST("NIVIRO_The_Ghost.mp3"),
    THE_FLOOR_IS_LAVA("NIVIRO_The_Floor_Is_Lava.mp3"),
    LOOP_TEST("Loop_Test.mp3");

    private File file;
    private int length;
    private float msPerFrame;

    /**
     * initializes {@code SoundResource} with given file.
     * 
     * @param name name of a music file. The file must be located in
     *                 {@code res/sounds}.
     */
    SoundResource(String name)
    {
        file = new File(getClass().getResource("sounds/" + name).getPath());

        try
        {
            FileInputStream fis = new FileInputStream(file);
            Bitstream bitstream = new Bitstream(fis);
            Header h = bitstream.readFrame();

            length = (int) h.total_ms((int) fis.getChannel().size());
            msPerFrame = h.ms_per_frame();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            length = -1;
        }
    }

    /**
     * creates {@code Player} that plays this music.
     * 
     * @return created {@code Player} object.
     */
    public Player createPlayer()
    {
        try
        {
            return new Player(new BufferedInputStream(new FileInputStream(file)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * creates {@code AdvancedPlayer} that plays this music to given {@code device}.
     * 
     * @return created {@code AdvancedPlayer} object.
     */
    public AdvancedPlayer createAdvancedPlayer(AudioDevice device)
    {
        try
        {
            return new AdvancedPlayer(new BufferedInputStream(new FileInputStream(file)), device);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return total length of this music, in milliseconds.
     */
    public int length()
    {
        return length;
    }

    /**
     * @return milliseconds per frame of this music file.
     */
    public float msPerFrame()
    {
        return msPerFrame;
    }
}
