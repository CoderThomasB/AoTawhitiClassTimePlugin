package CoderThomasB.AoTawhitiClassTimePlugin;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.time.LocalDateTime;

public class MainListener implements Listener {
    private final AoTawhitiClassTimePlugin Owner;

    public MainListener(AoTawhitiClassTimePlugin MyPlugin) {
        Owner = MyPlugin;
    }

//    @EventHandler
//    public void onPreLoginEvent(PreLoginEvent event) {
//        Owner.DisconnectPlayerIfInClass(event.getConnection());
//    }

    @EventHandler
    public void onPostLoginEvent(PostLoginEvent event) {
        Owner.DisconnectPlayerIfInClass(event.getPlayer(), TimeBlock.GetBlockAtTime(LocalDateTime.now()));

        Owner.ClassBossBarIndicator.setAction(0);
        event.getPlayer().unsafe().sendPacket(Owner.ClassBossBarIndicator);

        if(Owner.IsServerShutdownBossBarIndicatorVisible) {
            Owner.ServerShutdownBossBarIndicator.setAction(0);
            event.getPlayer().unsafe().sendPacket(Owner.ServerShutdownBossBarIndicator);
        }
    }
}
