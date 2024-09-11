package models.baseModels;


import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, представляющий музыкальный лейбл.
 */
public class Label implements Serializable {
    private Long bands;

    public Label(Long bands) throws NullPointerException {
        this.bands = Objects.requireNonNull(bands, "Field cannot be null");
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bands", this.bands);
        return json;
    }

    public Long getBands() {
        return bands;
    }
}
