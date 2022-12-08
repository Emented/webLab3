package com.emented.weblab3.model;

import com.emented.weblab3.DTO.HitDTO;
import com.emented.weblab3.DAO.HitsDAO;
import com.emented.weblab3.service.HitChecker;
import lombok.Data;
import org.jooq.Converter;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Data
@ManagedBean
@RequestScoped
public class Hit implements Serializable {

    @ManagedProperty("#{table}")
    private Table table;

    @ManagedProperty("#{hitCheckerImpl}")
    private HitChecker hitChecker;

    @ManagedProperty("#{jooqHitsDAO}")
    private HitsDAO hitsDAO;

    @ManagedProperty("#{hitConverter}")
    private Converter<HitDTO, Hit> hitConverter;

    private Double x;
    private Double y;
    private Double r = 1D;
    private Instant checkDate = Instant.now();
    private Long executionTime;
    private boolean status;
    private String timezone;

    public void saveToTable() {
        r = table.getCurrentR();
        status = hitChecker.checkHit(this);
        saveTimezone(timezone);
        executionTime = System.currentTimeMillis() - checkDate.toEpochMilli();
        hitsDAO.save(hitConverter.to(this));
    }

    public ZonedDateTime getCheckDateWithTimeZone() {
        return checkDate.atZone(ZoneId.of(getSessionTimezone()));
    }

    private void saveTimezone(String timezone) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("timezone", timezone);
    }

    private String getSessionTimezone() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().putIfAbsent("timezone", "Europe/London");
        return (String) context.getExternalContext().getSessionMap().get("timezone");
    }

}
