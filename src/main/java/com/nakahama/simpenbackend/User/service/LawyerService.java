package com.nakahama.simpenbackend.User.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nakahama.simpenbackend.User.model.Lawyer;
import com.nakahama.simpenbackend.User.repository.LawyerDb;

@Service
public class LawyerService {

    @Autowired
    LawyerDb lawyerDb;

    public List<Lawyer> getAll() {
        return lawyerDb.findAll();
    }

    public Lawyer save(Lawyer lawyer) {
        return lawyerDb.save(lawyer);
    }
}
