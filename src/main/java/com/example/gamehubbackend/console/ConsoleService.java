package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.console_brand.ConsoleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsoleService {
    private final ConsoleRepository consoleRepository;
    private final ConsoleBrandRepository consoleBrandRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository, ConsoleBrandRepository consoleBrandRepository) {
        this.consoleRepository = consoleRepository;
        this.consoleBrandRepository = consoleBrandRepository;
    }

    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    public Optional<Console> getAllConsolesOFaBrand(int console_brand_id) {
        return consoleRepository.findConsoleByID(console_brand_id);
    }

    public Console addConsole(Console console) {
        Optional<ConsoleBrand> optionalConsoleBrand = consoleBrandRepository.findById(console.getConsole_brand().getId());
        if (!optionalConsoleBrand.isPresent()) {
            throw new IllegalStateException("Console brand does not exits");
        }
        Optional<Console> consoleOptional= consoleRepository.findConsoleByName(console.getName());
        if (consoleOptional.isPresent()){
            throw  new IllegalStateException("the console already exits");
        }
        console.setConsole_brand(optionalConsoleBrand.get());
        return consoleRepository.save(console);
    }

    @Transactional
    public Console updateConsole(Console console) {
        Long consoleID = console.getId();
        String consoleName = console.getName();
        ConsoleBrand consoleBrand = console.getConsole_brand();
        int consoleBrandId = consoleBrand.getId();
        Console consoleOnDB= consoleRepository.findConsoleByID(consoleBrandId)
                .orElseThrow(()->new IllegalStateException(("console with id "+ consoleBrandId +"does not exits")));
        if (consoleName!= null &&
                consoleName.length()>0 &&
                !Objects.equals(consoleOnDB.getName(),consoleName)){
            consoleOnDB.setName(consoleName);
        }
        return consoleOnDB;
//        consoleOnDB.setConsole_brand(consoleBrandId);

    }

    public void deleteConsole(int console_id) {
        Optional<Console> consoleOptional= consoleRepository.findConsoleByID(console_id);
        if (!consoleOptional.isPresent()){
            throw  new IllegalStateException("console with id "+ console_id +"does not exits");
        }
        consoleRepository.deleteById(console_id);
    }

}
