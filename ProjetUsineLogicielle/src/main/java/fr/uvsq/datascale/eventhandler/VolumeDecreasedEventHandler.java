package fr.uvsq.datascale.eventhandler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.uvsq.datascale.api.event.VolumeDecreasedEvent;

import javax.sql.DataSource;


@Component
public class VolumeDecreasedEventHandler {

    @Autowired
    DataSource dataSource;

    @EventHandler
    public void handleVolumeDeacreasedEvent(VolumeDecreasedEvent event) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Get the current states as reflected in the event
        String idDiplome = event.getIdDiplome();
        int  curVol = event.getCurVolume();
        int volDegrease = event.getVolumeDecreased();
        int newVol = curVol -volDegrease;

        // Update the view
        String updateQuery = "UPDATE diplome_view SET curVol = ? WHERE dilome_id = ?";
        jdbcTemplate.update(updateQuery, new Object[]{newVol, idDiplome});
    }
}
