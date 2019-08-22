package com.company.bps.web.screens.forms.orderstart;

import com.company.bps.entity.Order;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Window;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("bps_OrderEditStartForm")
@UiDescriptor("order-edit-start-form.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
@ProcessForm
public class OrderEditStartForm extends StandardEditor<Order> {

    @Inject
    private Metadata metadata;

    @Inject
    private ProcessFormContext processFormContext;

    @Subscribe
    protected void onInit(InitEvent event) {
        Order order = metadata.create(Order.class);
        setEntityToEdit(order);
    }

    @Subscribe("startProcessBtn")
    private void onStartProcessBtnClick(Button.ClickEvent event) {
        commitChanges()
                .then(() -> {
                    Order order = getEditedEntity();
                    String businessKey = order.getNumber();
                    processFormContext.processStarting()
                            .withBusinessKey(businessKey)
                            .addProcessVariable("order", order)
                            .addProcessVariable("approver", order.getManager())
                            .start();
                    closeWithCommit();
                });
    }
}