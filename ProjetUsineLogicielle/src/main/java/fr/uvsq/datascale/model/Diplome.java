package fr.uvsq.datascale.model;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.h2.util.New;

import fr.uvsq.datascale.api.command.DecreaseVolumeCommand;
import fr.uvsq.datascale.api.command.IncreaseVolumeCommand;
import fr.uvsq.datascale.api.event.DiplomeCreatedEvent;
import fr.uvsq.datascale.api.event.VolumeDecreasedEvent;
import fr.uvsq.datascale.api.event.VolumeIncreasedEvent;

public class Diplome extends AbstractAnnotatedAggregateRoot {

	private static final long serialVersionUID = 3503481380350385846L;

	@AggregateIdentifier
	private String idDiplome;
	
	private int volumehoraire;

	public Diplome(String idDiplome) {
		apply(new DiplomeCreatedEvent(idDiplome));
	}
	

	public Diplome() {
		super();
		// TODO Auto-generated constructor stub
	}


	@EventSourcingHandler
	public void applyDiplomeCreation(DiplomeCreatedEvent event) {
		this.idDiplome = event.getIdDiplome();
		this.volumehoraire = 0;

	}
	
	
	
	public void decrease(int volAsoustraire) {
		if(Integer.compare(volAsoustraire, 0) > 0 &&
                this.volumehoraire - volAsoustraire > -1) {
			apply(new VolumeDecreasedEvent(this.idDiplome, volAsoustraire, this.volumehoraire));
		}else {
            throw new IllegalArgumentException("Ne peut pas diminuer le volume");
        }

	}

	@EventSourcingHandler
	private void applyDecrease(VolumeDecreasedEvent event) {

		this.volumehoraire-= event.getCurVolume();
	}
	
	
	public void increase(int increaseVolume) {
		if(Integer.compare(increaseVolume, 0) >0 
				&& Integer.compare(increaseVolume, 500)<0){
			apply(new VolumeIncreasedEvent(this.idDiplome,increaseVolume, this.volumehoraire));
			
		} else {
            throw new IllegalArgumentException("Nombre d'heure trop eleve");
        }
	}
	
    @EventSourcingHandler
    private void applyincrease(VolumeIncreasedEvent event) {
      
        this.volumehoraire += event.getVolumeIncreased();
    }
    
    public int getVolume(){
    	return volumehoraire;
    }
    
    public void setIdentifier(String id) {
        this.idDiplome = id;
    }
    @Override
    public Object getIdentifier() {
        return idDiplome;
    }
	
	

}
