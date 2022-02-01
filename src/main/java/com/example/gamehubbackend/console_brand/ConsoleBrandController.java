package com.example.gamehubbackend.console_brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public String addBrands(@RequestBody ConsoleBrand brand) {
        consoleBrandService.addBrands(brand);
        return ("Success");
    }
}
