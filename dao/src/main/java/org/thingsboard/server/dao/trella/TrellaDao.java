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

import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;
import org.thingsboard.server.dao.Dao;
import org.thingsboard.server.dao.TenantEntityDao;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface CustomerDao.
 */
public interface TrellaDao extends Dao<Trella>, TenantEntityDao {

   
   

    /**
     
     *
     * @param trella the customer object
     * @return saved customer object
     */
    Trella save(TenantId tenantId, Trella trella);
    
    /**
      * Find customers by tenant id and page link.
      *
      * @param tenantId the tenant id
      * @param pageLink the page link
      * @return the list of customer objects
      */
     PageData<Trella> findTrellaByTenantId(UUID tenantId, PageLink pageLink);

    // /**
    //  * Find customers by tenantId and customer title.
    //  *
    //  * @param tenantId the tenantId
    //  * @param title the customer title
    //  * @return the optional customer object
    //  */
    // Optional<Customer> findCustomersByTenantIdAndTitle(UUID tenantId, String title);


}
