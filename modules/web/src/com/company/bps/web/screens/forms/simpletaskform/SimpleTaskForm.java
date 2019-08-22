package com.company.bps.web.screens.forms.simpletaskform;

import com.google.common.base.Strings;
import com.haulmont.addon.bproc.web.processform.*;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;

@UiController("bps_SimpleTaskForm")
@UiDescriptor("simple-task-form.xml")
@ProcessForm(
        outcomes = {
                @Outcome(id = "approve"),
                @Outcome(id = "reject", outputVariables = {
                        @OutputVariable(name = "rejectionReason", type = String.class)
                })
        },
        params = {
                @Param(name = "windowCaption")
        },
        outputVariables = {
                @OutputVariable(name = "approvalDate", type = Date.class)
        }
)
public class SimpleTaskForm extends Screen {

    @Inject
    @ProcessVariable
    protected TextField<String> orderNo;

    @Inject
    private ProcessFormContext processFormContext;

    @ProcessFormParam
    private String windowCaption;

    @Inject
    private TextArea<String> rejectionReasonField;

    @Inject
    @ProcessVariable(name = "approvalDate")
    private DateField<Date> approvalDateField;

    @Inject
    private Notifications notifications;

    @Inject
    private MessageBundle messageBundle;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        getWindow().setCaption(windowCaption);
    }

    @Subscribe("approveBtn")
    protected void onApproveBtnClick(Button.ClickEvent event) {
        processFormContext.taskCompletion()
                .saveInjectedProcessVariables()
                .withOutcome("approve")
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe("rejectBtn")
    protected void onRejectBtnClick(Button.ClickEvent event) {
        if (Strings.isNullOrEmpty(rejectionReasonField.getValue())) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption(messageBundle.getMessage("fillRejectionReason"))
                    .show();
            return;
        }
        processFormContext.taskCompletion()
                .saveInjectedProcessVariables()
                .withOutcome("reject")
                .addProcessVariable("rejectionReason", rejectionReasonField.getValue())
                .complete();
        closeWithDefaultAction();
    }
}