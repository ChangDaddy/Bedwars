package cf.strafe.config;

import cf.strafe.Bedwars;
import lombok.experimental.UtilityClass;
import org.bukkit.configuration.file.FileConfiguration;

@UtilityClass
public class Config {
    public String KILL_MESSAGE;
    public String WIN_MESSAGE;

    public void loadConfigurations() {
        FileConfiguration config = Bedwars.INSTANCE.getPlugin().getConfig();
        KILL_MESSAGE = config.getString("themes.killMessage");
        WIN_MESSAGE = config.getString("themes.winMessage");
    }

}
