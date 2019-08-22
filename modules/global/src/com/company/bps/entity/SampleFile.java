package com.company.bps.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "bps_SampleFile")
public class SampleFile extends BaseUuidEntity {
    private static final long serialVersionUID = -5886403697947627577L;

    @MetaProperty
    protected String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}