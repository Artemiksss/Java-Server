package models.baseModels;

import java.io.Serializable;

/**
 * Перечисление, представляющее различные музыкальные жанры.
 */
public enum MusicGenre implements Serializable {
    ROCK("ROCK"),
    BLUES("BLUES"),
    POP("POP"),
    POST_ROCK("POST_ROCK"),
    BRIT_POP("BRIT_POP");

    private String genre;

    MusicGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public static MusicGenre convert(String genre) {
        for (MusicGenre musicGenre : MusicGenre.values()) {
            if (musicGenre.getGenre().equalsIgnoreCase(genre)) {
                return musicGenre;
            }
        }
        return null;
    }
}
