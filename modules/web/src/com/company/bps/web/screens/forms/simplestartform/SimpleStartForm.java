package com.company.bps.web.screens.forms.simplestartform;

import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;

@UiController("bps_SimpleStartForm")
@UiDescriptor("simple-start-form.xml")
@LoadDataBeforeShow
@ProcessForm
public class SimpleStartForm extends Screen {

    @Inject
    @ProcessVariable
    protected TextField<String> orderNo;

    @Inject
    @ProcessVariable(name = "manager")
    protected LookupField<User> managerLookup;

    @Inject
    @ProcessVariable
    private TextArea<String> description;

    @Inject
    private ProcessFormContext processFormContext;

    @Subscribe("startProcessBtn")
    private void onStartProcessBtnClick(Button.ClickEvent event) {
        processFormContext.processStarting()
                .saveInjectedProcessVariables()
                .withBusinessKey(orderNo.getValue())
                .start();
        closeWithDefaultAction();
    }
}