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
import org.mineacademy.chatcontrol.model.SimpleChannel;

/**
 * 代表一個快捷頻道包裹器
 * @author MouBieCat
 */
public class ChannelWrapper
        implements Channel {

    // 快捷符號
    @NotNull
    private final String prefix;

    // 頻道
    @NotNull
    private final SimpleChannel simpleChannel;

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
     * @param prefix 快捷鍵符號
     * @return 是或否
     */
    public boolean checkPrefix(final @NotNull String prefix) {
        return prefix.equalsIgnoreCase(this.prefix);
    }

    /**
     * 發送訊息到頻道上
     * @param sender  發送者
     * @param message 訊息
     */
    public final void sendMessage(final @NotNull Player sender, final @NotNull String message) {
        this.simpleChannel.sendMessage(sender, message, true);
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
