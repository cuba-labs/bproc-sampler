package com.company.bps.core.provider;

import com.haulmont.addon.bproc.provider.UserProvider;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * A {@link UserProvider} implementation that returns a user with the "Manager"  role.
 */
@Component("bps_ManagerUserProvider")
public class ManagerUserProvider implements UserProvider {

    @Inject
    private DataManager dataManager;

    @Inject
    private Logger log;

    private static final String MANAGER_ROLE_NAME = "Manager";

    @Override
    public User get(String executionId) {
        List<User> managers = dataManager.load(User.class)
                .query("select u from sec$User u join u.userRoles ur where ur.role.name = :roleName")
                .parameter("roleName", MANAGER_ROLE_NAME)
                .list();
        if (managers.isEmpty()) {
            log.warn("No users with 'Manager' role are found");
            return null;
        }
        return managers.get(0);
    }
}
