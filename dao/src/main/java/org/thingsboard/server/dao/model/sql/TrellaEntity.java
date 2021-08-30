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
package org.thingsboard.server.dao.model.sql;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.model.BaseSqlEntity;
import org.thingsboard.server.dao.model.sql.TrellaEntity;
import org.thingsboard.server.dao.model.ModelConstants;
import org.thingsboard.server.dao.model.SearchTextEntity;
import org.thingsboard.server.dao.util.mapping.JsonStringType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.sql.Date;
import java.util.UUID;



@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Table(name = ModelConstants.TRELLA_COLUMN_FAMILY_NAME)
public final class TrellaEntity extends BaseSqlEntity<Trella> implements SearchTextEntity<Trella>
 {

    @Column(name = ModelConstants.TRELLA_TENANT_ID_PROPERTY)
    private UUID tenantId;
    
    
    @Column(name = ModelConstants.TRELLA_PLANT_NAME_PROPERTY)
    private String plant_name;
    
    @Column(name = ModelConstants.TRELLA_PLANT_TYPE_PROPERTY)
    private String plant_type;
    
    @Column(name = ModelConstants.TRELLA_START_DATE_PROPERTY)
    private Date start_date;

    @Column(name = ModelConstants. TRELLA_VEGETATION_DAYS_PROPERTY)
    private int vegetation_days;

    @Column(name = ModelConstants.TRELLA_FLOWERING_DAYS_PROPERTY)
    private int flowering_days;

    @Column(name = ModelConstants.TRELLA_SEARCH_TEXT_PROPERTY)
    private String searchText;

    public TrellaEntity() {
        super();
    }

    public TrellaEntity(Trella trella) {
        if (trella.getId() != null) {
            this.setUuid(trella.getId().getId());
        }
        this.tenantId = trella.getTenantId().getId();
        this.plant_name=trella.getPlantName();
        this.plant_type = trella.getPlantType();
        this.start_date = trella.getStart_Time();
        this.vegetation_days = trella.getVegetation_days();
        this.flowering_days = trella.getFlowering_days();
       // this.searchText = trella.getSearchText();
 
    }

    @Override
    public String getSearchTextSource() {
        return plant_name;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


    @Override
    public Trella toData() {
        Trella trella = new Trella(new TrellaId(this.getUuid()));
        trella.setTenantId(new TenantId(tenantId));
        trella.setPlantName(plant_name);
        trella.setPlantType(plant_type);
        trella.setStart_Time(start_date);
        trella.setVegetation_days(vegetation_days);
        trella.setFlowering_days(flowering_days);
       // trella.setSearchText(searchText);
       // trella.setSearchText(searchText);
        return trella;
    }

}
