package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import com.example.gamehubbackend.console_brand.ConsoleBrandService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
class ConsoleServiceTest {
    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private ConsoleBrandService consoleBrandService;
    @MockBean
    private ConsoleRepository consoleRepository;
    @MockBean
    private static ConsoleBrandRepository consoleBrandRepository;

    ConsoleBrand consoleBrand1 = new ConsoleBrand("Xbox");


    @Test
    void getAllConsoles() {

        Console console1 = new Console("Xbox 360", consoleBrand1);
        Console console2 = new Console("Xbox One", consoleBrand1);
        doReturn(Arrays.asList(console1,console2)).when(consoleRepository).findAll();

        List<Console> result= consoleService.getAllConsoles();
        Assertions.assertEquals(2, result.size(), "findAll should return 2 consoles");
    }

    @Test
    void addConsole() {
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findById(0);
        Console console1 = new Console("Xbox 360", consoleBrand1);
        doReturn(console1).when(consoleRepository).save(console1);

        Console result = consoleService.addConsole(console1);
        Assertions.assertEquals("Xbox 360",result.getName(), "It should add the console");
    }

    @Test
    void updateConsole() {
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findBrandByID(0);
        ConsoleBrand newConsoleBrand = new ConsoleBrand(1,"Xbox2");
        doReturn(Optional.of(newConsoleBrand)).when(consoleBrandRepository).findBrandByID(1);
        Console originalConsole = new Console("Xbox 360", consoleBrand1);
        Console newConsole = new Console(0,"Xbox One", newConsoleBrand);
        doReturn(Optional.of(originalConsole)).when(consoleRepository).findConsoleByID(0);

        Console result = consoleService.updateConsole(newConsole);
        Assertions.assertEquals("Xbox One",result.getName());
        Assertions.assertEquals(newConsoleBrand,result.getConsole_brand());


    }

    @Test
    void deleteConsole() {
        doReturn(Optional.of(consoleBrand1)).when(consoleBrandRepository).findById(0);
        Console console1 = new Console("Xbox 360", consoleBrand1);
        doReturn(Optional.of(console1)).when(consoleRepository).findConsoleByID(0);
        String result = consoleService.deleteConsole(0);
        Assertions.assertEquals("Success deleting the console with id: 0",result);

    }
}