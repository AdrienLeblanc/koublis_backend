package com.koublis.converters;

import com.koublis.model.dto.globalwinescore.WineDto;
import com.koublis.model.documents.Wine;

public class WineConverter {

    public static Wine WineDtoToWine(WineDto wineDto) {
        Wine wine = new Wine();
        wine.setAppellation(wineDto.appellation);
        wine.setAppellation_slug(wineDto.appellation_slug);
        wine.setClassification(wineDto.classification);
        wine.setColor(wineDto.color);
        wine.setConfidence_index(wineDto.confidence_index);
        wine.setCountry(wineDto.country);
        wine.setDate(wineDto.date);
        wine.setIs_primeurs(wineDto.is_primeurs);
        wine.setJournalist_count(wineDto.journalist_count);
        wine.setLwin(wineDto.lwin);
        wine.setLwin_11(wineDto.lwin_11);
        wine.setRegions(wineDto.regions);
        wine.setScore(wineDto.score);
        wine.setVintage(wineDto.vintage);
        wine.setWine(wineDto.wine);
        wine.setWine_id(wineDto.wine_id);
        wine.setWine_slug(wineDto.wine_slug);
        wine.setWine_type(wineDto.wine_type);
        return wine;
    }

    public static WineDto WineToWineDto(Wine wine) {
        WineDto wineDto = new WineDto();
        wineDto.appellation = wine.getAppellation();
        wineDto.appellation_slug = wine.getAppellation_slug();
        wineDto.classification = wine.getClassification();
        wineDto.color = wine.getColor();
        wineDto.confidence_index = wine.getConfidence_index();
        wineDto.country = wine.getCountry();
        wineDto.date = wine.getDate();
        wineDto.is_primeurs = wine.isIs_primeurs();
        wineDto.journalist_count = wine.getJournalist_count();
        wineDto.lwin = wine.getLwin();
        wineDto.lwin_11 = wine.getLwin_11();
        wineDto.regions = wine.getRegions();
        wineDto.score = wine.getScore();
        wineDto.vintage = wine.getVintage();
        wineDto.wine = wine.getWine();
        wineDto.wine_id = wine.getWine_id();
        wineDto.wine_slug = wine.getWine_slug();
        wineDto.wine_type = wine.getWine_type();
        return wineDto;
    }

}
