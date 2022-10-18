package com.marlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlabs.model.BatchMaster;

public interface BatchRepository extends JpaRepository<BatchMaster, String> {

}
