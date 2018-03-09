package fr.uvsq.datascale.api.event;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VolumeDecreasedEvent {
	private final String idDiplome;
	private final int volumeDecreased ;
	private final int curVolume;
	private final long timeStamp;

	public String getIdDiplome() {
		return idDiplome;
	}
	public VolumeDecreasedEvent(String idDiplome, int volumeDecreased, int curVolume) {
		super();
		this.idDiplome = idDiplome;
		this.volumeDecreased = volumeDecreased;
		this.curVolume = curVolume;
		ZoneId zoneId = ZoneId.systemDefault();
		this.timeStamp = LocalDateTime.now().atZone(zoneId).toEpochSecond();
	}
	public int getVolumeDecreased() {
		return volumeDecreased;
	}
	public int getCurVolume() {
		return curVolume;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	

}
