package fr.uvsq.datascale.api.command;

public class DecreaseVolumeCommand {
	private final String diplome;
    private final int volume;
	public String getDiplome() {
		return diplome;
	}
	public int getVolume() {
		return volume;
	}
	public DecreaseVolumeCommand(String diplome, int volume) {
		super();
		this.diplome = diplome;
		this.volume = volume;
	}

}
