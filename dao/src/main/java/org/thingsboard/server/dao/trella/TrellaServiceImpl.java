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
package org.thingsboard.server.dao.trella;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.Tenant;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.trella.TrellaDao;
import org.thingsboard.server.common.data.tenant.profile.DefaultTenantProfileConfiguration;
import org.thingsboard.server.dao.asset.AssetService;
import org.thingsboard.server.dao.dashboard.DashboardService;
import org.thingsboard.server.dao.trella.TrellaService;
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
public class TrellaServiceImpl extends AbstractEntityService implements TrellaService {

    public static final String INCORRECT_TRELLA_ID = "Incorrect trellaId ";
    public static final String INCORRECT_TENANT_ID = "Incorrect tenantId ";

    @Autowired
    private TrellaDao trellaDao;

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private TrellaService trellaService;

    @Override
    public Trella findTrellaById(TenantId tenantId, TrellaId trellaId) {
        log.trace("Executing findTrellaById [{}]", trellaId);
       // System.out.println("trellServiceimple");
        Validator.validateId(trellaId, INCORRECT_TRELLA_ID + trellaId);
       // System.out.println(trellaDao.findById(tenantId, trellaId.getId()));
        return trellaDao.findById(tenantId, trellaId.getId());
    }
   
    @Override
    public Trella saveTrella(Trella trella) {
        log.trace("Executing saveTrella [{}]", trella);
        //customerValidator.validate(customer, Customer::getTenantId);
        Trella savedTrella = trellaDao.save(trella.getTenantId(),trella);
       // dashboardService.updateCustomerDashboards(savedTrella.getTenantId(), savedTrella.getId());
        return savedTrella;
    }

    @Override
    public void deleteTrella(TenantId tenantId, TrellaId trellaId) {
        log.trace("Executing deleteTrella [{}]", trellaId);
        //Validator.validateId(customerId, INCORRECT_TRELLA_ID + trellaId);
        Trella trella = findTrellaById(tenantId, trellaId);
        if (trella == null) {
            throw new IncorrectParameterException("Unable to delete non-existent trella.");
        }
        // dashboardService.unassignCustomerDashboards(tenantId, customerId);
        // entityViewService.unassignCustomerEntityViews(customer.getTenantId(), customerId);
        // assetService.unassignCustomerAssets(customer.getTenantId(), customerId);
        // deviceService.unassignCustomerDevices(customer.getTenantId(), customerId);
        // userService.deleteCustomerUsers(customer.getTenantId(), customerId);
        // deleteEntityRelations(tenantId, customerId);
        trellaDao.removeById(tenantId, trellaId.getId());
    }
   

    /*@Override
    public PageData<Trella> findTrellaByTenantId(TenantId tenantId, PageLink pageLink) {
        log.trace("Executing findTrellaByTenantId, tenantId [{}], pageLink [{}]", tenantId, pageLink);
        Validator.validateId(tenantId, "Incorrect tenantId " + tenantId);
        Validator.validatePageLink(pageLink);
        return trellaDao.findTrellaByTenantId(tenantId.getId(), pageLink);
    }*/

}
