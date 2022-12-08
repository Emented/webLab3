package com.emented.weblab3.DTO;

import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;

@Data
public class HitDTO {

    private Integer id;
    private String sessionId;
    private Double x;
    private Double y;
    private Double r;
    private OffsetDateTime checkDate;
    private Long executionTime;
    private boolean status;

}
