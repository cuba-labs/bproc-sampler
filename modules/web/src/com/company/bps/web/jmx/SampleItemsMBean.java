package com.company.bps.web.jmx;

import com.haulmont.cuba.core.sys.jmx.JmxBean;

@JmxBean(module = "bproc-sampler", alias = "SampleItemsMBean")
public interface SampleItemsMBean {
    void reset();
}
