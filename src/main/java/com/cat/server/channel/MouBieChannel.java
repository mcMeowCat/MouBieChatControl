package com.cat.server.channel;

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
     * 獲取快捷符號
     * @return 符號
     */
    @NotNull
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * 或去 ChatControl 頻道
     * @return 頻道
     */
    @NotNull
    public SimpleChannel getChannel() {
        return this.channel;
    }

    /**
     * 檢查該訊息是否為該快捷鍵
     * @param prefix 快捷鍵符號
     * @return 是或否
     */
    public boolean checkPrefix(final @NotNull String prefix) {
        return prefix.equalsIgnoreCase(this.prefix);
    }

}
