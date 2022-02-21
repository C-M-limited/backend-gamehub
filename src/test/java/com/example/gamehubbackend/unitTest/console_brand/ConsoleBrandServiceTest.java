package com.example.gamehubbackend.unitTest.console_brand;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ConsoleBrandServiceTest {
    @Autowired
    private ConsoleBrandService consoleBrandService;

    @MockBean
    private ConsoleBrandRepository consoleBrandRepository;


    @Test
    @DisplayName("Test should get All brands")
    void getAllBrands() {
        ConsoleBrand consoleBrand1= new ConsoleBrand("Xbox");
        ConsoleBrand consoleBrand2= new ConsoleBrand("Play Station");
        doReturn(Arrays.asList(consoleBrand1,consoleBrand2)).when(consoleBrandRepository).findAll();

        List<ConsoleBrand> result= consoleBrandService.getAllBrands();
        Assertions.assertEquals(2, result.size(), "findAll should return 2 brands");
    }

    @Test
    void addBrands() {
        ConsoleBrand consoleBrand= new ConsoleBrand("Xbox");
        doReturn(consoleBrand).when(consoleBrandRepository).save(any());

        ConsoleBrand result = consoleBrandService.addBrands(consoleBrand);
        Assertions.assertNotNull(result, "The saved brand should not be null");
        Assertions.assertEquals("Xbox",result.getName(), "The name should be xbox");
    }

    @Test
    void updateBrands() {
        ConsoleBrand orginalConsoleBrand= new ConsoleBrand("Xbox");
        doReturn(Optional.of(orginalConsoleBrand)).when(consoleBrandRepository).findBrandByID(0);

        ConsoleBrand newConsoleBrand = new ConsoleBrand("Xbox2");
        ConsoleBrand result = consoleBrandService.updateBrands(newConsoleBrand);
        Assertions.assertEquals("Xbox2",result.getName(), "The name should be xbox2");

    }

    @Test
    void deleteBrands() {
        ConsoleBrand consoleBrand=new ConsoleBrand("Xbox");
        doReturn(Optional.of(consoleBrand)).when(consoleBrandRepository).findBrandByID(0);
        String result = consoleBrandService.deleteBrands(0);
        Assertions.assertEquals("Success deleting the brand with id: 0",result);
    }

}