package cf.strafe.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

import java.util.List;


@RequiredArgsConstructor
@Getter
public class Map {
    /* Key */
    private final String name;

    /* Values */
    private final boolean team;
    private final int maxPlayers, minPlayers;
    private final Location lobbyLocation;
    private final List<TeamObj> teamObjList;
    private final List<Generator> generators;

}
