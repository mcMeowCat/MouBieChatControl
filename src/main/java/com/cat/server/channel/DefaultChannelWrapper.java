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

import com.cat.server.channel.api.DefaultChannel;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.chatcontrol.model.SimpleChannel;

/**
 * 代表一個預設發送訊息的頻道包裝器
 * @author MouBieCat
 */
public final class DefaultChannelWrapper
        extends ChannelWrapper
        implements DefaultChannel {

    /**
     * 建構子
     * @param simpleChannel 頻道
     */
    public DefaultChannelWrapper(final @NotNull SimpleChannel simpleChannel) {
        super("default", simpleChannel);
    }

    /**
     * 檢查該訊息是否為該快捷鍵
     * @param prefix 快捷鍵符號
     * @return 預設頻道一律為true
     */
    @Override
    public boolean checkPrefix(final @NotNull String prefix) {
        return true;
    }

}
