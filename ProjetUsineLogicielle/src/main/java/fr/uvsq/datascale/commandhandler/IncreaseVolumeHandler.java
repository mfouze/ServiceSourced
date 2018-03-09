package fr.uvsq.datascale.commandhandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.uvsq.datascale.api.command.IncreaseVolumeCommand;
import fr.uvsq.datascale.model.Diplome;
@Component
public class IncreaseVolumeHandler {

    private Repository repository;

		@Autowired
		public IncreaseVolumeHandler(Repository repository) {
			this.repository = repository;
		}

		@CommandHandler
		public void handle(IncreaseVolumeCommand increaseVolumeCommand){
			Diplome diplome = (Diplome) repository.load(increaseVolumeCommand.getDiplome());
			diplome.increase(increaseVolumeCommand.getVolume());
		}
	}
