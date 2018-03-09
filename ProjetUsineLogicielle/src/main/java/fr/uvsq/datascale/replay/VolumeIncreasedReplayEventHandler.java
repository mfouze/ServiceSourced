package fr.uvsq.datascale.replay;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.springframework.stereotype.Component;


import fr.uvsq.datascale.api.event.VolumeDecreasedEvent;
import fr.uvsq.datascale.api.event.VolumeIncreasedEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class VolumeIncreasedReplayEventHandler implements ReplayAware {

    List<String> audit = new ArrayList<>();

    @EventHandler
    public void handle(VolumeIncreasedEvent event) {
        String auditMsg = String.format("%s Increased to volume with diplome no {%s} on %s",
                event.getVolumeIncreased(), event.getIdDiplome(), formatTimestampToString(event.getTimeStamp()));
        audit.add(auditMsg);
    }

    @EventHandler
    public void handle(VolumeDecreasedEvent event) {
        String auditMsg = String.format("%s Deacreased from volume with diplome no {%s} on %s",
                event.getVolumeDecreased(), event.getIdDiplome(), formatTimestampToString(event.getTimeStamp()));
        audit.add(auditMsg);
    }

    public List<String> getAudit() {
        return audit;
    }

    @Override
    public void beforeReplay() {
        audit.clear();
    }

    @Override
    public void afterReplay() {
    }

    @Override
    public void onReplayFailed(Throwable cause) {}

    private String formatTimestampToString(long timestamp) {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp * 1000);
    }
}
