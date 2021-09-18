package cf.strafe.game;

import cf.strafe.data.PlayerData;
import cf.strafe.map.Generator;
import cf.strafe.map.Map;
import cf.strafe.util.ColorUtil;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;


import java.util.ArrayList;


@Getter
public class Game {

    private final ArrayList<PlayerData> players = new ArrayList<>();
    private final ArrayList<PlayerData> spectators = new ArrayList<>();

    private State state;

    private final Map map;
    private int gameTime;

    public Game(Map map) {
        this.map = map;
        gameTime = 30;
        state = State.WAITING;
    }

    public void addPlayer(PlayerData player) {
        if (players.size() >= map.getMaxPlayers()) {
            player.getPlayer().sendMessage(ColorUtil.translate("&cThat game is fulL!"));
            return;
        }
        if (state == State.WAITING) {
            if (!players.contains(player)) {
                players.add(player);
                player.getPlayer().sendMessage(ColorUtil.translate("&aYou have joined the game!"));
            }
        } else {
            if (!spectators.contains(player)) {
                spectators.add(player);
                player.getPlayer().sendMessage(ColorUtil.translate("&eYou are now spectating the game!"));
            }
        }
    }

    public void removePlayer(PlayerData player) {
        players.remove(player);
        spectators.remove(player);
        player.getPlayer().sendMessage(ColorUtil.translate("&cYou have left the game!"));
        for (PotionEffect effect : player.getPlayer().getActivePotionEffects())
            player.getPlayer().removePotionEffect(effect.getType());
    }

    public void update() {
        switch (state) {
            case WAITING: {
                if(players.size() >= map.getMinPlayers()) {
                    state = State.STARTING;
                }
                break;
            }
            case STARTING: {
                if(players.size() < map.getMinPlayers()) {
                    state = State.WAITING;

                    if(gameTime == 30) {
                        players.forEach(playerData -> playerData.getPlayer().sendMessage(ChatColor.RED + "Countdown cancelled waiting for players."));
                    }
                } else {
                    gameTime--;

                    if (gameTime % 5 == 0) {
                        players.forEach(playerData -> playerData.getPlayer().sendMessage(ChatColor.GREEN + "Game starting in " + gameTime + " seconds!"));
                    }
                    if(gameTime == 1) {
                        state = State.INGAME;
                    }
                }
                break;
            }
            case INGAME: {
                gameTime++;

                map.getGenerators().stream().filter(generator -> generator.getGeneratorType() == Generator.GeneratorType.BASE).forEach(Generator::generate);

                if(gameTime % 20 == 0) {
                    map.getGenerators().stream().filter(generator -> generator.getGeneratorType() == Generator.GeneratorType.DIAMOND).forEach(Generator::generate);
                }

                if(gameTime % 40 == 0) {
                    map.getGenerators().stream().filter(generator -> generator.getGeneratorType() == Generator.GeneratorType.EMERALD).forEach(Generator::generate);
                }


                break;
            }
        }
    }


    public enum State {
        WAITING, STARTING , INGAME, END;

        public String toString() {
            return StringUtils.capitalize(this.name().toLowerCase());
        }

        public State next() {
            if (values()[ordinal() + 1] == null) {
                return values()[0];
            }
            return values()[ordinal() + 1];
        }
    }
}
