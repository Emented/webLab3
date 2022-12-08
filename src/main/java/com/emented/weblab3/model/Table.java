package com.emented.weblab3.model;

import com.emented.weblab3.DTO.HitDTO;
import com.emented.weblab3.repository.HitsRepository;
import com.emented.weblab3.service.SessionIdGetter;
import lombok.Data;
import org.jooq.Converter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
@ManagedBean
@ApplicationScoped
public class Table implements Serializable {

    @ManagedProperty("#{jooqHitsRepository}")
    private HitsRepository hitsRepository;

    @ManagedProperty("#{hitConverter}")
    private Converter<HitDTO, Hit> hitConverter;

    private List<Hit> hitList = new ArrayList<>();

    Double currentR = 1D;

    public List<Hit> getAllHits() {
        hitList = hitsRepository.findAll().stream().map(hitDTO -> hitConverter.from(hitDTO)).toList();
        return hitList;
    }

    public List<Hit> gitHitsByR() {
        hitList = hitsRepository.findAll().stream().map(hitDTO -> hitConverter.from(hitDTO)).toList();
        return hitList.stream().filter(hit -> Objects.equals(hit.getR(), currentR)).collect(Collectors.toList());
    }

    public void changeR(Double r) {
        currentR = r;
    }

    public void clear() {
        hitsRepository.clearTable();
        hitList.clear();
    }


}
