package club.infinitymage.basicminigame.commands;

import club.infinitymage.basicminigame.BasicMinigame;
import club.infinitymage.basicminigame.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    private BasicMinigame plugin;

    public JoinCommand(BasicMinigame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("join")) return false;

        GameManager.getManager().addPlayer(args[0], ((Player) sender).getPlayer());

        return true;

    }

}
