package exceptions;

/**
 * Исключение, возникающее при обнаружении нулевого значения в поле, которое должно содержать данные.
 */
public class FieldNullException extends Exception {
    /**
     * Конструктор класса.
     *
     * @param message сообщение об ошибке
     */
    public FieldNullException(String message) {
        super(message);
    }
    /**
     * Переопределенный метод getMessage для получения сообщения об ошибке.
     *
     * @return сообщение об ошибке
     */
    @Override
    public String getMessage(){
        return "The field that should contain the data contains null.";
    }
}
