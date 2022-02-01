package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsoleService {
    private final ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepository) {
        this.consoleRepository = consoleRepository;
    }

    public List<Console> getAllConsoles() {
        return consoleRepository.findAll();
    }

    public void addConsole(Console console) {
        Optional<Console> consoleOptional= consoleRepository.findConsoleByName(console.getConsole_name());
        if (consoleOptional.isPresent()){
            throw  new IllegalStateException("the console already exits");
        }
        consoleRepository.save(console);
    }

    @Transactional
    public void updateConsole(Console console) {
        int consoleID = console.getConsole_id();
        String consoleName = console.getConsole_name();
        int consoleBrandId = console.getConsole_brand_id();
        Console consoleOnDB= consoleRepository.findConsoleByID(consoleID)
                .orElseThrow(()->new IllegalStateException(("console with id "+ consoleID +"does not exits")));
        if (consoleName!= null &&
                consoleName.length()>0 &&
                !Objects.equals(consoleOnDB.getConsole_name(),consoleName)){
            consoleOnDB.setConsole_name(consoleName);
        }
        consoleOnDB.setConsole_brand_id(consoleBrandId);

    }

    public void deleteConsole(int console_id) {
        Optional<Console> consoleOptional= consoleRepository.findConsoleByID(console_id);
        if (!consoleOptional.isPresent()){
            throw  new IllegalStateException("console with id "+ console_id +"does not exits");
        }
        consoleRepository.deleteById(console_id);
    }
}
