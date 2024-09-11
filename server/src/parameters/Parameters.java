package parameters;

import models.baseModels.MusicBand;

import java.io.Serializable;

public class Parameters implements Serializable {
    private String musicBandId;
    private String key;
    private String parameter;
    private MusicBand musicBand;

    public Parameters(){}
    public Parameters(String parameter, MusicBand musicBand){
        this.parameter = parameter;
        this.musicBand = musicBand;
    }

    public Parameters(String parameter){
        this.parameter = parameter;
    }
    public Parameters(MusicBand musicBand){
        this.musicBand = musicBand;
    }
    public Parameters(MusicBand musicBand, String key){
        this.musicBand = musicBand;
        this.key = key;
    }

    public String getParameter() {
        return parameter;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }
    public MusicBand getMusicBandWithUserId(Long userId) {
        this.musicBand.setUserId(userId);
        return this.musicBand;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMusicBandId() {
        return musicBandId;
    }

    public void setMusicBandId(String music_band_id) {
        this.musicBandId = music_band_id;
    }
}
