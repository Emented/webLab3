package com.emented.weblab3.DAO;

import com.emented.weblab3.DTO.HitDTO;

import java.util.List;

public interface HitsDAO {

    void clearTable();
    List<HitDTO> findAll();
    Integer save(HitDTO hit);

}
