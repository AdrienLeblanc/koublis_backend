package com.koublis.domain.dto.globalwinescore;

import com.koublis.controllers.dto.WineDTO;

import java.io.Serializable;

public class LatestResults implements Serializable {

    public int count;
    public String next;
    public String previous;
    public WineDTO[] results;

}
