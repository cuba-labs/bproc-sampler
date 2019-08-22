package com.company.bps.core.listeners;

import com.company.bps.entity.Order;
import com.haulmont.addon.bproc.entity.ProcessDefinitionData;
import com.haulmont.addon.bproc.events.ActivityStartedEvent;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.TransactionalDataManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Component("bps_ProcessStatesListener")
public class ProcessStatesListener {

    @Inject
    private BprocRuntimeService bprocRuntimeService;

    @Inject
    private TransactionalDataManager txDataManager;

    private List<String> applicableProcesses = Arrays.asList("user-task-assigned-event-listener", "entity-editor-form");

    @EventListener
    protected void onActivityStarted(ActivityStartedEvent event) {
        ProcessDefinitionData processDefinitionData = event.getProcessDefinitionData();
        if (!applicableProcesses.contains(processDefinitionData.getKey())) return;
        Order order = (Order) bprocRuntimeService.getVariable(event.getExecutionId(), "order");
        order.setState(event.getActivityName());
        txDataManager.save(order);
    }
}
