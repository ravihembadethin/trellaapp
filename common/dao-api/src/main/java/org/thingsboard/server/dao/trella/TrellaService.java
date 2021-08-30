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

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.Optional;

public interface TrellaService {

    Trella findTrellaById(TenantId tenantId, TrellaId trellaId);

    Trella saveTrella(Trella trella);

    void deleteTrella(TenantId tenantId, TrellaId trellaId);

    PageData<Trella> findTrellaByTenantId(TenantId tenantId, PageLink pageLink);

}
