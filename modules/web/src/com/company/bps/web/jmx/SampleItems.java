package com.company.bps.web.jmx;

import com.company.bps.web.config.SampleItemsLoader;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("bps_SampleItemsMBean")
public class SampleItems implements SampleItemsMBean {

    @Inject
    private SampleItemsLoader sampleItemsLoader;

    @Override
    public void reset() {
        sampleItemsLoader.reset();
    }
}