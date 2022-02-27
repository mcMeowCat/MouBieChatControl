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

import com.cat.server.MouBieCat;
import com.moubieapi.moubieapi.yaml.Loader;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.chatcontrol.settings.ChannelSettings;

import java.util.LinkedList;
import java.util.List;

/**
 * 頻道檔案加載器
 * @author MouBieCat
 */
public final class ChannelLoader extends Loader {

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
    public List<Channel> parsePrefixChannels() {
        final @Nullable ConfigurationSection configurationSection = this.configuration.getConfigurationSection("Channels");
        if (configurationSection == null)
            return new LinkedList<>();

        final List<Channel> channels = new LinkedList<>();

        for (final String prefix : configurationSection.getKeys(false)) {
            final String channelName = this.getString("Channels." + prefix);
            channels.add(new ChannelWrapper(prefix, ChannelSettings.getChannel(channelName)));
        }

        return channels;
    }

}
