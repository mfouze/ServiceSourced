package fr.uvsq.datascale.commandhandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import fr.uvsq.datascale.api.command.DecreaseVolumeCommand;
import fr.uvsq.datascale.model.Diplome;

public class DecreaseVolumeHandler {
    private Repository repository;

		@Autowired
		public DecreaseVolumeHandler(Repository repository) {
			this.repository = repository;
		}

		@CommandHandler
		public void handle(DecreaseVolumeCommand decreaseVolumeCommand){
			Diplome diplome = (Diplome) repository.load(decreaseVolumeCommand.getDiplome());
			diplome.decrease(decreaseVolumeCommand.getVolume());
		}

}
