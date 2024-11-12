package game.exceptions;

/**
 * A class for InsufficientBalanceException
 * @Author: Quan Hong
 */
public class InsufficientBalanceException extends Exception {
    /**
     * Constructor for InsufficientBalanceException
     * @param message
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
