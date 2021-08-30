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
package org.thingsboard.server.dao.sql.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.Tasks;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.tasks.TasksDao;
import org.thingsboard.server.dao.model.sql.TasksEntity;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;
import org.thingsboard.server.dao.sql.tasks.TasksRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@Component
public class JpaTasksDao extends JpaAbstractSearchTextDao<TasksEntity, Tasks> implements TasksDao {

    @Autowired
    private TasksRepository tasksRepository;

    @Override
    protected Class<TasksEntity> getEntityClass() {
        return TasksEntity.class;
    }

    @Override
    protected CrudRepository<TasksEntity, UUID> getCrudRepository() {
        return tasksRepository;
    }

    @Override
    public Long countByTenantId(TenantId tenantId) {
        return tasksRepository.countByTenantId(tenantId.getId());
    }
   
}
