package com.thesilentnights.openchestanywhere.events;

import com.thesilentnights.openchestanywhere.repo.ChestRepo;
import com.thesilentnights.openchestanywhere.utils.messageSender.MessageSender;
import com.thesilentnights.openchestanywhere.utils.messageSender.messageImp.MessageToSingle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.*;

public class PlayerOpenChestListener implements Listener {
    public static Map<String, String> players = new HashMap<>();
    public static boolean ifEnabled = false;

    @EventHandler
    public void PlayerOpenChest(InventoryOpenEvent event) {
        if (!ifEnabled) {
            return;
        }
        if (event.getInventory().getType().equals(InventoryType.CHEST) && players.containsKey(event.getPlayer().getName())) {
            ChestRepo.addInventory(players.get(event.getPlayer().getName()), event.getInventory());
        }
        MessageSender.send(new MessageToSingle("已将该inventory标记为:" + players.get(event.getPlayer().getName()), event.getPlayer()));
        players.remove(event.getPlayer().getName());
        MessageSender.send(new MessageToSingle("记录结束", event.getPlayer()));


        ifEnabled = false;
    }
}
