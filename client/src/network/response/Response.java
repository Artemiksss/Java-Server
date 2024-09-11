package network.response;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 88L;
    private String message;

    public String getMessage() {
        return message;
    }
}
