package com.thesilentnights.openchestanywhere.events;

import com.thesilentnights.openchestanywhere.repo.ChestRepo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.*;

public class PlayerOpenChestListener implements Listener {
    public static Map<String, String> players = new HashMap<>();

    @EventHandler
    public void PlayerOpenChest(InventoryOpenEvent event) {
        if (event.getInventory().getType().equals(InventoryType.CHEST) && players.containsKey(event.getPlayer().getName())) {
            ChestRepo.addInventory(players.get(event.getPlayer().getName()), event.getInventory());
        }
        players.remove(event.getPlayer().getName());
    }
}
