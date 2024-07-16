package com.thesilentnights.openchestanywhere.commands;

import com.thesilentnights.openchestanywhere.repo.Chest;
import com.thesilentnights.openchestanywhere.repo.ChestRepo;
import com.thesilentnights.openchestanywhere.utils.messageSender.MessageSender;
import com.thesilentnights.openchestanywhere.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Create implements ICommand {
    List<String> tabComplete = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            boolean needOp = Boolean.parseBoolean(strings[2]); //permission
            int size = Integer.parseInt(strings[1]);
            if ((size % 9) != 0 || size < 9 || size > 54) {
                MessageSender.send(new MessageToSingle("箱子的大小必须是满足 9<=size<=54 的9的倍数", commandSender));
                return true;
            }
            ChestRepo.addInventory(
                    strings[0],
                    new Chest(strings[0], Bukkit.createInventory(null, Integer.parseInt(strings[1])), needOp)
            );

            MessageSender.send(new MessageToSingle("成功创建了名为" + strings[0] + "的箱子", commandSender));

        } catch (NumberFormatException exception) {
            MessageSender.send(new MessageToSingle("权限格式错误", commandSender));

        } catch (ArrayIndexOutOfBoundsException exception) {
            ChestRepo.addInventory(
                    strings[0],
                    new Chest(strings[0], Bukkit.createInventory(null, Integer.parseInt(strings[1])))
            );
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        switch (strings.length) {
            case 1:
                return Collections.singletonList("[name]");
            case 2:
                Collections.addAll(tabComplete, "9", "18", "27", "36", "45", "54");
                return tabComplete;
            case 3:
                return Collections.singletonList("[accessPerm]");
            default:
                return Collections.emptyList();
        }
    }
}
