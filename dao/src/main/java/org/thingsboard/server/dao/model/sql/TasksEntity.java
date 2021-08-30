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
import org.thingsboard.server.common.data.Tasks;
import org.thingsboard.server.common.data.Trella;
import org.thingsboard.server.common.data.id.TasksId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.TrellaId;
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
@Table(name = ModelConstants.TASKS_COLUMN_FAMILY_NAME)
public final class TasksEntity extends BaseSqlEntity<Tasks> implements SearchTextEntity<Tasks>
 {

    @Column(name = ModelConstants.TASKS_TENANT_ID_PROPERTY)
    private UUID tenantId;

     @Column(name = ModelConstants.TASKS_TRELLA_ID_PROPERTY)
     private String trellaId;
    
    
    @Column(name = ModelConstants.TASKS_WATER_PROPERTY)
    private String water;
    
    @Column(name = ModelConstants.TASKS_TRIM_PROPERTY)
    private String trim;
    
    @Column(name = ModelConstants.TASKS_START_DATE_PROPERTY)
    private Date start_date;

    @Column(name = ModelConstants.TASKS_FEED_PROPERTY)
    private String feed;

    @Column(name = ModelConstants.TASKS_TRANSPLANT_PROPERTY)
    private String transplant;

    @Column(name = ModelConstants.TASKS_DEFOL_PROPERTY)
    private String defol;

    @Column(name = ModelConstants.TASKS_INSPECT_PROPERTY)
    private String  inspect;

    @Column(name = ModelConstants.TASKS_JOURNAL_NOTES_PROPERTY)
    private String journal_note;

    @Column(name = ModelConstants.TRELLA_SEARCH_TEXT_PROPERTY)
    private String searchText;

    public TasksEntity() {
        super();
    }
    
    public TasksEntity(Tasks tasks) {
        if (tasks.getId() != null) {
            this.setUuid(tasks.getId().getId());
        }
        this.tenantId = tasks.getTenantId().getId();
        this.trellaId = tasks.getTrellaId();
       // this.trellaId= tasks.getTrellaId();
        this.water = tasks.getWater();
        this.feed  = tasks.getFeed();
        this.trim = tasks.getTrim();
        this.transplant = tasks.getTransplant();
        this.inspect = tasks.getInspect();
        this.journal_note = tasks.getJournalNote();
        this.defol = tasks.getDefoliate();
        this.start_date = tasks.getStart_Time();
 
    }

    @Override
    public String getSearchTextSource() {
        return feed;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }


    @Override
    public Tasks toData() {
        Tasks tasks = new Tasks(new TasksId(this.getUuid()));
        tasks.setTenantId(new TenantId(tenantId));
        tasks.setTrellaId(trellaId);
        tasks.setWater(water);
        tasks.setFeed(feed);
        tasks.setTrim(trim);
        tasks.setTransplant(transplant);
        tasks.setInspect(inspect);
        tasks.setJournalNote(journal_note);
        tasks.setDefoliate(defol);
        tasks.setStart_Time(start_date);
        return tasks;
    }

}
