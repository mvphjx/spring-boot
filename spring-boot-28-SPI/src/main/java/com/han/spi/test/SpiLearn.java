package com.han.spi.test;

import org.springframework.core.convert.support.DefaultConversionService;

public class SpiLearn
{
    public static void main(String[] args)
    {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        Integer convert = defaultConversionService.convert("3", Integer.class);
        System.out.println(convert);
    }
}
