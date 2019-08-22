package com.company.bps.web.screens.sampler;

import com.company.bps.entity.DocumentationLink;
import com.company.bps.entity.SampleItem;
import com.company.bps.web.config.BprocSamplesConfig;
import com.company.bps.web.config.SampleItemsLoader;
import com.haulmont.addon.bproc.entity.ProcessDefinitionData;
import com.haulmont.addon.bproc.service.BprocRepositoryService;
import com.haulmont.addon.bproc.web.processform.ProcessFormScreens;
import com.haulmont.addon.bproc.web.screens.modeler.ModelerScreen;
import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.widgets.CubaSourceCodeEditor;
import com.haulmont.cuba.web.widgets.addons.aceeditor.AceMode;

import javax.inject.Inject;

@UiController("bps_SamplerScreen")
@UiDescriptor("sampler-screen.xml")
public class SamplerScreen extends Screen {

    @Inject
    private Table<SampleItem> sampleItemsTable;

    @Inject
    private CollectionContainer<SampleItem> sampleItemsDc;

    @Inject
    private InstanceContainer<SampleItem> sampleItemDc;

    @Inject
    private SampleItemsLoader sampleItemsConfig;

    @Inject
    private VBoxLayout sampleItemPanel;

    @Inject
    private UiComponents uiComponents;

    @Inject
    private TabSheet sampleItemTabSheet;


    @Inject
    private ProcessFormScreens processFormScreens;

    @Inject
    private BprocRepositoryService bprocRepositoryService;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private BprocSamplesConfig bprocSamplesConfig;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private Resources resources;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        initSampleItemsTree();
        sampleItemsTable.setSelected(sampleItemsDc.getMutableItems().get(0));

    }

    protected void initSampleItemsTree() {
        sampleItemsDc.getMutableItems().addAll(sampleItemsConfig.getSampleItems());
    }

    @Subscribe(id = "sampleItemsDc", target = Target.DATA_CONTAINER)
    private void onSampleItemsDcItemChange(InstanceContainer.ItemChangeEvent<SampleItem> event) {
        SampleItem sampleItem = event.getItem();
        sampleItemDc.setItem(sampleItem);

        initTabSheet(sampleItem);
    }

    private void initTabSheet(SampleItem sampleItem) {
        sampleItemTabSheet.removeAllTabs();
        sampleItemTabSheet.addTab("Description", createDescriptionContainer(sampleItem));
        sampleItem.getFiles()
                .forEach(sampleFile -> {
                    String path = sampleFile.getPath();
                    String fileName = path.substring(path.lastIndexOf("/") + 1);
                    String fileContent = resources.getResourceAsString(sampleFile.getPath());
                    SourceCodeEditor sourceCodeEditor = createSourceCodeEditor(AceMode.forFile(fileName));
                    sourceCodeEditor.setValue(fileContent);
                    sampleItemTabSheet.addTab(fileName, sourceCodeEditor);
                });
    }

    private Component createDescriptionContainer(SampleItem sampleItem) {
        VBoxLayout vBoxLayout = uiComponents.create(VBoxLayout.class);
        vBoxLayout.setHeight("100%");
        vBoxLayout.setMargin(true, false, false, false);
        ScrollBoxLayout scrollBoxLayout = uiComponents.create(ScrollBoxLayout.class);
//        scrollBoxLayout.addStyleName(DESCRIPTION_BOX_STYLE);
        scrollBoxLayout.setWidth("100%");
        scrollBoxLayout.setHeight("100%");
        scrollBoxLayout.setSpacing(true);

        StringBuilder descriptionTextSb = new StringBuilder(resources.getResourceAsString(sampleItem.getDescriptionFile()));
        Label<String> descriptionLabel = uiComponents.create(Label.TYPE_STRING);
        descriptionLabel.setHtmlEnabled(true);
        descriptionLabel.setWidth("100%");

        if (!sampleItem.getDocumentationLinks().isEmpty()) {
            descriptionTextSb.append("<h2>Documentation</h2>");
            descriptionTextSb.append("<p>");
            for (DocumentationLink documentationLink : sampleItem.getDocumentationLinks()) {
                String href = String.format("<a href=\"%s\" target=\"_blank\">%s</a>",
                        bprocSamplesConfig.getDocumentationUrl() + documentationLink.getUrlSuffix(),
                        documentationLink.getCaption());
                descriptionTextSb.append(href);
            }
            descriptionTextSb.append("</p>");

//            descriptionText += sb.toString();
//
//            Label<String> documentationHeaderLabel = uiComponents.create(Label.TYPE_DEFAULT);
//            documentationHeaderLabel.setValue(messageBundle.getMessage("documentation"));
//            documentationHeaderLabel.setStyleName("h2");
//            scrollBoxLayout.add(documentationHeaderLabel);
//
//            for (DocumentationLink documentationLink : sampleItem.getDocumentationLinks()) {
//                Link link = uiComponents.create(Link.class);
//                link.setUrl(bprocSamplesConfig.getDocumentationUrl() + documentationLink.getUrlSuffix());
//                link.setCaption(documentationLink.getCaption());
//                link.setTarget("_blank");
//                scrollBoxLayout.add(link);
//            }
        }

        descriptionLabel.setValue(descriptionTextSb.toString());
        scrollBoxLayout.add(descriptionLabel);

        vBoxLayout.add(scrollBoxLayout);
        return vBoxLayout;
    }



    private SourceCodeEditor createSourceCodeEditor(AceMode mode) {
        SourceCodeEditor editor = uiComponents.create(SourceCodeEditor.class);
//        editor.setStyleName("sample-browser");
        editor.setShowPrintMargin(false);
        CubaSourceCodeEditor codeEditor = (CubaSourceCodeEditor) WebComponentsHelper.unwrap(editor);
        codeEditor.setMode(mode);
        editor.setEditable(false);
        editor.setWidth("100%");
        editor.setHeight("100%");
        return editor;
    }

    @Subscribe("startProcessBtn")
    private void onStartProcessBtnClick(Button.ClickEvent event) {
        ProcessDefinitionData processDefinitionData = findProcessDefinitionData();
        processFormScreens.createStartProcessForm(processDefinitionData, this)
                .show();
    }

    private ProcessDefinitionData findProcessDefinitionData() {
        SampleItem sampleItem = sampleItemDc.getItem();
        return bprocRepositoryService.createProcessDefinitionDataQuery()
                .processDefinitionKey(sampleItem.getProcessDefinitionKey())
                .latestVersion()
                .singleResult();
    }

    @Subscribe("openModelerBtn")
    private void onOpenModelerBtnClick(Button.ClickEvent event) {
        ProcessDefinitionData processDefinitionData = findProcessDefinitionData();
        String bpmnXml = bprocRepositoryService.getBpmnXml(processDefinitionData.getId());
        ModelerScreen modelerScreen = screenBuilders.screen(this)
                .withScreenClass(ModelerScreen.class)
                .withOpenMode(OpenMode.NEW_TAB).build();
        modelerScreen.setBpmnXml(bpmnXml);
        modelerScreen.show();
    }
}