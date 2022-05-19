package com.example.spring_jpa.service;

import com.example.spring_jpa.dao.StaffRepository;
import com.example.spring_jpa.entity.Project;
import com.example.spring_jpa.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements  StaffService{
    @Autowired
    StaffRepository staffRepository;


    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public Staff findById(long id) {
      return   staffRepository.findById(id).get();
    }


    @Override
    public void remove(long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> findAllByProject(Project project) {
        return staffRepository.findAllByProjectsIs(project);
    }

    @Override
    public void saveAll(List<Staff> list) {
        staffRepository.saveAll(list);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }
}
