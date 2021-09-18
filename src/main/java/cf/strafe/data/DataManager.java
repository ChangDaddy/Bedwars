package cf.strafe.data;

import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public enum DataManager {
    INSTANCE;

    private final ConcurrentHashMap<UUID, PlayerData> playerDataMap = new ConcurrentHashMap<>();

    public void addPlayer(Player player) {
        playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
    }

    public void removePlayer(Player player) {
        playerDataMap.remove(player.getUniqueId(), new PlayerData(player.getUniqueId()));
    }

    public PlayerData getPlayer(Player player) {
        return playerDataMap.get(player.getUniqueId());
    }

}
