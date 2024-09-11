package exceptions;
/**
 * Исключение, возникающее при ошибке вывода данных в консоль.
 */
public class ConsoleOutputErrorException extends Exception{
    /**
     * Конструктор класса.
     *
     * @param description описание ошибки
     */
    public ConsoleOutputErrorException(String description) {
        super(description);
    }
    /**
     * Переопределенный метод getMessage для получения сообщения об ошибке.
     *
     * @return сообщение об ошибке
     */
    @Override
    public String getMessage(){
        return "Error when outputting data to the console.";
    }
}
