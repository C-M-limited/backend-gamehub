package com.example.gamehubbackend.console_brand;

import com.example.gamehubbackend.console.Console;
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

    @PostMapping(path = "")
    public ConsoleBrand addBrands(@RequestBody ConsoleBrand brand) {
        return consoleBrandService.addBrands(brand);
    }

    @PutMapping(path="")
    public ConsoleBrand updateBrands(@RequestBody ConsoleBrand brand){
        return consoleBrandService.updateBrands(brand);
    }

    @DeleteMapping(path="{brand_id}")
    public String deleteBrands(@PathVariable("brand_id") int brand_id){
        consoleBrandService.deleteBrands(brand_id);
        return ("Success Delete");
    }
    @PostMapping(path="/console")
    public String addConsole(@RequestBody Console console) {
        consoleBrandService.addConsole(console);
        return ("Success");
    }
}
