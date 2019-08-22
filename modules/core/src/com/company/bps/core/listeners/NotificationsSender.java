package com.company.bps.core.listeners;

import com.company.bps.entity.Order;
import com.google.common.base.Strings;
import com.haulmont.addon.bproc.entity.ProcessDefinitionData;
import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.events.UserTaskAssignedEvent;
import com.haulmont.addon.bproc.service.BprocRuntimeService;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component(NotificationsSender.NAME)
public class NotificationsSender {

    public static final String NAME = "bps_NotificationsSender";

    @Inject
    private BprocRuntimeService bprocRuntimeService;

    @Inject
    private EmailService emailService;

    @Inject
    private Logger log;

    /**
     * The listener will be  fired for 'user-task-assigned-event-sample' process only
     */
    @EventListener(condition = "#event.processDefinitionData.key == 'user-task-assigned-event'")
    public void onEventListenerSampleProcessTaskAssigned(UserTaskAssignedEvent event) {
        User user = event.getUser();
        if (Strings.isNullOrEmpty(user.getEmail())) {
            log.error("User email is not defined. Cannot send the email.");
            return;
        }
        TaskData taskData = event.getTaskData();
        Order order = (Order) bprocRuntimeService.getVariable(taskData.getExecutionId(), "order");
        String messageTitle = "Approval required";
        String templatePath = "com/company/bps/core/listeners/order-approval-notification.template";
        Map<String, Serializable> templateParameters = new HashMap<>();
        templateParameters.put("user", user);
        templateParameters.put("taskData", taskData);
        templateParameters.put("order", order);
        EmailInfo emailInfo = new EmailInfo(
                user.getEmail(),
                messageTitle,
                null,
                templatePath,
                templateParameters
        );
        emailService.sendEmailAsync(emailInfo);
        log.info("event-listener-sample email sent to {}", user.getEmail());
    }

    /**
     * The listener will be fired for all processes. The process definition can be evaluated within the method
     */
    @EventListener
    public void onOtherProcessTaskAssigned(UserTaskAssignedEvent event) {
        ProcessDefinitionData processDefinitionData = event.getProcessDefinitionData();
        switch (processDefinitionData.getKey()) {
            case "process-1":
            case "process-2":
        }
    }

}