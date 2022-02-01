package com.example.gamehubbackend.console_brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
