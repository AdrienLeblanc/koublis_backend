package com.koublis.repository;

import com.koublis.entities.Wine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WineRepositoryTest {

    @Autowired
    private WineRepository wineRepository;

    @Before
    public void setUp() {
        Wine wine1= new Wine(23, "Beaujolais");
        Wine wine2= new Wine(38, "St-Maritain");

        //save wine, verify has Appellation value after save

        assertNull(wine1.getAppellation());
        assertNull(wine2.getAppellation()); //null before save
        this.wineRepository.save(wine1);
        this.wineRepository.save(wine2);
        assertNotNull(wine1.getId());
        assertNotNull(wine2.getId());
    }

    @Test
    public void testFetchData(){

        /*Test data retrieval*/
        List<Wine> wineBeaujolais = wineRepository.findByAppellation("Beaujolais");
        assertNotNull(wineBeaujolais);
        assertEquals(23, wineBeaujolais.get(0).getId());
        /*Get all products, list should only have two*/
        Iterable<Wine> wines = wineRepository.findAll();
        int count = 0;
        for(Wine p : wines){
            count++;
        }
        assertEquals(count, 2);
    }
}