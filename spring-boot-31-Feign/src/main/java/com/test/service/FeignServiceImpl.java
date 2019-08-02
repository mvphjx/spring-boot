package com.test.service;

import org.springframework.stereotype.Component;

@Component
public class FeignServiceImpl implements FeignService
{

    @Override
    public String findById()
    {
        return "FeignServiceImpl 快速失败";
    }
}
