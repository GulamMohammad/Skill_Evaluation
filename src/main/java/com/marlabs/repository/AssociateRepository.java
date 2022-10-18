package com.marlabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlabs.model.AssociateDetailsMaster;

public interface AssociateRepository extends JpaRepository<AssociateDetailsMaster, String> {

}
