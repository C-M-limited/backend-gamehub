package com.example.gamehubbackend.console_brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/console_brand")
public class ConsoleBrandController {
    private final ConsoleBrandService consoleBrandService;

    @Autowired
    public ConsoleBrandController(ConsoleBrandService consoleBrandService) {
        this.consoleBrandService = consoleBrandService;
    }

    @GetMapping("/all")
    public List<ConsoleBrand> getAllBrands() {
        return consoleBrandService.getAllBrands();
    }

    @PostMapping(path = "")
    public String addBrands(@RequestBody ConsoleBrand brand) {
        consoleBrandService.addBrands(brand);
        return ("Success");
    }

    @PutMapping(path="")
    public String updateBrands(@RequestBody ConsoleBrand brand){
        consoleBrandService.updateBrands(brand);
        return ("Success");
    }

    @DeleteMapping(path="{brand_id}")
    public String deleteBrands(@PathVariable("brand_id") int brand_id){
        consoleBrandService.deleteBrands(brand_id);
        return ("Success Delete");
    }
}
