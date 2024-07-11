package com.thesilentnights.openchestanywhere.commands;

import com.thesilentnights.openchestanywhere.OpenChestAnywhere;
import com.thesilentnights.openchestanywhere.repo.ChestRepo;
import com.thesilentnights.openchestanywhere.utils.messageSender.MessageSender;
import com.thesilentnights.openchestanywhere.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Open implements ICommand {
    List<String> list = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 1) {
            MessageSender.send(new MessageToSingle("参数错误", commandSender));
            return true;
        }

        if (!(commandSender instanceof Player)) {
            MessageSender.send(new MessageToSingle("无法在当前身份下进行该命令", commandSender));
            return true;
        }

        Player sender = (Player) commandSender;
        switch (strings[0]) {
            case "recorded":
                Inventory inventory = ChestRepo.getInventory(strings[1]);
                if (inventory != null) {
                    sender.openInventory(inventory);
                } else {
                    MessageSender.send(new MessageToSingle("该箱子不存在或未被标记", sender));
                }
                return true;
            case "player":
                Player target = OpenChestAnywhere.getInstance().getServer().getPlayer(strings[1]);
                if (target == null) {
                    MessageSender.send(new MessageToSingle("玩家不存在", commandSender));
                    return true;
                }
                sender.openInventory(target.getInventory());
                return true;
            case "enderchest":
                Player target2 = OpenChestAnywhere.getInstance().getServer().getPlayer(strings[1]);
                if (target2 == null) {
                    MessageSender.send(new MessageToSingle("玩家不存在", commandSender));
                    return true;
                }
                sender.openInventory(target2.getEnderChest());
                return true;
            case "list":
                ChestRepo.getInventories().keySet().forEach(key -> {
                    MessageSender.send(new MessageToSingle(key,sender));
                });
            default:
                MessageSender.send(new MessageToSingle("不存在该操作", commandSender));
                return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        list.clear();
        switch (strings.length) {
            case 1:
                Collections.addAll(list, "player", "recorded","list","enderchest");
                return list;
            case 2:
                if (strings[0].equals("recorded")) {
                    list.addAll(ChestRepo.getInventories().keySet());
                } else if (strings[0].equals("player")) {
                    OpenChestAnywhere.getInstance().getServer().getOnlinePlayers().forEach(player -> {
                        list.add(player.getName());
                    });
                }
                return list;
            default:
                return Collections.emptyList();
        }
    }
}
