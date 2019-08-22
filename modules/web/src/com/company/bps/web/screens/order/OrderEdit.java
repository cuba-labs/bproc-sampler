package com.company.bps.web.screens.order;

import com.company.bps.entity.Order;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.cuba.gui.screen.*;

@UiController("bps_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
@ProcessForm
public class OrderEdit extends StandardEditor<Order> {

//    @Inject
//    private Metadata metadata;
//
//    @Inject
//    private ProcessFormContext processFormContext;
//
//    @ProcessVariable
//    private Order order;
//
//    @Subscribe
//    protected void onInit(InitEvent event) {
//        if (processFormContext != null) {
//            order = metadata.create(Order.class);
//            setEntityToEdit(order);
//        }
//    }
//
//    @Subscribe
//    protected void onBeforeClose(BeforeCloseEvent event) {
//        if (processFormContext != null) {
//            CloseAction closeAction = event.getCloseAction();
//            if (closeAction instanceof StandardCloseAction &&
//                    Window.COMMIT_ACTION_ID.equals(((StandardCloseAction) closeAction).getActionId())) {
//                Order order = getEditedEntity();
//                String businessKey = order.getNumber();
//                processFormContext.processStarting()
//                        .withBusinessKey(businessKey)
//                        .addProcessVariable("order", order)
//                        .addProcessVariable("approver", order.getManager())
//                        .start();
//            }
//        }
//    }
}