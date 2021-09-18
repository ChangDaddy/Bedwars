package cf.strafe.data;

import cf.strafe.game.Game;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {
    private final UUID uuid;
    private final Player player;
    private Game game;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        player = Bukkit.getPlayer(uuid);
    }


}
