package dev.sasukector.hundreddaysbase;

import dev.sasukector.hundreddaysbase.commands.TPSCommand;
import dev.sasukector.hundreddaysbase.commands.ToggleDaysCommand;
import dev.sasukector.hundreddaysbase.commands.PlayedCommand;
import dev.sasukector.hundreddaysbase.controllers.BoardController;
import dev.sasukector.hundreddaysbase.controllers.TeamsController;
import dev.sasukector.hundreddaysbase.events.SpawnEvents;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HundredDaysDesert extends JavaPlugin {

    private static @Getter HundredDaysDesert instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.DARK_PURPLE + "HundredDaysDesert startup!");
        instance = this;

        // Configuration
        this.saveDefaultConfig();

        // Register events
        this.getServer().getPluginManager().registerEvents(new SpawnEvents(), this);
        Bukkit.getOnlinePlayers().forEach(player -> BoardController.getInstance().newPlayerBoard(player));

        // Register commands
        Objects.requireNonNull(HundredDaysDesert.getInstance().getCommand("conter-tps")).setExecutor(new TPSCommand());
        Objects.requireNonNull(HundredDaysDesert.getInstance().getCommand("played")).setExecutor(new PlayedCommand());
        Objects.requireNonNull(HundredDaysDesert.getInstance().getCommand("toggleDays")).setExecutor(new ToggleDaysCommand());

        // Start teams
        TeamsController.getInstance();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.DARK_PURPLE + "HundredDaysDesert shutdown!");
    }
}
