package managers.Collection;

import models.baseModels.MusicBand;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class MusicBandCollectionManager {
    private Map<Long, MusicBand> musicBands;
    private final Date initializationDate;

    public MusicBandCollectionManager(){
        this.musicBands = Collections.synchronizedMap(new TreeMap<>());
        this.initializationDate = new Date();
    }

    public Date getInitializationDate() {
        return initializationDate;
    }

    public void updateCollection(TreeMap<Long, MusicBand> newMusicBands){
        this.musicBands = newMusicBands;
    }

    public Map<Long, MusicBand> getMusicBands() {
        return musicBands;
    }

    public void put(Long key, MusicBand musicBand){
        //gen key
        musicBands.put(key, musicBand);
    }

    public void remove(Long key) {
        musicBands.remove(key);
    }
}