package cf.strafe;

import cf.strafe.config.Config;
import cf.strafe.data.DataListener;
import cf.strafe.map.Map;
import cf.strafe.map.MapManager;
import lombok.Getter;


@Getter
public enum Bedwars {
    INSTANCE;

    private BedwarsPlugin plugin;

    public void onLoad(BedwarsPlugin plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
        Config.loadConfigurations();
        handleBukkit();
        MapManager.INSTANCE.loadMaps();
    }

    public void onDisable() {
        MapManager.INSTANCE.unloadMaps();
    }

    public void handleBukkit() {
        plugin.getServer().getPluginManager().registerEvents(new DataListener(), plugin);
    }

}
