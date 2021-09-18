package cf.strafe;

import cf.strafe.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class BedwarsPlugin extends JavaPlugin {
    @Override
    public void onLoad() {
        Bedwars.INSTANCE.onLoad(this);
        super.onLoad();
    }

    @Override
    public void onEnable() {
        Bedwars.INSTANCE.onEnable();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Bedwars.INSTANCE.onDisable();
        super.onDisable();
    }
}
