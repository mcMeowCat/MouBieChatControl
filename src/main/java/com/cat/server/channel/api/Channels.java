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

import com.cat.server.ChannelLoader;
import com.moubieapi.api.manager.Manager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 代表頻道管理紀錄器
 * @author MouBieCat
 */
public interface Channels
        extends Manager<String, Channel> {

    /**
     * 根據檔案內容加載頻道
     * @param loader 加載器
     */
    void loadChannels(@NotNull ChannelLoader loader);

    /**
     * 獲取預設發言的頻道
     * @return 預設頻道
     */
    @Nullable DefaultChannel getDefaultChannel();

}
