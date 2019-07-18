package com.dropfl.key;

import static java.awt.event.KeyEvent.*;

public enum Key
{
    LEFT(VK_LEFT),
    RIGHT(VK_RIGHT),
    UP(VK_UP),
    DOWN(VK_DOWN),
    SPACE(VK_SPACE),
    S(VK_S),
    ESCAPE(VK_ESCAPE);

    private final int keyCode;

    Key(int keyCode)
    {
        this.keyCode = keyCode;
    }

    public static Key getKey(int keyCode)
    {
        for (Key key : Key.values())
            if (key.keyCode == keyCode)
                return key;

        return null;
    }

    public int getKeyCode()
    {
        return keyCode;
    }
}