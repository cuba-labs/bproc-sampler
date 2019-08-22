package com.company.bps.web.screens.order;

import com.company.bps.entity.Order;
import com.haulmont.cuba.gui.screen.*;

@UiController("bps_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}