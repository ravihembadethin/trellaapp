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
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.EntityType;
import org.thingsboard.server.common.data.audit.ActionType;
import org.thingsboard.server.common.data.exception.ThingsboardException;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;

@RestController
@TbCoreComponent
@RequestMapping("/api")
//@CrossOrigin(origins="https://localhost:8080")
public class TrellaController extends BaseController {

    public static final String TRELLA_ID = "trellaId";

    @PreAuthorize("hasAnyAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/trella/{trellaId}", method = RequestMethod.GET)
    @ResponseBody
    public Trella getTrellaById(@PathVariable(TRELLA_ID) String strTrellaId) throws ThingsboardException {
        // checkParameter(TRELLA_ID, strTrellaId);
        try {
            // System.out.println("gettrellabyid");
            TrellaId trellaId = new TrellaId(toUUID(strTrellaId));
            //  System.out.println(trellaId);
            Trella trella = checkTrellaId(trellaId, Operation.READ);
            // if(!trella.getAdditionalInfo().isNull()) {
            //     processDashboardIdFromAdditionalInfo((ObjectNode) trella.getAdditionalInfo(), HOME_DASHBOARD);
            // }
            return trella;
        } catch (Exception e) {
            throw handleException(e);
        }
    }


    // @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    // @RequestMapping(value = "/trella", method = RequestMethod.POST)
    // @ResponseBody
    // public Trella saveTrella(@RequestBody Trella trella) throws ThingsboardException {
    //     try {
    //        // System.out.println("save for trella");
    //         trella.setTenantId(getCurrentUser().getTenantId());

    //         //  checkEntity(trella.getId(), trella, Resource.TRELLA);

    //         Trella savedTrella = checkNotNull(trellaService.saveTrella(trella));

    //         //logEntityAction(savedTrella.getId(), savedTrella,
    //       // savedTrella.getId(),
    //        // trella.getId() == null ? ActionType.ADDED : ActionType.UPDATED, null);
            
    //         return savedTrella;
    //     } catch (Exception e) {

    //         logEntityAction(emptyId(EntityType.TRELLA), trella,
    //                 null, trella.getId() == null ? ActionType.ADDED : ActionType.UPDATED, e);

    //         throw handleException(e);
    //     }
    // }
        @PreAuthorize("hasAuthority('TENANT_ADMIN')")
        @RequestMapping(value = "/trella", method = RequestMethod.POST)
        @ResponseBody
        public Trella saveTrella(@RequestBody Trella trella) throws ThingsboardException {
        try {
        //System.out.println("save for trella");
        trella.setTenantId(getCurrentUser().getTenantId());

        // checkEntity(trella.getId(), trella, Resource.TRELLA);

        Trella savedTrella = checkNotNull(trellaService.saveTrella(trella));


        return savedTrella;
        } catch (Exception e) {

        logEntityAction(emptyId(EntityType.TRELLA), trella,
        null, trella.getId() == null ? ActionType.ADDED : ActionType.UPDATED, e);

        throw handleException(e);
        }
        }

    @PreAuthorize("hasAuthority('TENANT_ADMIN')")
    @RequestMapping(value = "/trella/{trellaId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTrella(@PathVariable(TRELLA_ID) String strTrellaId) throws ThingsboardException {

        try {
            TrellaId trellaId = new TrellaId(toUUID(strTrellaId));
            Trella trella = checkTrellaId(trellaId, Operation.DELETE);
            trellaService.deleteTrella(getTenantId(), trellaId);

            // ActionType.DELETED, null, strTrellaId);

        } catch (Exception e) {

            logEntityAction(emptyId(EntityType.TRELLA),
                    null,
                    null,
                    ActionType.DELETED, e, strTrellaId);

            throw handleException(e);
        }
    }

}