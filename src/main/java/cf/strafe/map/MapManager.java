package cf.strafe.map;

import cf.strafe.Bedwars;
import cf.strafe.util.LocationUtil;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public enum MapManager {
    INSTANCE;

    private final List<Map> maps = new ArrayList<>();

    public void loadMaps() {
        File file = new File(Bedwars.INSTANCE.getPlugin().getDataFolder(), "Maps.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
            for(String key : yml.getKeys(false)) {
                List<TeamObj> teamObjList = new ArrayList<>();
                List<Generator> generators = new ArrayList<>();
                String name = yml.getString(key + ".name");
                boolean team = yml.getBoolean(key + ".team");
                int maxPlayers = yml.getInt(key + ".maxPlayers");
                int minPlayers = yml.getInt(key + ".minPlayers");
                Location location = LocationUtil.parseToLocation(yml.getString(key + ".lobbyLocation"));
                for(int i = 0; i < yml.getInt(key + ".teams.count"); i++) {
                    String teamName = yml.getString(key + ".teams." + i + ".name");
                    Location spawnLocation = LocationUtil.parseToLocation(yml.getString(key + ".teams." + i + ".spawn"));
                    Location bedLocation = LocationUtil.parseToLocation(yml.getString(key + ".teams." + i + ".bedLocation"));
                    teamObjList.add(new TeamObj(teamName, spawnLocation, bedLocation));
                }
                for(int i = 0; i < yml.getInt(key + ".generator.count"); i++) {
                    Location generatorLocation = LocationUtil.parseToLocation((key + ".generator." + i + ".location"));
                    Generator.GeneratorType generatorType = Generator.GeneratorType.valueOf(yml.getString(key + ".generator." + i + ".type"));
                    generators.add(new Generator(generatorType, generatorLocation));
                }

                maps.add(new Map(name, team, maxPlayers,minPlayers, location, teamObjList, generators));

            }

        }
    }

    public void unloadMaps() {
        File file = new File(Bedwars.INSTANCE.getPlugin().getDataFolder(), "Maps.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        for(Map map : maps) {
            String key = map.getName();
            yml.set(key + ".name", key);
            yml.set(key + ".team", map.isTeam());
            yml.set(key + ".lobbyLocation", LocationUtil.parseToString(map.getLobbyLocation()));
            yml.set(key + ".maxPlayers", map.getMaxPlayers());
            yml.set(key + ".minPlayers", map.getMinPlayers());
            int counter = 0;

            for(TeamObj obj : map.getTeamObjList()) {
                yml.set(key + ".teams." + counter + ".id", counter);
                yml.set(key + ".teams." + counter + ".name", obj.getName());
                yml.set(key + ".teams." + counter + ".spawn", obj.getSpawnLocation());
                yml.set(key + ".teams." + counter + ".bedLocation", obj.getBedLocation());
                counter++;
            }
            yml.set(key + ".teams.count", counter);
            counter = 0;
            for(Generator generator : map.getGenerators()) {
                yml.set(key + ".generator." + counter + ".id", counter);
                yml.set(key + ".generator." + counter + ".location", generator.getLocation());
                yml.set(key + ".generator." + counter + ".type", generator.getGeneratorType().toString());
                counter++;
            }

            yml.set(key + ".generator.count", counter);

        }
    }

}
