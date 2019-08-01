package com.han.spi;

import com.han.spi.data.AresMessageObject;
import com.han.spi.data.AresServiceInfo;

public interface IAresBaseService
{
    AresServiceInfo getServiceInfo();

    AresMessageObject search(String systemId, String dataType, String subDBName);

}
