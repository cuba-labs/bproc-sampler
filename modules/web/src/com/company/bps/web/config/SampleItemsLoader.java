package com.company.bps.web.config;

import com.company.bps.entity.DocumentationLink;
import com.company.bps.entity.SampleFile;
import com.company.bps.entity.SampleItem;
import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Resources;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component("bps_SampleItemsLoader")
public class SampleItemsLoader {

    private volatile boolean initialized;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Inject
    private Resources resources;

    @Inject
    private Metadata metadata;

    @Inject
    private Logger log;

    private List<SampleItem> sampleItems = new ArrayList<>();

    private void checkInitialized() {
        if (!initialized) {
            lock.readLock().unlock();
            lock.writeLock().lock();
            try {
                if (!initialized) {
                    init();
                    initialized = true;
                }
            } finally {
                lock.readLock().lock();
                lock.writeLock().unlock();
            }
        }
    }

    public List<SampleItem> getSampleItems() {
        lock.readLock().lock();
        try {
            checkInitialized();
            return Collections.unmodifiableList(sampleItems);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void init() {
        sampleItems.clear();
        String sampleItemsConfigLocation = "com/company/bps/samples-config.xml";
        Resource resource = resources.getResource(sampleItemsConfigLocation);
        if (resource.exists()) {
            try (InputStream stream = resource.getInputStream()) {
                Element rootElement = Dom4j.readDocument(stream).getRootElement();
                for (Element sampleItemElement : rootElement.elements("sample-item")) {
                    SampleItem sampleItem = metadata.create(SampleItem.class);
                    sampleItem.setProcessDefinitionKey(sampleItemElement.attributeValue("processDefinitionKey"));
                    sampleItem.setTitle(sampleItemElement.attributeValue("title"));
                    sampleItem.setDescriptionFile(sampleItemElement.attributeValue("descriptionFile"));

                    sampleItem.setFiles(new ArrayList<>());
                    Element filesElement = sampleItemElement.element("files");
                    if (filesElement != null) {
                        for (Element fileElement : filesElement.elements("file")) {
                            SampleFile sampleFile = metadata.create(SampleFile.class);
                            sampleFile.setPath(fileElement.attributeValue("path"));
                            sampleItem.getFiles().add(sampleFile);
                        }
                    }

                    sampleItem.setDocumentationLinks(new ArrayList<>());
                    Element documentationLinks = sampleItemElement.element("documentationLinks");
                    if (documentationLinks != null) {
                        for (Element documentationLinkElement : documentationLinks.elements("documentationLink")) {
                            DocumentationLink documentationLink = metadata.create(DocumentationLink.class);
                            documentationLink.setUrlSuffix(documentationLinkElement.attributeValue("urlSuffix"));
                            documentationLink.setCaption(documentationLinkElement.attributeValue("caption"));
                            sampleItem.getDocumentationLinks().add(documentationLink);
                        }
                    }

                    sampleItems.add(sampleItem);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to read sample items config from " + sampleItemsConfigLocation, e);
            }
        } else {
            log.error("Resource " + sampleItemsConfigLocation + " not found");
        }
    }

    public void reset() {
        initialized = false;
    }
}
