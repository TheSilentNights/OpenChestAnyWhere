package com.thesilentnights.openchestanywhere.repo;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class ChestRepo {
    private static final Map<String, Inventory> inventories;
    static {
        inventories = new HashMap<>();
    }

    public static void addInventory(String name,Inventory inventory){
        inventories.put(name,inventory);
    }

    public static Inventory getInventory(String name){
        return inventories.get(name);
    }

    public static Map<String, Inventory> getInventories(){
        return inventories;
    }
}
