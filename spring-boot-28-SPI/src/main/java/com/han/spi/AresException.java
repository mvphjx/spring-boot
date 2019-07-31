package com.han.spi;

public abstract class AresException extends Exception
{
    /**
     * Construct a new Ares exception.
     *
     * @param message the exception message
     */
    public AresException(String message)
    {
        super(message);
    }

    /**
     * Construct a new Ares exception.
     *
     * @param message the exception message
     * @param cause   the cause
     */
    public AresException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
