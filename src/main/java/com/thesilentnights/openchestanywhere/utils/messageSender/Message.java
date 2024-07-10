package com.thesilentnights.openchestanywhere.utils.messageSender;

import org.bukkit.command.CommandSender;

abstract public class Message {
    protected CommandSender targetPlayer;
    protected String body;

    abstract protected void send();
}

