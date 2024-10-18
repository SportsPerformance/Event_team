package com.example.eventpro.repo;

import com.example.eventpro.model.Eventpro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Eventrepo extends JpaRepository<Eventpro, Long> {

}
