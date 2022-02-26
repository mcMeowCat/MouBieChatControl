package com.cat.server.channel;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.model.SimpleChannel;

/**
 * 代表一個前綴一個頻道的頻道包裝器
 * @author MouBieCat
 */
public record MouBieChannel(@NotNull String prefix,
                            @NotNull SimpleChannel channel) {

    /**
     * 建構子
     * @param prefix 前綴快捷符號
     * @param channel 頻道
     */
    public MouBieChannel(final @NotNull String prefix, final @NotNull SimpleChannel channel) {
        this.prefix = prefix;
        this.channel = channel;
    }

    /**
     * 檢查該訊息是否為該快捷鍵
     * @param prefix 快捷鍵符號
     * @return 是或否
     */
    public boolean checkPrefix(final @NotNull String prefix) {
        return prefix.equalsIgnoreCase(this.prefix);
    }

    /**
     * 發送訊息到頻道上
     * @param sender 發送者
     * @param message 訊息
     * @param sendToSelf 是否發送給自己
     */
    public void sendMessage(final @NotNull CommandSender sender, final @NotNull String message, final boolean sendToSelf) {
        this.channel.sendMessage(sender, message, sendToSelf);
    }

}
