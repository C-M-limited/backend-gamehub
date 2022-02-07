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

    public Console addConsole(Console console) {
        String consoleName= console.getName();
        Optional<ConsoleBrand> optionalConsoleBrand = consoleBrandRepository.findById(console.getConsole_brand().getId());
        if (!optionalConsoleBrand.isPresent()) {
            throw new IllegalStateException("Console brand does not exits");
        }
        Optional<Console> consoleOptional= consoleRepository.findConsoleByName(consoleName);
        if (consoleOptional.isPresent()){
            throw  new IllegalStateException("the console already exits");
        }
        console.setConsole_brand(optionalConsoleBrand.get());
        return consoleRepository.save(console);
    }

    @Transactional
    public Console updateConsole(Console console) {
        int consoleID = console.getId();
        String consoleName = console.getName();
        int consoleBrandId = console.getConsole_brand().getId();
        Console consoleOnDB= consoleRepository.findConsoleByID(consoleID)
                .orElseThrow(()->new IllegalStateException(("console with id "+ consoleID +" does not exist")));
        ConsoleBrand consoleBrandOnDB = consoleBrandRepository.findBrandByID(consoleBrandId)
                .orElseThrow(()->new IllegalStateException(("brand with id "+ consoleBrandId + " does not exist.")));
        if (consoleOnDB.getConsole_Brand_Id()!=consoleBrandId){
            consoleOnDB.setConsole_brand(consoleBrandOnDB);
        }

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
