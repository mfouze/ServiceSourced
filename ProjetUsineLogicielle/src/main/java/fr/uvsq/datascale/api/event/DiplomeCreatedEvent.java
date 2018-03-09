package fr.uvsq.datascale.api.event;

public class DiplomeCreatedEvent {
	 private final String idDiplome;

	public DiplomeCreatedEvent(String idDiplome) {
		super();
		this.idDiplome = idDiplome;
	}

	public String getIdDiplome() {
		return idDiplome;
	}
	 

}
