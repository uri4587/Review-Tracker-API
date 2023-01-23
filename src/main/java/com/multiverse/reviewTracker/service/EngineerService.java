package com.multiverse.reviewTracker.service;

import com.multiverse.reviewTracker.dto.EngineerRequestDTO;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Engineer;

import com.multiverse.reviewTracker.repository.EngineerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {
    private final EngineerRepository engineerRepository;
    @Autowired
    public EngineerService(EngineerRepository engineerRepository) {
        this.engineerRepository = engineerRepository;
    }

    public List<Engineer> getEngineers() {
        return engineerRepository.findAll();
    }

    public Engineer getEngineer(Long id) {
        return engineerRepository.findById(id).orElseThrow();
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
