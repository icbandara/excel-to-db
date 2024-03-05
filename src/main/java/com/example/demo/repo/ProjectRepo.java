package com.example.demo.repo;

import com.example.demo.entity.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Udara Deshan <udaradeshan.ud@gmail.com>
 * @since : 10/31/2022
 **/

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
    Optional<Project> findFirstByProName(String proName);
}
