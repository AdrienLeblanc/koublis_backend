package com.koublis.model.dto.globalwinescore;

import java.io.Serializable;
import java.util.Date;

public class WineDto implements Serializable {

    public String appellation;
    public String appellation_slug;
    public String classification;
    public String color;
    public String confidence_index;
    public String country;
    public Date date;
    public Boolean is_primeurs;
    public Long journalist_count;
    public Long lwin;
    public Long lwin_11;
    public String[] regions;
    public Long score;
    public String vintage;
    public String wine;
    public Long wine_id;
    public String wine_slug;
    public String wine_type;

    public WineDto() {
    }

}
