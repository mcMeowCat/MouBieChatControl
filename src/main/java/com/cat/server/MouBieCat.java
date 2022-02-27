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

package com.cat.server;

import com.cat.server.channel.ChannelManager;
import com.cat.server.channel.api.Channels;
import com.cat.server.listener.PlayerChatEvent;
import com.moubieapi.api.plugin.PluginRegister;
import com.moubieapi.moubieapi.plugin.MouBiePluginBase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的主要類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePluginBase {

    // 頻道管理器
    @NotNull
    private final Channels manager = new ChannelManager();

    @PluginRegister(name = "註冊插件檔案", type = PluginRegister.ActionType.ACTION_ENABLE, priority = PluginRegister.ActionPriority.HIGHEST)
    public void registerFiles() {
        // 頻道加載器
        this.manager.loadChannels(new ChannelLoader());
    }

    @PluginRegister(name = "註冊插件事件", type = PluginRegister.ActionType.ACTION_ENABLE)
    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(), this);
    }


    @PluginRegister(name = "重載插件檔案", type = PluginRegister.ActionType.ACTION_RELOAD)
    public void reloadFiles() {
        this.manager.loadChannels(new ChannelLoader());
    }


    /**
     * 獲取頻道管理器
     * @return 管理器
     */
    @NotNull
    public Channels getChannelManager() {
        return this.manager;
    }

    /**
     * 獲取插件本身
     * @return 插件本身
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

}
