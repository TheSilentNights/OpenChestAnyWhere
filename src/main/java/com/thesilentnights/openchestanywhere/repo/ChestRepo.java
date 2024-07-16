package com.thesilentnights.openchestanywhere.repo;

import org.bukkit.inventory.Inventory;

import java.util.*;

public class ChestRepo {
    private static final Map<String, Chest> inventories;

    static {
        inventories = new HashMap<>();
    }

    public static void addInventory(String name, Chest inventory) {
        inventories.put(name, inventory);
    }

    public static Chest getInventory(String name) {
        return inventories.get(name);
    }

    public static Map<String, Chest> getInventories() {
        return inventories;
    }


}
