package res;

import java.util.HashMap;
import java.util.Map;

public class ConfigLoader
{
    private static Map<String, String> cache = new HashMap<>();

    private ConfigLoader()
    {
    }

    public static String loadConfig(String fileName)
    {
        return cache.computeIfAbsent(
            fileName,
            (k) -> ConfigLoader.class.getResource("configs/" + k).getPath()
        );
    }
}
