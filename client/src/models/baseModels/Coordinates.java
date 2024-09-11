package models.baseModels;



import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, представляющий координаты.
 */
public class Coordinates implements Serializable {
    private Float x;
    private Integer y;

    public Coordinates(Float x, Integer y){
        if (x < 71) {
            this.x = Objects.requireNonNull(x, "Field cannot be null");;
        }
        this.y = Objects.requireNonNull(y, "Field cannot be null");
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("x", this.x);
        json.put("y", this.y);
        return json;
    }

    public Float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
