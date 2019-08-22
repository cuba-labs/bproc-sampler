package com.company.bps.service;

public interface FailedJobDemoService {
    String NAME = "bps_FailedJobDemoService";

    void methodThatFails(boolean fail);
}