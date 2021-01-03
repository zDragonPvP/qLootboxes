package pw.navigations.qLootboxes.commands;

import net.minecraft.util.com.google.common.primitives.Ints;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pw.navigations.qLootboxes.qLootboxes;
import pw.navigations.qLootboxes.utils.Builder;
import pw.navigations.qLootboxes.utils.ColorUtils;

public class LootboxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
            if (!sender.hasPermission("lootboxes.command.lootbox")) {
                sender.sendMessage(ColorUtils.Color("&cYou do not have permission to execute this command."));
                return true;
            }

            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (args[1].equalsIgnoreCase("all")) {
                        if (Ints.tryParse(args[2]) == null) {
                            sender.sendMessage(ColorUtils.Color("&cThe number you entered is invalid."));
                            return true;
                        }

                        int amount = Ints.tryParse(args[2]);

                        ItemStack box = Builder.nameItem(Material.valueOf(qLootboxes.getInstance().getConfig().getString("OPTIONS.LOOTBOX-ITEM.MATERIAL")), qLootboxes.getInstance().getConfig().getString("OPTIONS.LOOTBOX-ITEM.DISPLAY-NAME"), (short) qLootboxes.getInstance().getConfig().getInt("OPTIONS.LOOTBOX-ITEM.ITEM-META"), amount, qLootboxes.getInstance().getConfig().getStringList("OPTIONS.LOOTBOX-ITEM.LORES"));

                        for (Player target : Bukkit.getOnlinePlayers()) {
                            if (target.getInventory().firstEmpty() == -1) {
                                target.getWorld().dropItemNaturally(target.getLocation(), box);
                                Bukkit.broadcast(ColorUtils.Color("&4&lINFO &8» &c" + target.getName() + "'s lootbox was dropped on the ground due to lack of inventory space."), "lootboxes.command.lootbox");
                                target.sendMessage(ColorUtils.Color(ColorUtils.PREFIX + "You have received &6x" + amount + " 2020 Lootbox" + (amount == 1 ? "" : "es") + " &efrom &4" + sender.getName() + "&e, however your inventory was full so it was dropped on the ground."));
                            } else {
                                target.getInventory().addItem(box);
                                target.sendMessage(ColorUtils.Color(ColorUtils.PREFIX + "You have received &6x" + amount + " 2020 Lootbox" + (amount == 1 ? "" : "es") + " &efrom &4" + sender.getName() + "&e."));
                            }
                        }
                    } else {
                        if (Bukkit.getPlayer(args[1]) == null) {
                            sender.sendMessage(ColorUtils.Color("&cA player with that name was not found."));
                            return true;
                        }

                        if (Ints.tryParse(args[2]) == null) {
                            sender.sendMessage(ColorUtils.Color("&cThe number you entered is invalid."));
                            return true;
                        }

                        Player target = Bukkit.getPlayer(args[1]);
                        int amount = Ints.tryParse(args[2]);

                        ItemStack box = Builder.nameItem(Material.valueOf(qLootboxes.getInstance().getConfig().getString("OPTIONS.LOOTBOX-ITEM.MATERIAL")), qLootboxes.getInstance().getConfig().getString("OPTIONS.LOOTBOX-ITEM.DISPLAY-NAME"), (short) qLootboxes.getInstance().getConfig().getInt("OPTIONS.LOOTBOX-ITEM.ITEM-META"), amount, qLootboxes.getInstance().getConfig().getStringList("OPTIONS.LOOTBOX-ITEM.LORES"));

                        if (target.getInventory().firstEmpty() == -1) {
                            target.getWorld().dropItemNaturally(target.getLocation(), box);
                            Bukkit.broadcast(ColorUtils.Color("&4&lINFO &8» &c" + target.getName() + "'s lootbox was dropped on the ground due to lack of inventory space."), "lootboxes.command.lootbox");
                            target.sendMessage(ColorUtils.Color(ColorUtils.PREFIX + "You have received &6x" + amount + " 2020 Lootbox" + (amount == 1 ? "" : "es") + " &efrom &4" + sender.getName() + "&e, however your inventory was full so it was dropped on the ground."));
                        } else {
                            target.getInventory().addItem(box);
                            target.sendMessage(ColorUtils.Color(ColorUtils.PREFIX + "You have received &6x" + amount + " 2020 Lootbox" + (amount == 1 ? "" : "es") + " &efrom &4" + sender.getName() + "&e."));
                        }
                    }
                    return true;
                }
            }

            sender.sendMessage(ColorUtils.Color("&cUsage: /" + string + " give <player | all> <amount>"));
        
        return false;
    }

}
