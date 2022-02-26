package com.cat.server;

import com.cat.server.channel.ChannelLoader;
import com.cat.server.channel.ChannelManager;
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
    private final ChannelManager manager = new ChannelManager();

    @PluginRegister(name = "註冊插件檔案", type = PluginRegister.ActionType.ACTION_ENABLE, priority = PluginRegister.ActionPriority.HIGHEST)
    public void registerFiles() {
        // 頻道加載器
        ChannelLoader loader = new ChannelLoader();
        this.manager.loadChannels(loader);
    }

    @PluginRegister(name = "註冊插件事件", type = PluginRegister.ActionType.ACTION_ENABLE)
    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(), this);
    }


    @PluginRegister(name = "重載插件檔案", type = PluginRegister.ActionType.ACTION_RELOAD)
    public void reloadFiles() {
        this.registerFiles();
    }


    /**
     * 獲取頻道管理器
     * @return 管理器
     */
    @NotNull
    public ChannelManager getChannelManager() {
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
