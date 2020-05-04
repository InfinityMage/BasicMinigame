package club.infinitymage.basicminigame.instances;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class GamePlayer {

    public UUID player;
    public Location assignedSpawn;
    public int playerMode; // 0 = active player; 1 = dead player (spectator); 2 = spectator

    public Location getPlayerSpawn() {
        return assignedSpawn;
    }

    public void setPlayerSpawn(Location spawn) {
        assignedSpawn = spawn;
    }

    public void tpPlayerSpawn() {
        Bukkit.getPlayer(this.player).teleport(assignedSpawn);
    }

    public GamePlayer(UUID player) {
        this.player = player;
    }

}
