package com.cat.server.listener;

import com.cat.server.MouBieCat;
import com.cat.server.channel.MouBieChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

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

        // 獲取玩家前綴頻道快捷符號
        final MouBieChannel prefixChannel = MouBieCat.getInstance().getChannelManager().getPrefixChannel(message);
        if (prefixChannel == null)
            return;

        // 如果快捷符號有被找到，則對該頻道發送聊天訊息
        prefixChannel.sendMessage(player, message.substring(1), true);
        event.setCancelled(true);
    }

}
