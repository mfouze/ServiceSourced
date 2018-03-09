package fr.uvsq.datascale.eventhandler;

import org.axonframework.domain.Message;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import fr.uvsq.datascale.api.event.VolumeIncreasedEvent;

import javax.sql.DataSource;


@Component
public class VolumeIncreasedEventHandler {

    @Autowired
    DataSource dataSource;

    @EventHandler
    public void handleVolumeIncreasedEvent(VolumeIncreasedEvent event, Message eventMessage, @Timestamp DateTime moment) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Get the current states as reflected in the event
        String idDiplome = event.getIdDiplome();
        int curVolume = event.getCurVolume();
        int volIncreased = event.getVolumeIncreased();
        int newVolume =curVolume+volIncreased;

        // Update the view
        String updateQuery = "UPDATE diplome_view SET curVolume = ? WHERE diplome_no = ?";
        jdbcTemplate.update(updateQuery, new Object[]{newVolume, idDiplome});

        System.out.println("Events Handled With EventMessage " + eventMessage.toString() + " at " + moment.toString());
    }

}
