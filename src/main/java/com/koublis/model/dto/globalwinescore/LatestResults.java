package com.koublis.model.dto.globalwinescore;

import java.io.Serializable;

public class LatestResults implements Serializable {

    public int count;
    public String next;
    public String previous;
    public WineDto[] results;

}
