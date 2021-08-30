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



package org.thingsboard.server.common.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.UUIDBased;
import org.thingsboard.server.common.data.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;
import java.util.UUID;
import java.sql.Date;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

public class Trella extends SearchTextBasedWithAdditionalInfo<TrellaId> implements HasTenantId,HasName{
    
    private static final long serialVersionUID = -1599722990298929275L;
    
    
    private TenantId tenantId;
    private String plant_name;
    private String plant_type;
    private Date  start_date;
    private int vegetation_days;
    private int flowering_days;

    public Trella() {
        super();
    }

    public Trella(TrellaId id) {

        super(id);
    }
    
    public Trella(Trella trella) {
        super(trella);
        this.tenantId = trella.getTenantId();
        this.plant_name = trella.getPlantName();
        this.plant_type = trella.getPlantType();
        this.start_date = trella.getStart_Time();
        this.vegetation_days = trella.getVegetation_days();
        this.flowering_days = trella.getFlowering_days();
        
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }
    
    public String getPlantName() {
        return plant_name;
    }

    public void setPlantName(String plant_name) {
        this.plant_name = plant_name;
    }
    public String getPlantType() {
        return plant_type;
    }

    public void setPlantType(String plant_type) {
        this.plant_type = plant_type;
    }

    public int getVegetation_days() {
        return vegetation_days;
    }

    public void setVegetation_days(int vegetation_days) {
        this.vegetation_days = vegetation_days;
    }

    public int getFlowering_days() {
        return flowering_days;
    }

    public void setFlowering_days(int flowering_days) {
        this.flowering_days = flowering_days;
    }

    public Date getStart_Time() {
        return start_date;
    }

    public void setStart_Time(Date start_date) {
        this.start_date = start_date;
    }
   
    @JsonIgnore
    public ShortTrellaInfo toShortTrellaInfo() {
        return new ShortTrellaInfo(id);
    }
    @Override
    public String getSearchText() {
        return getPlantName();
    }
    @Override
    @JsonProperty(access = Access.READ_ONLY)
    public String getName() {
        return plant_name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Trella trella = (Trella) o;
        return start_date == trella.start_date && vegetation_days == trella.vegetation_days && flowering_days == trella.flowering_days && Objects.equals(tenantId, trella.tenantId) && Objects.equals(plant_name, trella.plant_name) && Objects.equals(plant_type, trella.plant_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tenantId, plant_name, plant_type, start_date, vegetation_days, flowering_days);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
       // builder.append("Trella");
        builder.append("tenantId=");
        builder.append(tenantId);
        builder.append(", plant_name=");
        builder.append(plant_name);
        builder.append(", plant_type=");
        builder.append(plant_type);
        builder.append(", vegetation_days=");
        builder.append(vegetation_days);
        builder.append(", flowering_days=");
        builder.append(flowering_days);
        builder.append(", start_date=");
        builder.append(start_date);
        builder.append(", id=");
        builder.append(id);
        return builder.toString();
    }
}
