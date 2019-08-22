package com.company.bps.web.screens.forms.ordertask;

import com.company.bps.entity.Order;
import com.haulmont.addon.bproc.web.processform.Outcome;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("bps_OrderEditTaskForm")
@UiDescriptor("order-edit-task-form.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
@ProcessForm(outcomes = {
        @Outcome(id = "approve"),
        @Outcome(id = "reject")
})
public class OrderEditTaskForm extends StandardEditor<Order> {

    @ProcessVariable
    private Order order;

    @Inject
    private ProcessFormContext processFormContext;

    @Subscribe
    protected void onInit(InitEvent event) {
        setEntityToEdit(order);
    }

    @Subscribe("approve")
    protected void onApprove(Action.ActionPerformedEvent event) {
        commitChanges()
                .then(() -> {
                    processFormContext.taskCompletion()
                            .withOutcome("approve")
                            .complete();
                    closeWithDefaultAction();
                });
    }

    @Subscribe("reject")
    protected void onReject(Action.ActionPerformedEvent event) {
        commitChanges()
                .then(() -> {
                    processFormContext.taskCompletion()
                            .withOutcome("reject")
                            .complete();
                    closeWithDefaultAction();
                });
    }
}