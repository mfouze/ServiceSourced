package fr.uvsq.datascale.api.event;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VolumeIncreasedEvent {
	private final String idDiplome;
	private final int volumeIncreased ;
	private final int curVolume;
	private final long timeStamp;
	public VolumeIncreasedEvent(String idDiplome, int volumeIncreased, int curVolume) {
		super();
		this.idDiplome = idDiplome;
		this.volumeIncreased = volumeIncreased;
		this.curVolume = curVolume;
		ZoneId zoneId = ZoneId.systemDefault();
		this.timeStamp = LocalDateTime.now().atZone(zoneId).toEpochSecond();

	}
	public String getIdDiplome() {
		return idDiplome;
	}
	public int getVolumeIncreased() {
		return volumeIncreased;
	}
	public int getCurVolume() {
		return curVolume;
	}
	public long getTimeStamp() {
		return timeStamp;
	}

}
