package com.company.bps.web.screens.forms.claimcompletetask;

import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("bps_ClaimCompleteTaskForm")
@UiDescriptor("claim-complete-task-form.xml")
@ProcessForm
public class ClaimCompleteTaskForm extends Screen {

    @Inject
    protected Button completeTaskBtn;

    @Inject
    protected ProcessFormContext processFormContext;

    @Inject
    protected Button claimTaskBtn;

    @Subscribe
    protected void onInit(InitEvent event) {
        TaskData taskData = processFormContext.getTaskData();
        completeTaskBtn.setVisible(taskData.getAssignee() != null);
        claimTaskBtn.setVisible(taskData.getAssignee() == null);
    }

    @Subscribe("claimTaskBtn")
    protected void onClaimTaskBtnClick(Button.ClickEvent event) {
        processFormContext.taskClaiming().claim();
        closeWithDefaultAction();
    }

    @Subscribe("completeTaskBtn")
    protected void onCompleteTaskBtnClick(Button.ClickEvent event) {
        processFormContext.taskCompletion().complete();
        closeWithDefaultAction();
    }
}