/**
 * LICENSE
 * MouBieChatControlPlayer
 * -------------
 * Copyright (C) 2021 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.cat.server.channel;

import com.cat.server.channel.api.Channel;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.api.CheckResult;
import org.mineacademy.chatcontrol.api.EventCancelledException;
import org.mineacademy.chatcontrol.listener.ListenerChecker;
import org.mineacademy.chatcontrol.model.SimpleChannel;

/**
 * 代表一個快捷頻道包裹器
 * @author MouBieCat
 */
public class ChannelWrapper
        implements Channel {

    // 快捷符號
    @NotNull
    protected final String prefix;

    // 頻道
    @NotNull
    protected final SimpleChannel simpleChannel;

    /**
     * 建構子
     * @param prefix        前綴快捷符號
     * @param simpleChannel 頻道
     */
    public ChannelWrapper(final @NotNull String prefix, final @NotNull SimpleChannel simpleChannel) {
        this.prefix = prefix;
        this.simpleChannel = simpleChannel;
    }

    /**
     * 檢查該訊息是否為該快捷鍵
     * @param message 玩家輸入的訊息
     * @return 是或否
     */
    public boolean checkPrefix(final @NotNull String message) {
        final String substring = message.substring(0, this.prefix.length());
        return prefix.equalsIgnoreCase(substring);
    }

    /**
     * 檢查玩家是否可以發送訊息
     * @param player 玩家
     * @param message 訊息
     * @return 是否可以
     */
    public boolean checkPlayer(final @NotNull Player player, final @NotNull String message) {
        // 建立結果
        final CheckResult result = new CheckResult(message, player);
        try {
            // 檢查結果
            final @Nullable CheckResult resultBuffer = ListenerChecker.checkMessage(result);

            // 如果原始訊息被修改
            if (resultBuffer == null || resultBuffer.hasMessageChanged())
                return false;

        } catch (final EventCancelledException ignored) { return false; }
        return true;
    }

    /**
     * 發送訊息到頻道上
     * @param sender  發送者
     * @param message 訊息
     * @param needCheck 是否需要進行發言檢查
     */
    public void sendMessage(final @NotNull Player sender, final @NotNull String message, final boolean needCheck) {
        if (!needCheck || this.checkPlayer(sender, message) && this.checkPrefix(message))
            this.simpleChannel.sendMessage(sender, message.substring(this.prefix.length()), true);
    }

    /**
     * 獲取頻道快捷符號
     * @return 符號
     */
    @NotNull
    public final String getPrefix() {
        return this.prefix;
    }

    /**
     * 獲取主要頻道實例
     * @return 頻道
     */
    @NotNull
    public final SimpleChannel getChannel() {
        return this.simpleChannel;
    }


}
