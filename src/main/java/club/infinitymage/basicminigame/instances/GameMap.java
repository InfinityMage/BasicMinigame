package club.infinitymage.basicminigame.instances;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static club.infinitymage.basicminigame.BasicMinigame.testSchemFile;

public class GameMap {

    String[] acceptableMapIDs = {
            "test"
    };

    public String mapID;
    public World world;
    public File schemFile;
    public List<Location> assignableSpawns = new ArrayList<Location>();
    public int negXBound;
    public int yBound;
    public int negZBound;

    public GameMap(String map, String wld) throws IOException, WorldEditException {
        if (!Arrays.asList(acceptableMapIDs).contains(map)) return;

        world = Bukkit.getWorld(wld);

        if(map == "test") {
            schemFile = testSchemFile;
            mapID = "test";
            assignableSpawns.add(new Location(world, 5.5, 57, 14.5));
            assignableSpawns.add(new Location(world, -5.5, 57, -14.5));
            negXBound = -17;
            yBound = 90;
            negZBound = -26;
            pasteMap();
        }
    }

    public String getMapID() {
        return mapID;
    }

    public List<Location> getAssignableSpawns() {
        return assignableSpawns;
    }

    public void pasteMap() throws IOException, WorldEditException {
        Clipboard clipboard;

        ClipboardFormat format = ClipboardFormats.findByFile(schemFile);

        ClipboardReader reader = format.getReader(new FileInputStream(schemFile));
        clipboard = reader.read();

        World wld = world;
        com.sk89q.worldedit.world.World weWld = BukkitAdapter.adapt(wld);

        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(weWld, -1);
        Operation operation = new ClipboardHolder(clipboard)
                .createPaste(editSession)
                .to(BlockVector3.at(0, 64, 0))
                .build();
        Operations.complete(operation);
        editSession.flushSession();
    }

    public void clearMap() {
        // Loop through all y levels
        for (int y = yBound; y > 0; y--) {
            // Loop through all x positions
            for (int x = negXBound; x < Math.abs(negXBound); x++) {
                // Loop through all z positions
                for (int z = negZBound; z < Math.abs(negZBound); z++) {
                    world.getBlockAt(x, y, z).setType(Material.AIR);
                }
            }
        }
    }

}
