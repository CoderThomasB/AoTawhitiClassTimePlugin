package CoderThomasB.AoTawhitiClassTimePlugin;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.protocol.packet.BossBar;
import org.bstats.bungeecord.Metrics;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AoTawhitiClassTimePlugin extends Plugin {
    Configuration MainConfiguration;
    File MainConfigurationFile;
    MainRunnable TheMainRunnable;
    MainListener TheListener;
    BossBar ClassBossBarIndicator;
    BossBar ServerShutdownBossBarIndicator;
    boolean IsServerShutdownBossBarIndicatorVisible = false;

    @Override
    public void onEnable() {
        InitConfiguration();

        ClassBossBarIndicator = new BossBar(UUID.randomUUID(), 0);
        ClassBossBarIndicator.setHealth(1f);
        ClassBossBarIndicator.setDivision(0); // Solid

        ServerShutdownBossBarIndicator = new BossBar(UUID.randomUUID(), 0);
        ServerShutdownBossBarIndicator.setHealth(1f);
        ServerShutdownBossBarIndicator.setDivision(0); // Solid
        ServerShutdownBossBarIndicator.setTitle("{\"text\":\"The Server is shutting down in the next 10m!\",\"bold\":true,\"color\":\"red\"}");
        ServerShutdownBossBarIndicator.setColor(2);

        TheListener = new MainListener(this);
        getProxy().getPluginManager().registerListener(this, TheListener);

        TheMainRunnable = new MainRunnable(this);
        TheMainRunnable.ScheduleNextRun();

        CheckPermissions();

        int pluginId = 11228;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public void CheckPermissions() {
        boolean IsIgnoreDisconnectInAdmin = false;
        for (String Permission : getProxy().getConfigurationAdapter().getPermissions("admin")) {
            if (Permission.equals("AoTawhitiClassTime.IgnoreDisconnect")) {
                IsIgnoreDisconnectInAdmin = true;
                break;
            }
        }
        if (!IsIgnoreDisconnectInAdmin) {
            getLogger().warning("The AoTawhitiClassTime.IgnoreDisconnect Permission is not assigned to the admin group! admins will get kicked from the server!");
        }
    }

    public void InitConfiguration() {
        MainConfigurationFile = new File(getDataFolder(), "config.yml");

        if (!getDataFolder().exists())
            //noinspection ResultOfMethodCallIgnored
            getDataFolder().mkdir();

        if (!MainConfigurationFile.exists()) {
            try (InputStream in = getResourceAsStream("DefaultConfig.yml")) {
                Files.copy(in, MainConfigurationFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            MainConfiguration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(MainConfigurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info(MainConfiguration.toString());
    }

    public void DisconnectPlayerIfInClass(ProxiedPlayer Player, TimeBlock TimeBlockNow) {
        // don't disconnect them if it's not the right time!
        if (TimeBlockNow.CanPlayAtTime()) return;

        // if the player has the correct permissions then we don't disconnect them
        if (Player.hasPermission("AoTawhitiClassTime.IgnoreDisconnect")) return;

        getLogger().info("Trying To kick player: " + Player.getDisplayName());
        Player.disconnect(new TextComponent("You are not allowed play on the Ao Tawhiti Minecraft Server in class Time!\n\nIf you are using the server for an SDL or need access to it in this time pleases email thomas.booker@aotawhiti.school.nz"));
    }

    public void UpdateTitleAndColorOfBossBarForPlayer(ProxiedPlayer Player, BossBar BossBar) {
        // We need to stagger the packets as the sending proses takes time

        getProxy().getScheduler().schedule(this, () -> {
            // 4 is update style (color and division)
            //                \/
            BossBar.setAction(4);
            Player.unsafe().sendPacket(BossBar);
        }, 3, TimeUnit.MILLISECONDS);

        getProxy().getScheduler().schedule(this, () -> {
            //          3 is update name
            //                \/
            BossBar.setAction(3);
            Player.unsafe().sendPacket(BossBar);
        }, 1, TimeUnit.MILLISECONDS);
    }
}
