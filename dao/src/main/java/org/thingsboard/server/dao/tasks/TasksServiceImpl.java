/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.dao.tasks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.Customer;
import org.thingsboard.server.common.data.Tasks;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.Tenant;
//import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TasksId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.tasks.TasksDao;
import org.thingsboard.server.common.data.tenant.profile.DefaultTenantProfileConfiguration;
import org.thingsboard.server.dao.asset.AssetService;
import org.thingsboard.server.dao.dashboard.DashboardService;
import org.thingsboard.server.dao.tasks.TasksService;
import org.thingsboard.server.dao.entity.AbstractEntityService;
import org.thingsboard.server.dao.entityview.EntityViewService;
import org.thingsboard.server.dao.exception.DataValidationException;
import org.thingsboard.server.dao.exception.IncorrectParameterException;
import org.thingsboard.server.dao.service.DataValidator;
import org.thingsboard.server.dao.service.PaginatedRemover;
import org.thingsboard.server.dao.service.Validator;
import org.thingsboard.server.dao.tenant.TbTenantProfileCache;
import org.thingsboard.server.dao.tenant.TenantDao;
import org.thingsboard.server.dao.user.UserService;

import java.io.IOException;
import java.util.Optional;

import static org.thingsboard.server.dao.service.Validator.validateId;

@Service
@Slf4j
public class TasksServiceImpl extends AbstractEntityService implements TasksService {

    public static final String INCORRECT_TASKS_ID = "Incorrect tasksId ";
    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";

    @Autowired
    private TasksDao tasksDao;

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private TasksService tasksService;

    @Autowired
    @Lazy
    private TbTenantProfileCache tenantProfileCache;

    @Override
    public Tasks findTasksById(TenantId tenantId, TasksId tasksId) {
        log.trace("Executing findTasksById [{}]", tasksId);
      //  System.out.println("trellServiceimple");
        Validator.validateId(tasksId, INCORRECT_TASKS_ID + tasksId);
     //   System.out.println(tasksDao.findById(tenantId, tasksId.getId()));
        return tasksDao.findById(tenantId, tasksId.getId());
    }
   
    @Override
    public Tasks saveTasks(Tasks tasks) {
        log.trace("Executing saveTasks [{}]", tasks);
        //tasksVal.validate(customer, Customer::getTenantId);
        Tasks savedTasks = tasksDao.save(tasks.getTenantId(),tasks);
       // dashboardService.updateCustomerDashboards(savedTrella.getTenantId(), savedTrella.getId());
        return savedTasks;
    }

    @Override
    public void deleteTasks(TenantId tenantId, TasksId tasksId) {
        log.trace("Executing deleteTasks [{}]", tasksId);
        //Validator.validateId(customerId, INCORRECT_TRELLA_ID + trellaId);
        Tasks tasks = findTasksById(tenantId, tasksId);
        if (tasks == null) {
            throw new IncorrectParameterException("Unable to delete non-existent tasks.");
        }
        // dashboardService.unassignCustomerDashboards(tenantId, customerId);
        // entityViewService.unassignCustomerEntityViews(customer.getTenantId(), customerId);
        // assetService.unassignCustomerAssets(customer.getTenantId(), customerId);
        // deviceService.unassignCustomerDevices(customer.getTenantId(), customerId);
        // userService.deleteCustomerUsers(customer.getTenantId(), customerId);
        // deleteEntityRelations(tenantId, customerId);
        tasksDao.removeById(tenantId, tasksId.getId());
    }
    private DataValidator<Tasks> tasksDataValidator =
            new DataValidator<Tasks>() {

                @Override
                protected void validateCreate(TenantId tenantId, Tasks tasks) {
                    DefaultTenantProfileConfiguration profileConfiguration =
                            (DefaultTenantProfileConfiguration)tenantProfileCache.get(tenantId).getProfileData().getConfiguration();
                    long maxTasks = profileConfiguration.getMaxTasks();

                    validateNumberOfEntitiesPerTenant(tenantId, tasksDao, maxTasks, EntityType.TASKS);
//                    tasksDao.findCustomersByTenantIdAndTitle(tasks.getTenantId().getId()).ifPresent(
//                            c -> {
//                                throw new DataValidationException("Customer with such title already exists!");
//                            }
//                    );
                }

//                @Override
//                protected void validateUpdate(TenantId tenantId, Tasks tasks) {
//                    tasksDao.findCustomersByTenantIdAndTitle(tasks.getTenantId().getId()).ifPresent(
//                            t -> {
//                                if (!t.getId().equals(tasks.getId())) {
//                                    throw new DataValidationException("Customer with such title already exists!");
//                                }
//                            }
//                    );
//                }

                @Override
                protected void validateDataImpl(TenantId tenantId, Tasks tasks) {
//                    if (StringUtils.isEmpty(customer.getTitle())) {
//                        throw new DataValidationException("Customer title should be specified!");
//                    }
//                    if (customer.getTitle().equals(PUBLIC_CUSTOMER_TITLE)) {
//                        throw new DataValidationException("'Public' title for customer is system reserved!");
//                    }
//                    if (!StringUtils.isEmpty(customer.getEmail())) {
//                        validateEmail(customer.getEmail());
//                    }
                    if (tasks.getTenantId() == null) {
                        throw new DataValidationException("Customer should be assigned to tenant!");
                    } else {
                        Tenant tenant = tenantDao.findById(tenantId, tasks.getTenantId().getId());
                        if (tenant == null) {
                            throw new DataValidationException("Customer is referencing to non-existent tenant!");
                        }
                    }
                }
            };
   
}
