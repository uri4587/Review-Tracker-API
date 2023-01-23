package com.multiverse.reviewTracker.service;

import com.multiverse.reviewTracker.dto.ManagerRequestDTO;
import com.multiverse.reviewTracker.exception.ResourceNotFoundException;
import com.multiverse.reviewTracker.model.Engineer;
import com.multiverse.reviewTracker.model.Manager;
import com.multiverse.reviewTracker.repository.EngineerRepository;
import com.multiverse.reviewTracker.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ManagerService {
    private final ManagerRepository managerRepository;
    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getManagers() {
        return managerRepository.findAll();
    }

    public Manager getManager(Long id) {
        return managerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Manager not found with id: " + id));
    }

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

    @Transactional
    public Manager deleteManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Manager not found with id: " + id));

        managerRepository.delete(manager);
        return manager;
    }

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
}