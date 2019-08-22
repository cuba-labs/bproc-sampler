package com.company.bps.web.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

@Source(type = SourceType.DATABASE)
public interface BprocSamplesConfig extends Config {

    @Property("bprocSamples.documentationUrl")
    @Default("https://doc.cuba-platform.com/bproc-1.0/")
    String getDocumentationUrl();
}
