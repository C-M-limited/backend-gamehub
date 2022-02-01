package com.example.gamehubbackend.console_brand;

import com.example.gamehubbackend.console.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsoleBrandService {
    private final ConsoleBrandRepository consoleBrandRepository;

    @Autowired
    public ConsoleBrandService(ConsoleBrandRepository consoleBrandRepository){
        this.consoleBrandRepository=consoleBrandRepository;
    }
    public List<ConsoleBrand> getAllBrands() {
        return consoleBrandRepository.findAll();
    }

    public void addBrands(ConsoleBrand brand) {
        Optional<ConsoleBrand> brandOptional= consoleBrandRepository.findBrandByName(brand.getConsole_brand_name());
        if (brandOptional.isPresent()){
            throw  new IllegalStateException("the brand already exits");
        }
        consoleBrandRepository.save(brand);
    }

    @Transactional
    public void updateBrands(ConsoleBrand brand) {
        int brandID = brand.getConsole_brand_id();
        String brandName = brand.getConsole_brand_name();
        ConsoleBrand brandOnDB = consoleBrandRepository.findBrandByID(brandID)
                .orElseThrow(()->new IllegalStateException(("console with id "+ brandID +"does not exits")));
        if (brandName!= null &&
                brandName.length()>0 &&
                !Objects.equals(brandOnDB.getConsole_brand_name(),brandName)){
            brandOnDB.setConsole_brand_name(brandName);
        }
    }

    public void deleteBrands(int brand_id) {
        Optional<ConsoleBrand> brandOptional= consoleBrandRepository.findBrandByID(brand_id);
        if (!brandOptional.isPresent()){
            throw  new IllegalStateException("brand with id "+ brand_id +"does not exits");
        }
        consoleBrandRepository.deleteById(brand_id);
    }


}
