package com.TechnoWebDistributed.tp2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // ignore unknown properties
public class Match {
    private String competition;
    private int year;
    private String round;
    private String team1;
    private String team2;
    private String team1goals;
    private String team2goals;
}
