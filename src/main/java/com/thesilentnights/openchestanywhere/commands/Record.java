package com.thesilentnights.openchestanywhere.commands;

import com.thesilentnights.openchestanywhere.events.PlayerOpenChestListener;
import com.thesilentnights.openchestanywhere.utils.messageSender.MessageSender;
import com.thesilentnights.openchestanywhere.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Record implements ICommand {
    List<String> list = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            MessageSender.send(new MessageToSingle("无法在当前身份下进行该命令", commandSender));
            return true;
        }


        Player sender = (Player) commandSender;
        PlayerOpenChestListener.players.put(sender.getName(), strings[1]);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        list.clear();
        switch (strings.length){
            case 1:
                Collections.addAll(list,"enderchest","chest");
                return list;
            case 2:
                list.add("[name]");
                return list;
            default:
                return Collections.emptyList();
        }
    }
}
