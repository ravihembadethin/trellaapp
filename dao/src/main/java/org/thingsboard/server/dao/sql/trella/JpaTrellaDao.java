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
package org.thingsboard.server.dao.sql.trella;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.DaoUtil;
import org.thingsboard.server.dao.trella.TrellaDao;
import org.thingsboard.server.dao.model.sql.TrellaEntity;
import org.thingsboard.server.dao.sql.JpaAbstractSearchTextDao;
import org.thingsboard.server.dao.sql.trella.TrellaRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
@Component
public class JpaTrellaDao extends JpaAbstractSearchTextDao<TrellaEntity, Trella> implements TrellaDao {

    @Autowired
    private TrellaRepository trellaRepository;

    @Override
    protected Class<TrellaEntity> getEntityClass() {
        return TrellaEntity.class;
    }

    @Override
    protected CrudRepository<TrellaEntity, UUID> getCrudRepository() {
        return trellaRepository;
    }

    @Override
    public Long countByTenantId(TenantId tenantId) {
        return trellaRepository.countByTenantId(tenantId.getId());
    }
    
    @Override
    public PageData<Trella> findTrellaByTenantId(UUID tenantId, PageLink pageLink) {
        return DaoUtil.toPageData(trellaRepository.findByTenantId(
                tenantId,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink)));
    }

   
}
