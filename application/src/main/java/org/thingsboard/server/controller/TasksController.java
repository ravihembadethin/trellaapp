/**
 * Copyright © 2016-2021 The Thingsboard Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.server.common.data.Tasks;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.audit.ActionType;
import org.thingsboard.server.common.data.exception.ThingsboardException;
import org.thingsboard.server.common.data.id.TasksId;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;
import org.thingsboard.server.dao.tasks.TasksService;

@RestController
@TbCoreComponent
@RequestMapping("/api")
public class TasksController extends BaseController {

    public static final String TASKS_ID = "tasksId";

    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tasks/{tasksId}", method = RequestMethod.GET)
    @ResponseBody
    public Tasks getTasksById(@PathVariable(TASKS_ID) String strTasksId) throws ThingsboardException {
        // checkParameter(TRELLA_ID, strTrellaId);
        try {
            // System.out.println("gettrellabyid");
            TasksId tasksId = new TasksId(toUUID(strTasksId));
            //  System.out.println(tasksId);
            Tasks tasks = checkTasksId(tasksId, Operation.READ);
            // if(!trella.getAdditionalInfo().isNull()) {
            //     processDashboardIdFromAdditionalInfo((ObjectNode) trella.getAdditionalInfo(), HOME_DASHBOARD);
            // }
            return tasks;
        } catch (Exception e) {
            throw handleException(e);
        }
    }


    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public Tasks saveTasks(@RequestBody Tasks tasks) throws ThingsboardException {
        try {
             System.out.println("save for trella");
            tasks.setTenantId(getCurrentUser().getTenantId());
            // tasks.setTrellaId(Trella.getTrellaId());

            //  checkEntity(trella.getId(), trella, Resource.TRELLA);

            Tasks savedTasks = checkNotNull(tasksService.saveTasks(tasks));
            //  logEntityAction(savedTasks.getId(), savedTasks,
            // savedTasks.getId();
            // tasks.getId() == null ? ActionType.ADDED : ActionType.UPDATED, null);
            return savedTasks;

        } catch (Exception e) {

            logEntityAction(emptyId(EntityType.TASKS), tasks,
                    null, tasks.getId() == null ? ActionType.ADDED : ActionType.UPDATED, e);

            throw handleException(e);
        }
    }


    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/tasks/{tasksId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTasks(@PathVariable(TASKS_ID) String strTasksId) throws ThingsboardException {
        try {
            TasksId tasksId = new TasksId(toUUID(strTasksId));
            Tasks tasks = checkTasksId(tasksId, Operation.DELETE);
            tasksService.deleteTasks(getTenantId(), tasksId);

            // ActionType.DELETED, null, strTrellaId);

        } catch (Exception e) {

            logEntityAction(emptyId(EntityType.TASKS),
                    null,
                    null,
                    ActionType.DELETED, e, strTasksId);

            throw handleException(e);
        }
    }


}