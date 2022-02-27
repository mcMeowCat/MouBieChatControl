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

package com.cat.server.listener;

import com.cat.server.MouBieCat;
import com.cat.server.channel.api.Channel;
import com.cat.server.channel.api.Channels;
import com.cat.server.channel.api.DefaultChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.api.CheckResult;
import org.mineacademy.chatcontrol.api.EventCancelledException;
import org.mineacademy.chatcontrol.listener.ListenerChecker;

/**
 * 有關玩家聊天事件的監聽類
 * @author MouBieCat
 */
public final class PlayerChatEvent
        implements Listener {

    /**
     * 玩家聊天事件
     * @param event 事件
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(final @NotNull AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();

        if (this.playerChecker(player, message))
            // 發送訊息
            this.sendMessage(player, message);

        // 無論如何取消事件
        event.setCancelled(true);
    }

    /**
     * 檢查玩家是否可以發送訊息
     * @param player 玩家
     * @param message 訊息
     * @return 是否可以
     */
    private boolean playerChecker(final @NotNull Player player, final @NotNull String message) {
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
     * 發送訊息到指定頻道
     * @param player 玩家
     * @param message 訊息
     */
    private void sendMessage(final @NotNull Player player, final @NotNull String message) {
        // 從訊息中提取快捷符號
        final String prefix = message.substring(0, 1);

        // 獲取所有頻道
        final Channels manager = MouBieCat.getInstance().getChannelManager();

        // 獲取玩家前綴頻道快捷符號
        final @Nullable Channel channel = manager.get(prefix);

        // 如果頻道不為空、檢查快捷符號符合
        if (channel != null && channel.checkPrefix(prefix))
            channel.sendMessage(player, message.substring(1));

        else {
            // 這裡將把訊息帶往預設頻道
            final @Nullable DefaultChannel defaultChannel = manager.getDefaultChannel();
            if (defaultChannel != null)
                defaultChannel.sendMessage(player, message);
        }
    }

}
