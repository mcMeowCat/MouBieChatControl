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

package com.cat.server.channel.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.model.SimpleChannel;

/**
 * 代表一個頻道
 * @author MouBieCat
 */
public interface Channel {

    /**
     * 獲取頻道快捷符號
     * @return 符號
     */
    @NotNull String getPrefix();

    /**
     * 獲取主要頻道實例
     * @return 頻道
     */
    @NotNull SimpleChannel getChannel();

    /**
     * 發送訊息到頻道上
     * @param sender  發送者
     * @param message 訊息
     * @param needCheck 是否需要進行發言檢查
     */
    void sendMessage(final @NotNull Player sender, final @NotNull String message, final boolean needCheck);

    /**
     * 檢查該訊息是否為該快捷鍵
     * @param message 玩家輸入的訊息
     * @return 是或否
     */
    boolean checkPrefix(@NotNull String message);

    /**
     * 檢查玩家是否可以發送訊息
     * @param player 玩家
     * @param message 訊息
     * @return 是否可以
     */
    boolean checkPlayer(@NotNull Player player, @NotNull String message);

}
