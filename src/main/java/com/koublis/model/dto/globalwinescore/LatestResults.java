package com.koublis.model.dto.globalwinescore;

import com.koublis.controller.WineDTO;

import java.io.Serializable;

public class LatestResults implements Serializable {

    public int count;
    public String next;
    public String previous;
    public WineDTO[] results;

}
