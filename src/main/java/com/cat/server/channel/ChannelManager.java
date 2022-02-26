package com.cat.server.channel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * 代表頻道管理紀錄器
 * @author MouBieCat
 */
public final class ChannelManager {

    // 頻道集合
    @NotNull
    private final List<MouBieChannel> channels = new LinkedList<>();

    /**
     * 註冊一個頻道
     * @param channel 頻道
     */
    public void register(final @NotNull MouBieChannel channel) {
        final @Nullable MouBieChannel prefixChannel = this.getPrefixChannel(channel.getPrefix());
        if (prefixChannel == null)
            this.channels.add(channel);
    }

    /**
     * 取消註冊一個頻道
     * @param channel 頻道
     */
    public void unregister(final @NotNull MouBieChannel channel) {
        this.channels.remove(channel);
    }

    /**
     * 取消註冊一個頻道
     * @param prefix 快捷符號
     */
    public void unregister(final @NotNull String prefix) {
        final @Nullable MouBieChannel prefixChannel = this.getPrefixChannel(prefix);
        if (prefixChannel != null)
            this.unregister(prefixChannel);
    }

    /**
     * 根據訊息或取一個頻道
     * @param message 帶有快捷符號的訊息
     * @return 頻道(為null代表沒有這個快捷符號頻道)
     */
    @Nullable
    public MouBieChannel getPrefixChannel(final @NotNull String message) {
        final String prefix = message.substring(0, 1);
        for (final MouBieChannel channel : this.channels) {
            if (channel.checkPrefix(prefix)) {
                return channel;
            }
        }
        return null;
    }

    /**
     * 清除所有頻道
     */
    public void clearChannels() {
        this.channels.clear();
    }

    /**
     * 根據檔案內容加載頻道
     * @param loader 加載器
     */
    public void loadChannels(final @NotNull ChannelLoader loader) {
        this.clearChannels();
        for (final MouBieChannel channel : loader.parsePrefixChannels())
            this.register(channel);
    }

}
