package com.emented.weblab3.repository;

import com.emented.weblab3.DTO.HitDTO;

import java.util.List;

public interface HitsRepository {

    void clearTable();
    List<HitDTO> findAll();
    Integer save(HitDTO hit);

}
