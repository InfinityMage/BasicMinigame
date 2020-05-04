package club.infinitymage.basicminigame.commands;

import club.infinitymage.basicminigame.BasicMinigame;
import club.infinitymage.basicminigame.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {

    private BasicMinigame plugin;

    public LeaveCommand(BasicMinigame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("leave")) return false;

        GameManager.getManager().removePlayer(((Player) sender).getPlayer());

        return true;

    }

}
