package com.dropfl.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import res.ConfigLoader;

public class Configurations
{
    private static Yaml yaml = new Yaml();
    private static File file;
    private static Configurations meta;

    public Map<String, Config> properties;

    static
    {
        try
        {
            File file = new File(Configurations.class.getResource("meta.yml").toURI());
            FileInputStream stream = new FileInputStream(file);
            meta = yaml.loadAs(stream, Configurations.class);
        }
        catch (IOException | URISyntaxException e)
        {
            e.printStackTrace();
        }
    }

    private Configurations()
    {
    }

    public static void initalize(String fileName)
    {
        Map<String, Object> settings;

        try
        {
            file = new File(ConfigLoader.loadConfig(fileName));
            settings = yaml.load(new FileInputStream(file));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            settings = new HashMap<>();
        }

        for (String key : meta.properties.keySet())
        {
            Config cfg = meta.properties.get(key);

            try
            {
                cfg.set(settings.get(key));
            }
            catch (Exception e)
            {
                cfg.set(cfg.getDefaultValue());
            }            
        }
    }

    public static String getProperties()
    {
        return meta.properties.toString();
    }

    public static Config get(String name)
    {
        return meta.properties.get(name);
    }
}