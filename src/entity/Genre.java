package entity;

import java.util.HashMap;
import java.util.Map;

public class Genre {
    private HashMap<String, Integer> genre = new HashMap<>();

    public void setGenreMap(HashMap<String, Integer> genre) {
        this.genre = genre;
    }

    public HashMap<String, Integer> getGenreMap() {
        return genre;
    }
}
