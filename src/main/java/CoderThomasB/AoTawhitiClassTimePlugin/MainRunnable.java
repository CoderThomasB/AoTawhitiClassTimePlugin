package CoderThomasB.AoTawhitiClassTimePlugin;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

class MainRunnable implements Runnable {
    AoTawhitiClassTimePlugin Owner;

    public MainRunnable(AoTawhitiClassTimePlugin MyPlugin) {
        Owner = MyPlugin;
    }

    public void ScheduleNextRun() {
        Owner.getProxy().getScheduler().schedule(Owner, this, 1, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        TimeBlock TimeBlockNow = TimeBlock.GetBlockAtTime(LocalDateTime.now());
        TimeBlock TimeBlockNext = TimeBlock.GetBlockAtTime(LocalDateTime.now().plusMinutes(10));

        Owner.ClassBossBarIndicator.setTitle(String.format("[\"\",{\"text\":\"Now Happening is \"},{\"text\":\"%s\",\"color\":\"%s\"}]", TimeBlockNow.toDisplayString(), TimeBlockNow.GetRawTestColor()));
        Owner.ClassBossBarIndicator.setColor(TimeBlockNow.GetBossBarColor());

        if (TimeBlockNext != TimeBlockNow) {
            Owner.ClassBossBarIndicator.setTitle(String.format("[\"\",{\"text\":\"Now Happening is \"},{\"text\":\"%s\",\"color\":\"%s\"},{\"text\":\", Next Up is \"},{\"text\":\"%s\",\"color\":\"%s\"}]",
                    TimeBlockNow.toDisplayString(), TimeBlockNow.GetRawTestColor(),
                    TimeBlockNext.toDisplayString(), TimeBlockNext.GetRawTestColor()
                    )
            );
        }

        for (ProxiedPlayer Player : Owner.getProxy().getPlayers()) {
            Owner.DisconnectPlayerIfInClass(Player, TimeBlockNow);
            Owner.UpdateTitleAndColorOfBossBarForPlayer(Player, Owner.ClassBossBarIndicator);
        }

        if(TimeBlockNow.CanPlayAtTime() && !TimeBlockNext.CanPlayAtTime() && !Owner.IsServerShutdownBossBarIndicatorVisible){
            for (ProxiedPlayer Player : Owner.getProxy().getPlayers()) {
                Owner.ServerShutdownBossBarIndicator.setAction(0);
                Player.unsafe().sendPacket(Owner.ServerShutdownBossBarIndicator);
                Player.sendMessage("The Server is shutting down in the next 10m!");
            }
            Owner.IsServerShutdownBossBarIndicatorVisible = true;
        }

        if(!TimeBlockNow.CanPlayAtTime() || TimeBlockNext.CanPlayAtTime() && Owner.IsServerShutdownBossBarIndicatorVisible){
            for (ProxiedPlayer Player : Owner.getProxy().getPlayers()) {
                Owner.ServerShutdownBossBarIndicator.setAction(1);
                Player.unsafe().sendPacket(Owner.ServerShutdownBossBarIndicator);
            }
            Owner.IsServerShutdownBossBarIndicatorVisible = false;
        }

        ScheduleNextRun();
    }

}
