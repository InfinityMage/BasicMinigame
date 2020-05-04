package club.infinitymage.basicminigame;

import club.infinitymage.basicminigame.commands.JoinCommand;
import club.infinitymage.basicminigame.commands.LeaveCommand;
import club.infinitymage.basicminigame.commands.StartCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BasicMinigame extends JavaPlugin {

    public static File testSchemFile;
    public static Integer maxGames;

    @Override
    public void onEnable() {

        PluginManager pm = Bukkit.getPluginManager();

        testSchemFile = new File(getDataFolder(),"testMap.schem");

        this.getCommand("start").setExecutor(new StartCommand(this));
        this.getCommand("join").setExecutor(new JoinCommand(this));
        this.getCommand("leave").setExecutor(new LeaveCommand(this));

    }

    @Override
    public void onDisable() {

    }

}