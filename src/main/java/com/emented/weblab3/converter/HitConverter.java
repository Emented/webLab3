package com.emented.weblab3.converter;

import com.emented.weblab3.DTO.HitDTO;
import com.emented.weblab3.model.Hit;
import com.emented.weblab3.service.SessionIdGetter;
import lombok.Data;
import org.jooq.Converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.time.ZoneOffset;

@Data
@ManagedBean
@ApplicationScoped
public class HitConverter implements Converter<HitDTO, Hit> {

    @ManagedProperty("#{sessionIdGetterImpl}")
    private SessionIdGetter sessionIdGetter;

    @Override
    public Hit from(HitDTO databaseObject) {
        Hit hit = new Hit();

        hit.setX(databaseObject.getX());
        hit.setY(databaseObject.getY());
        hit.setR(databaseObject.getR());
        hit.setCheckDate(databaseObject.getCheckDate().toInstant());
        hit.setExecutionTime(databaseObject.getExecutionTime());
        hit.setStatus(databaseObject.isStatus());

        return hit;
    }

    @Override
    public HitDTO to(Hit userObject) {
        HitDTO hitDTO = new HitDTO();

        hitDTO.setX(userObject.getX());
        hitDTO.setY(userObject.getY());
        hitDTO.setR(userObject.getR());
        hitDTO.setCheckDate(userObject.getCheckDate().atOffset(ZoneOffset.UTC));
        hitDTO.setExecutionTime(userObject.getExecutionTime());
        hitDTO.setStatus(userObject.isStatus());
        hitDTO.setSessionId(sessionIdGetter.getCurrentSessionId());

        return hitDTO;
    }

    @Override
    public Class<HitDTO> fromType() {
        return HitDTO.class;
    }

    @Override
    public Class<Hit> toType() {
        return Hit.class;
    }
}
