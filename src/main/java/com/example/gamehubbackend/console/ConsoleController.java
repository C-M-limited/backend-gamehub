package com.example.gamehubbackend.console;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/console")
public class ConsoleController {
    private final ConsoleService consoleService;

    @Autowired
    public ConsoleController(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    @GetMapping("/all")
    public List<Console> getAllConsoles() {
        return consoleService.getAllConsoles();
    }

    @PostMapping(path="")
    public String addConsole(@RequestBody Console console){
        consoleService.addConsole(console);
        return ("Success");
    }
    @PutMapping(path="")
    public String editConsole(@RequestBody Console console){
        consoleService.updateConsole(console);
        return ("Success");
    }
    @DeleteMapping(path="{brand_id}")
    public String deleteConsole(@PathVariable("console_id") int console_id){
        consoleService.deleteConsole(console_id);
        return ("Success Delete");
    }

}
