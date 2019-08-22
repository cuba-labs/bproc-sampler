package com.company.bps.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.List;

@NamePattern("%s|title")
@MetaClass(name = "bps_SampleItem")
public class SampleItem extends BaseUuidEntity {
    private static final long serialVersionUID = -466128608436284594L;

    @MetaProperty
    protected String title;

    @MetaProperty
    protected String processDefinitionKey;

    @MetaProperty
    protected String descriptionFile;

    @MetaProperty
    protected List<SampleFile> files;

    @MetaProperty
    protected List<DocumentationLink> documentationLinks;

    public List<SampleFile> getFiles() {
        return files;
    }

    public void setFiles(List<SampleFile> files) {
        this.files = files;
    }

    public String getDescriptionFile() {
        return descriptionFile;
    }

    public void setDescriptionFile(String descriptionFilePath) {
        this.descriptionFile = descriptionFilePath;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DocumentationLink> getDocumentationLinks() {
        return documentationLinks;
    }

    public void setDocumentationLinks(List<DocumentationLink> documentationLinks) {
        this.documentationLinks = documentationLinks;
    }
}