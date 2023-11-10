package com.TechnoWebDistributed.tp2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // ignore unknown properties
public class Page {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Match> data;
}
