package com.company.bps.service;

import org.springframework.stereotype.Service;

@Service(FailedJobDemoService.NAME)
public class FailedJobDemoServiceBean implements FailedJobDemoService {

    @Override
    public void methodThatFails(boolean fail) {
        if (fail) throw new RuntimeException("Method failed!");
    }
}