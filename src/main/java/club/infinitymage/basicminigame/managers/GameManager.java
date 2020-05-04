package club.infinitymage.basicminigame.managers;

import club.infinitymage.basicminigame.BasicMinigame;
import club.infinitymage.basicminigame.instances.Game;
import com.sk89q.worldedit.WorldEditException;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.util.*;

import static club.infinitymage.basicminigame.BasicMinigame.maxGames;
import static club.infinitymage.basicminigame.util.CalculateGameID.calculateGameID;

public class GameManager {

    public Map<UUID, Location> locs = new HashMap<UUID, Location>();

    public static GameManager gm = new GameManager();
    List<Game> games = new ArrayList<Game>();

    private BasicMinigame plugin;
    public GameManager(BasicMinigame plugin) {
        this.plugin = plugin;
    }

    public GameManager() {

    }

    public static GameManager getManager() {
        return gm;
    }

    public Game getGame(String id) {
        for (Game g : games) {
            if(g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public String createGame(String mapID) {
        // Responses: 0 = too many games; 1 = unknown error

        if (games.size() > (maxGames - 1)) {
            return "0";
        }

        try {
            String id = calculateGameID(games.size());
            games.add(new Game(id, "test"));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WorldEditException e) {
            e.printStackTrace();
        }

        return "1";
    }

    public Integer addPlayer(String id, UUID p) {
        // Responses: 0 = nonexistent game; 1 = player already in a game; 2 = success
        Game g = getGame(id);

        if (g == null) {
            return 0;
        }

        if (checkPlayer(p)) {
            return 1;
        }

        g.getPlayers().add(p);

        locs.put(p, Bukkit.getPlayer(p).getLocation());

        return 2;

    }

    public Integer removePlayer(UUID p) {
        // Responses: 0 = player not in game; 1 = success

        Game g = null;

        for (Game game : games) {
            if (game.getPlayers().contains(p)) {
                g = game;
            }
        }

        if (g == null) {
            return 0;
        }

        g.getPlayers().remove(p);

        Bukkit.getPlayer(p).teleport(locs.get(p));
        locs.remove(p);

        return 1;

    }

    public boolean checkPlayer(UUID p) {

        Game g = null;

        for (Game game : games) {
            if (game.getPlayers().contains(p)) {
                g = game;
            }
        }

        return g != null;

    }

}
