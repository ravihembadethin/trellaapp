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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.dao.model.sql.TasksEntity;
import org.thingsboard.server.common.data.id.TasksId;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
public interface TasksRepository extends PagingAndSortingRepository<TasksEntity, UUID> {

    @Query("SELECT t1 FROM TasksEntity t1 WHERE t1.tenantId = :tenantId " +
            "AND LOWER(t1.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
   
    //TrellaEntity findTrellaById(UUID tenantId, TrellaId trellaId);

    Long countByTenantId(UUID tenantId);
}
