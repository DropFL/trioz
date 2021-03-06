package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.ImageOverlayEffect;
import com.dropfl.music.DefaultMusicPlayer;
import com.dropfl.key.Key;
import com.dropfl.key.KeyStatus;
import res.ImageResource;
import res.SoundResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.VolatileImage;

public final class MainActivity extends Activity{
    
    private Image bgImage;
    private int prevBeat;
    private float alpha;
    private DefaultMusicPlayer bgm;
    private final JButton start_btn;
    // private final JButton option_btn;
    private final JButton exit_btn;
    private final MouseAdapter start_adapter;
    private final MouseAdapter exit_adapter;
    
    public MainActivity () {
        title = "Main Activity";
        bgm = new DefaultMusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
        bgImage = ImageResource.START_BACKGROUND.getImageIcon().getImage();
        alpha = 0;
        start_btn = new JButton(ImageResource.BLANK_BUTTON.getImageIcon(226, 68));
        // option_btn = new JButton(ImageResource.BLANK_BUTTON.getImageIcon(226, 68));
        exit_btn = new JButton(ImageResource.BLANK_BUTTON.getImageIcon(226, 68));
        
        start_adapter = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                super.mouseClicked(e);
                if(bgm.isPlaying())
                    requestActivityChange(PlatformerActivity.class);
            }
        };
        
        exit_adapter = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                super.mouseClicked(e);
                close();
                System.exit(0);
            }
        };
    }
    
    @Override
    public VolatileImage getScreen () {
        
        if(KeyStatus.isKeyJustPressed(Key.ESCAPE)) {
            KeyStatus.setKeyProcessed(Key.ESCAPE);
            close();
            System.exit(0);
        }
        
        updateImage();
        graphics.setRenderingHints(Main.getRenderingHint());
        
        int time = bgm.time();
        int curBeat = prevBeat;
        
        prevBeat = time % 1875;
        if(prevBeat < curBeat) alpha = 0.8f;
        
        graphics.drawImage(bgImage, 0, 0, null);
        
        new ImageOverlayEffect(0, 0, ImageResource.START_BACKGROUND_EMPHASIZE.getImageIcon().getImage(), alpha).apply(image);
        
        alpha /= 1.01f;
        
        return image;
    }
    
    @Override
    public void start () {
        bgm.play();
        createImage();
        
        addButton(start_btn, Main.SCREEN_WIDTH / 2 + 275, Main.SCREEN_HEIGHT / 2 - 65, 226, 68, start_adapter);
        addButton(exit_btn, Main.SCREEN_WIDTH / 2 + 275, Main.SCREEN_HEIGHT / 2 + 110, 226, 68, exit_adapter);
    }
    
    @Override
    public void close () {
        start_btn.removeMouseListener(start_adapter);
        exit_btn.removeMouseListener(exit_adapter);
        
        try {
            bgm.stop();
        } catch (IllegalStateException e) {
            // do nothing
        }
    }
}
