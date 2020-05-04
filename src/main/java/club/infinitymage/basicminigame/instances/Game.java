package club.infinitymage.basicminigame.instances;

import com.sk89q.worldedit.WorldEditException;

import java.io.IOException;
import java.util.*;

public class Game {

    public String id;
    public GameMap gameMap;
    public String world;
    public boolean started = false;
    public int waitTime = 60;
    public int maxPlayers = 2;
    public int minPlayers = 2;
    public List<UUID> players = new ArrayList<UUID>();
    public Map<UUID, GamePlayer> playerData = new HashMap<UUID, GamePlayer>();

    public Game(String managerID, String mapID) throws IOException, WorldEditException {

        gameMap = new GameMap(mapID, managerID);

    }

    public String getId() {
        return this.id;
    }

    public List<UUID> getPlayers() {
        return this.players;
    }

    public boolean addPlayer(UUID player) {
        if (!this.started && players.size()+1 <= this.maxPlayers) {
            players.add(player);
            playerData.put(player, new GamePlayer(player));
            playerData.get(player).setPlayerSpawn(gameMap.assignableSpawns.get(players.size()-1));
            return true;
        }
        return false;
    }

    public boolean removePlayer(UUID player) {
        if (players.contains(player)) {
            players.remove(player);
            playerData.remove(player);
            return true;
        }
        return false;
    }

}