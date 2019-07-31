package com.han.spi;

import com.han.spi.data.AresServiceInfo;

public class AresFailedException extends AresException
{
    private final AresServiceInfo aresServiceInfo;
    /**
     * Create a new conversion exception.
     * @param aresServiceInfo
     * @param cause the cause of the conversion failure
     */
    public AresFailedException(AresServiceInfo aresServiceInfo, Throwable cause) {
        super("Failed to XXX in "+aresServiceInfo);
        this.aresServiceInfo = aresServiceInfo;
    }
}
