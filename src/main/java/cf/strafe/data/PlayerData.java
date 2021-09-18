package cf.strafe.data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {
    private final UUID uuid;
    private boolean inGame;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }
}
