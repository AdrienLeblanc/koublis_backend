package com.koublis.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wines")
public class Wine implements Serializable {

    @Id
    private Long wine_id;

    private String wine;

    private String wine_slug;

    private String appellation;

    private String appellation_slug;

    private String color;

    private String wine_type;

    private String[] regions;

    private String country;

    private String classification;

    private String vintage;

    private Date date;

    private Boolean is_primeurs;

    private Long score;

    private String confidence_index;

    private Long journalist_count;

    private Long lwin;

    private Long lwin_11;


    public Wine() {
    }

    public Long getWine_id() {
        return wine_id;
    }

    public void setWine_id(Long wine_id) {
        this.wine_id = wine_id;
    }

    public String getWine() {
        return wine;
    }

    public void setWine(String wine) {
        this.wine = wine;
    }

    public String getWine_slug() {
        return wine_slug;
    }

    public void setWine_slug(String wine_slug) {
        this.wine_slug = wine_slug;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getAppellation_slug() {
        return appellation_slug;
    }

    public void setAppellation_slug(String appellation_slug) {
        this.appellation_slug = appellation_slug;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWine_type() {
        return wine_type;
    }

    public void setWine_type(String wine_type) {
        this.wine_type = wine_type;
    }

    public String[] getRegions() {
        return regions;
    }

    public void setRegions(String[] regions) {
        this.regions = regions;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getVintage() {
        return vintage;
    }

    public void setVintage(String vLongage) {
        this.vintage = vLongage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isIs_primeurs() {
        return is_primeurs;
    }

    public void setIs_primeurs(Boolean is_primeurs) {
        this.is_primeurs = is_primeurs;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getConfidence_index() {
        return confidence_index;
    }

    public void setConfidence_index(String confidence_index) {
        this.confidence_index = confidence_index;
    }

    public Long getJournalist_count() {
        return journalist_count;
    }

    public void setJournalist_count(Long journalist_count) {
        this.journalist_count = journalist_count;
    }

    public Long getLwin() {
        return lwin;
    }

    public void setLwin(Long lwin) {
        this.lwin = lwin;
    }

    public Long getLwin_11() {
        return lwin_11;
    }

    public void setLwin_11(Long lwin_11) {
        this.lwin_11 = lwin_11;
    }
}
