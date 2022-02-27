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

import com.cat.server.channel.api.Channel;
import com.cat.server.channel.ChannelWrapper;
import com.cat.server.channel.DefaultChannelWrapper;
import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.model.SimpleChannel;
import org.mineacademy.chatcontrol.settings.ChannelSettings;

import java.util.LinkedList;
import java.util.List;

/**
 * 頻道檔案加載器
 * @author MouBieCat
 */
public final class ChannelLoader extends Loader {

    private static final String CHANNELS_PATH_CHANNELS = "Channels";

    private static final String CHANNELS_PATH_DEFAULT_CHANNELS = "DefaultChannel";

    /**
     * 建構子
     */
    public ChannelLoader() {
        super(MouBieCat.getInstance(), "", "Channels.yml", false);
    }

    /**
     * 根據檔案內容解析快捷頻道
     * @return 頻道集合
     */
    @NotNull
    public List<Channel> parseChannels() {
        final List<Channel> channels = new LinkedList<>();

        // 處理各種頻道
        this.loadChannels(channels);
        this.loadDefaultChannel(channels);

        return channels;
    }

    /**
     * 處理具有快捷符號的頻道
     * @param channelsBuffer 頻道集合(用於存放)
     */
    private void loadChannels(final @NotNull List<Channel> channelsBuffer) {
        final @Nullable ConfigurationSection channelsSection = this.configuration.getConfigurationSection(CHANNELS_PATH_CHANNELS);

        if (channelsSection == null)
            return;

        for (final String prefix : channelsSection.getKeys(false)) {
            final String channelName = this.getString(CHANNELS_PATH_CHANNELS + "." + prefix);
            final @Nullable SimpleChannel simpleChannel = ChannelSettings.getChannel(channelName);

            if (simpleChannel != null)
                channelsBuffer.add(new ChannelWrapper(prefix, ChannelSettings.getChannel(channelName)));

            // 在控制台印出錯誤訊息
            else
                this.logErrorChannel(channelName);
        }
    }

    /**
     * 處理預設發言頻道
     * @param channelsBuffer 頻道集合(用於存放)
     */
    private void loadDefaultChannel(final @NotNull List<Channel> channelsBuffer) {
        final @Nullable String defaultChannelName = this.configuration.getString(CHANNELS_PATH_DEFAULT_CHANNELS);

        if (defaultChannelName != null) {
            final @Nullable SimpleChannel defaultChannel = ChannelSettings.getChannel(defaultChannelName);

            if (defaultChannel != null)
                channelsBuffer.add(new DefaultChannelWrapper(defaultChannel));

            // 在控制台印出錯誤訊息
            else
                this.logErrorChannel(defaultChannelName);
        }
    }

    /**
     * 紀錄頻道沒有找到訊息
     * @param channelName 頻道頻稱
     */
    private void logErrorChannel(final @NotNull String channelName) {
        MouBieCat.getInstance().getDebugger().warning(
                "§e在插件中讀取到頻道 §6" + channelName + " §e，但是卻在 ChatControl 找不到該該頻道名稱。為了避免例外的狀況，因此跳過該頻道的處理。"
        );
    }

}
