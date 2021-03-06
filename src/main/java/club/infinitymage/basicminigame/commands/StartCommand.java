package club.infinitymage.basicminigame.commands;

import club.infinitymage.basicminigame.BasicMinigame;
import club.infinitymage.basicminigame.managers.GameManager;
import com.sk89q.worldedit.WorldEditException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class StartCommand implements CommandExecutor {

    private BasicMinigame plugin;

    public StartCommand(BasicMinigame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("start")) return false;

        try {
            GameManager.getManager().createGame(args[0], ((Player) sender).getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WorldEditException e) {
            e.printStackTrace();
        }

        return true;

    }

}
