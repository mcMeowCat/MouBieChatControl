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
import com.cat.server.channel.Channel;
import com.cat.server.channel.Channels;
import com.cat.server.channel.DefaultChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        final String prefix = message.substring(0, 1);

        final Channels manager = MouBieCat.getInstance().getChannelManager();

        // 獲取玩家前綴頻道快捷符號
        final @Nullable Channel channel = manager.get(prefix);

        if (channel != null && channel.checkPrefix(prefix))
            channel.sendMessage(player, message.substring(1));

        else {
            // 這裡將把訊息帶往預設頻道
            final Channel defaultChannel = manager.get("");
            if (defaultChannel instanceof DefaultChannel)
                defaultChannel.sendMessage(player, message);
        }

        // 無論如何取消事件
        event.setCancelled(true);
    }

}
