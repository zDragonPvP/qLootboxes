package pw.navigations.qLootboxes;

import jdk.internal.jline.internal.TerminalLineSettings;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pw.navigations.qLootboxes.commands.LootboxCommand;
import pw.navigations.qLootboxes.listeners.LootboxListener;

public class qLootboxes extends JavaPlugin {
    @Getter public static qLootboxes instance;

    public static TerminalLineSettings getInstance() {
        return null;
    }

    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        this.getCommand("lootbox").setExecutor(new LootboxCommand());
        this.getServer().getPluginManager().registerEvents(new LootboxListener(), this);
    }
}
