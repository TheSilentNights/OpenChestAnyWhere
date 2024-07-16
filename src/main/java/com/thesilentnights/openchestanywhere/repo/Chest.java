package com.thesilentnights.openchestanywhere.repo;

import org.bukkit.inventory.Inventory;

public class Chest {
    private final String name;
    private final boolean needOp;
    private final Inventory inventory;

    public Chest(String name, Inventory inventory, boolean needOp) {
        this.name = name;
        this.needOp = needOp;
        this.inventory = inventory;
    }

    public Chest(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
        this.needOp = false;
    }

    public String getName() {
        return name;
    }

    public boolean getAccessPermission() {
        return needOp;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
