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
import org.thingsboard.server.common.data.id.TasksId;
import org.thingsboard.server.common.data.id.TrellaId;
import org.thingsboard.server.common.data.id.TenantId;
//import org.thingsboard.server.common.data.id.TasksId;
import org.thingsboard.server.common.data.id.UUIDBased;
import org.thingsboard.server.common.data.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;
import java.util.UUID;
import java.sql.Date;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)

public class Tasks extends SearchTextBasedWithAdditionalInfo<TasksId> implements HasTenantId,HasName{
    
    private static final long serialVersionUID = -1599722990298929275L;
    
    
    private TenantId tenantId;
    private String trellaId;
    private  String water;
    private  String feed;
    private  String trim;
    private  String transplant;
    private  String inspect;
    private  String journal_note;
    private  String defol;
    private Date start_date; 

    public Tasks() {
        super();
    }

    public Tasks(TasksId id) {

        super(id);
    }
    
    public Tasks(Tasks tasks) {
        super(tasks);
        this.tenantId = tasks.getTenantId();
       this.trellaId = tasks.getTrellaId();
        this.water = tasks.getWater();
        this.feed  = tasks.getFeed();
        this.trim = tasks.getTrim();
        this.transplant = tasks.getTransplant();
        this.inspect = tasks.getInspect();
        this.journal_note = tasks.getJournalNote();
        this.defol = tasks.getDefoliate();
        this.start_date = tasks.getStart_Time();

        
    }
    public TenantId getTenantId() {
        return tenantId;
    }

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public String getTrellaId() {
        return trellaId;
    }

    public void setTrellaId(String trellaId) {
        this.trellaId = trellaId;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }
    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }
    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim)
     {
        this.trim = trim;
    }
    public String getTransplant() {
        return transplant;
    }

    public void setTransplant(String transplant) {
        this.transplant = transplant;
    }
    public String getInspect() {
        return inspect;
    }

    public void setInspect(String inspect) {
        this.inspect = inspect;
    }
    public String getDefoliate() {
        return defol;
    }

    public void setDefoliate(String defol) {
        this.defol = defol;
    }
    public Date getStart_Time() {
        return start_date;
    }

    public void setStart_Time(Date start_date) {
        this.start_date = start_date;
    }

    public String getJournalNote() {
        return journal_note;
    }

    public void setJournalNote(String journal_note) {
        this.journal_note = journal_note;
    }

    @JsonIgnore
    public ShortTasksInfo toShortTasksInfo() {
        return new ShortTasksInfo(id);
    }
    @Override
    public String getSearchText() {
        return getFeed();
    }
    @Override
    @JsonProperty(access = Access.READ_ONLY)
    public String getName() {
        return feed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tasks tasks = (Tasks) o;
        return Objects.equals(tenantId, tasks.tenantId) && Objects.equals(trellaId, tasks.trellaId) && Objects.equals(water, tasks.water) && Objects.equals(feed, tasks.feed) && Objects.equals(trim, tasks.trim) && Objects.equals(transplant, tasks.transplant) && Objects.equals(inspect, tasks.inspect) && Objects.equals(journal_note, tasks.journal_note) && Objects.equals(defol, tasks.defol) && Objects.equals(start_date, tasks.start_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tenantId, trellaId, water, feed, trim, transplant, inspect, journal_note, defol, start_date);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
       // builder.append("Trella");
        builder.append("tenantId=");
        builder.append(tenantId);
        builder.append(", water=");
        builder.append(water);
        builder.append(", feed=");
        builder.append(feed);
        builder.append(",trim =");
        builder.append(trim);
        builder.append(", transplant=");
        builder.append(transplant);
        builder.append(", defol=");
        builder.append(defol);
        builder.append(", inspect=");
        builder.append(inspect);
        builder.append(", journal_note=");
        builder.append(journal_note);
        builder.append(", start_date=");
        builder.append(start_date);
        builder.append(", trellaId=");
        builder.append(trellaId);
        builder.append(", id=");
        builder.append(id);
        return builder.toString();
    }
}
