package cf.strafe.game;

import cf.strafe.Bedwars;
import cf.strafe.map.Map;
import cf.strafe.map.MapManager;
import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum GameManager {
    INSTANCE;

    private final List<Game> runningGames = new ArrayList<>();

    public void init() {
        for (Map map : MapManager.INSTANCE.getMaps()) {
            runningGames.add(new Game(map));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Game game : runningGames) {
                    game.update();
                }
            }
        }.runTaskTimerAsynchronously(Bedwars.INSTANCE.getPlugin(), 0, 20);
    }
}
