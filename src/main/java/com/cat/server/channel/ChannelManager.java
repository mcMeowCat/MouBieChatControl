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

import com.cat.server.ChannelLoader;
import com.cat.server.channel.api.Channel;
import com.cat.server.channel.api.Channels;
import com.cat.server.channel.api.DefaultChannel;
import com.moubieapi.moubieapi.manager.ManagerAbstract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表頻道管理紀錄器
 * @author MouBieCat
 */
public final class ChannelManager
        extends ManagerAbstract<String, Channel>
        implements Channels {

    @Nullable
    private DefaultChannel defaultChannel;

    /**
     * 根據檔案內容加載頻道
     * @param loader 加載器
     */
    public void loadChannels(final @NotNull ChannelLoader loader) {
        this.manager.clear();
        this.defaultChannel = null;

        for (final Channel channel : loader.parseChannels()) {
            // 如果為預設頻道，則另外處理
            if (channel instanceof DefaultChannel)
                this.defaultChannel = (DefaultChannel) channel;

            // 一般具有快捷頻道的就進行一般方式處理
            else
                this.add(channel.getPrefix(), channel);
        }
    }

    /**
     * 獲取預設發言的頻道
     * @return 預設頻道
     */
    @Nullable
    public DefaultChannel getDefaultChannel() {
        return this.defaultChannel;
    }

}
