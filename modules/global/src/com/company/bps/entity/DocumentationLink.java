package com.company.bps.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@NamePattern("%s|caption")
@MetaClass(name = "bps_DocumentationLink")
public class DocumentationLink extends BaseUuidEntity {
    private static final long serialVersionUID = -160578892456023316L;

    @MetaProperty
    protected String urlSuffix;

    @MetaProperty
    protected String caption;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrlSuffix() {
        return urlSuffix;
    }

    public void setUrlSuffix(String urlSuffix) {
        this.urlSuffix = urlSuffix;
    }
}