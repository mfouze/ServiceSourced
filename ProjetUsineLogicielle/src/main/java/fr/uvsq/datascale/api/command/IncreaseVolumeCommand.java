package fr.uvsq.datascale.api.command;

public class IncreaseVolumeCommand {
	
	private final String diplome;
    private final int volume;
	public String getDiplome() {
		return diplome;
	}
	public int getVolume() {
		return volume;
	}
	public IncreaseVolumeCommand(String diplome, int volume) {
		super();
		this.diplome = diplome;
		this.volume = volume;
	}
    
    

}
