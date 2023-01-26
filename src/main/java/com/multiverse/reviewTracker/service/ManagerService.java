package com.multiverse.reviewTracker.service;

import com.multiverse.reviewTracker.dto.ManagerRequestDTO;
import com.multiverse.reviewTracker.dto.ManagerResponseDTO;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.EngineerRepository;
import com.multiverse.reviewTracker.repository.ManagerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.catalina.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final EngineerRepository engineerRepository;

//    class constructor for ManagerService
    @Autowired
    public ManagerService(ManagerRepository managerRepository,
                          EngineerRepository engineerRepository) {
        this.managerRepository = managerRepository;
        this.engineerRepository = engineerRepository;
    }

//    Get All Managers Method
    @Transactional
    public List<ManagerResponseDTO> getManagers() {
        return managerRepository.findAll().stream()
                .map(ManagerResponseDTO::new)
                .collect(Collectors.toList());
    }

//    Get Manager by id Method
    @Transactional
    public Manager getManager(Long id) {
        return managerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Manager not found with id: " + id));
    }
// Create a Manager Instance Method
    @Transactional
    public Manager createManager(String firstName,
                                   String lastName,
                                   String email,
                                   String password) {
        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setEmail(email);
        manager.setPassword(password);

        return managerRepository.save(manager);
    }

// Delete Manager Instance Method
    @Transactional
    public Manager deleteManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Manager not found with id: " + id));
        //remove join between engineer and manager entities
        List<Engineer> engineers = manager.getEngineers();
        for (Engineer engineer: engineers) {
            engineer.getManagers().remove(manager);
        }
        manager.setEngineers(new ArrayList<>());

        // delete manager instance
        managerRepository.delete(manager);
        return manager;
    }
// Delete all Managers Method
    @Transactional
    public void deleteAll() {

        managerRepository.deleteAll();
    }

//Update Manager Method
    @Transactional
    public Manager updateManager(Long id, ManagerRequestDTO managerRequestDTO) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Manager not found with id:" + id));

        manager.setFirstName(managerRequestDTO.getFirstName());
        manager.setLastName(managerRequestDTO.getLastName());
        manager.setEmail(managerRequestDTO.getEmail());
        manager.setPassword(managerRequestDTO.getPassword());
        return managerRepository.save(manager);
    }

//Create a JOIN instance for Engineer and Manager(Exclusive to only Managers)
    @Transactional
    public void createManagerEngineerJoin(Long managerId, Long engineerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        Engineer engineer = engineerRepository.findById(engineerId)
                .orElseThrow(()-> new ResourceNotFoundException("Engineer not found"));

        manager.getEngineers().add(engineer);
        engineer.getManagers().add(manager);

        managerRepository.save(manager);
        engineerRepository.save(engineer);
    }

    @Transactional
    public String getManagerEngineers(Long managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));

        return manager.getEngineers().toString();

    }

//Delete a Join between Manager and Engineer(Exclusive to Only Managers)
    @Transactional
    public void deleteManagerEngineerJoin(Long managerId, Long engineerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        Engineer engineer = engineerRepository.findById(engineerId)
                .orElseThrow(()-> new ResourceNotFoundException("Engineer not found"));

        if(manager != null && engineer != null) {
            manager.getEngineers().remove(engineer);
            engineer.getManagers().remove(manager);
            managerRepository.save(manager);
            engineerRepository.save(engineer);
        }



    }
}