package com.company.bps.core.delegate;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.security.entity.User;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailJavaDelegate implements JavaDelegate {

    private Expression addressee;
    private Expression emailSubject;
    private Expression emailBody;

    private static Logger log = LoggerFactory.getLogger(SendEmailJavaDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        User addresseeValue = (User) addressee.getValue(execution);
        if (addresseeValue == null || Strings.isNullOrEmpty(addresseeValue.getEmail())) {
            log.error("Cannot evaluate addressee email");
            return;
        }
        String emailSubjectValue = (String) emailSubject.getValue(execution);
        String emailBodyValue = (String) emailBody.getValue(execution);
        EmailService emailService = AppBeans.get(EmailService.class);
        emailService.sendEmailAsync(new EmailInfo(addresseeValue.getEmail(),
                emailSubjectValue,
                emailBodyValue));
    }
}
