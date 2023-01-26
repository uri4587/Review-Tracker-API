package com.multiverse.reviewTracker.service;

import com.multiverse.reviewTracker.dto.EngineerRequestDTO;
import com.multiverse.reviewTracker.dto.EngineerResponseDTO;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Engineer;

import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.EngineerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EngineerService {
    private final EngineerRepository engineerRepository;
    @Autowired
    public EngineerService(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }
    @Transactional
    public List<EngineerResponseDTO> getEngineers() {
        return engineerRepository.findAll().stream()
                .map(EngineerResponseDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public Engineer getEngineer(Long id) {
        return engineerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engineer not found"));
    }
    @Transactional
    public String getEngineerManagers(Long id) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Engineer not found"));

        return engineer.getManagers().toString();
    }

    @Transactional
    public Engineer createEngineer(String firstName,
                                   String lastName,
                                   String email,
                                   String password) {
        Engineer engineer = new Engineer();
        engineer.setFirstName(firstName);
        engineer.setLastName(lastName);
        engineer.setEmail(email);
        engineer.setPassword(password);
        
        return engineerRepository.save(engineer);
    }

    @Transactional
    public Engineer deleteEngineer(Long id) {

           Engineer engineer = engineerRepository.findById(id).orElseThrow(() ->
           new ResourceNotFoundException("Engineer not found with id: " + id));

           //remove the join between and engineer and manager entities
        List<Manager> managers = engineer.getManagers();
        for(Manager manager: managers) {
            manager.getEngineers().remove(engineer);
        }
        engineer.setManagers(new ArrayList<>());

        //delete engineer instance
           engineerRepository.delete(engineer);
           return engineer;


    }

    @Transactional
    public void deleteAll(){
        engineerRepository.deleteAll();
    }

    @Transactional
    public Engineer updateEngineer(Long id, EngineerRequestDTO engineerRequestDTO) {
            Engineer engineer = engineerRepository.findById(id)
                    .orElseThrow(() ->
                    new ResourceNotFoundException("Engineer not found with id:" + id));

                engineer.setFirstName(engineerRequestDTO.getFirstName());
                engineer.setLastName(engineerRequestDTO.getLastName());
                engineer.setEmail(engineerRequestDTO.getEmail());
                engineer.setPassword(engineerRequestDTO.getPassword());
                return engineerRepository.save(engineer);
    }

}
