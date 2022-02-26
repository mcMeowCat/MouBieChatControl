package com.cat.server.channel;

import com.cat.server.MouBieCat;
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
    public List<MouBieChannel> parsePrefixChannels() {
        final List<MouBieChannel> channels = new LinkedList<>();

        final @Nullable ConfigurationSection configurationSection = this.configuration.getConfigurationSection("Channels");
        if (configurationSection == null)
            return channels;

        for (final String prefix : configurationSection.getKeys(false)) {
            final String channelName = this.getString("Channels." + prefix);
            final SimpleChannel simpleChannel = ChannelSettings.getChannel(channelName);
            channels.add(new MouBieChannel(prefix, simpleChannel));
        }

        return channels;
    }

}
