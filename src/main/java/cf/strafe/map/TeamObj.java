package cf.strafe.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@Getter
@RequiredArgsConstructor
public class TeamObj {
    private final String name;
    private final Location spawnLocation, bedLocation;
}
