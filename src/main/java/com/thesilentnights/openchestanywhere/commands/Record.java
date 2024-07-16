package com.thesilentnights.openchestanywhere.commands;

import com.thesilentnights.openchestanywhere.events.PlayerOpenChestListener;
import com.thesilentnights.openchestanywhere.utils.messageSender.MessageSender;
import com.thesilentnights.openchestanywhere.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Record implements ICommand {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            MessageSender.send(new MessageToSingle("无法在当前身份下进行该命令", commandSender));
            return true;
        }

        if (strings.length == 0) {
            MessageSender.send(new MessageToSingle("参数错误", commandSender));
            return true;
        }

        Player sender = (Player) commandSender;
        PlayerOpenChestListener.players.put(sender.getName(), strings[0]);
        MessageSender.send(new MessageToSingle("记录中", commandSender));
        PlayerOpenChestListener.ifEnabled = true;

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return Collections.singletonList("[name]");
        }
        return Collections.emptyList();
    }
}
