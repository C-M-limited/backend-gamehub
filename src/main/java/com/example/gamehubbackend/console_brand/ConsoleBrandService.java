package com.example.gamehubbackend.console_brand;

import com.example.gamehubbackend.console.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsoleBrandService {
    private final ConsoleBrandRepository consoleBrandRepository;
    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleBrandService(ConsoleBrandRepository consoleBrandRepository, ConsoleRepository consoleRepository){
        this.consoleBrandRepository=consoleBrandRepository;
        this.consoleRepository = consoleRepository;
    }
    public List<ConsoleBrand> getAllBrands() {
        return consoleBrandRepository.findAll();
    }

    public ConsoleBrand addBrands(ConsoleBrand brand) {
        Optional<ConsoleBrand> brandOptional= consoleBrandRepository.findBrandByName(brand.getName());
        if (brandOptional.isPresent()){
            throw  new IllegalStateException("the brand already exits");
        }
        return consoleBrandRepository.save(brand);
    }

    @Transactional
    public ConsoleBrand updateBrands(ConsoleBrand brand) {
        int brandID = brand.getId();
        String brandName = brand.getName();
        ConsoleBrand brandOnDB = consoleBrandRepository.findBrandByID(brandID)
                .orElseThrow(()->new IllegalStateException(("console with id "+ brandID +" does not exits")));
        if (brandName!= null &&
                brandName.length()>0 &&
                !Objects.equals(brandOnDB.getName(),brandName)){
            brandOnDB.setName(brandName);
            return brandOnDB;
        }
        throw new IllegalStateException("Not valid name");

    }

    public String deleteBrands(int brand_id) {
        Optional<ConsoleBrand> brandOptional= consoleBrandRepository.findBrandByID(brand_id);
        if (!brandOptional.isPresent()){
            throw  new IllegalStateException("brand with id "+ brand_id +" does not exits");
        }
        consoleBrandRepository.deleteById(brand_id);
        return ("Success deleting the brand with id: "+brand_id);
    }
}
