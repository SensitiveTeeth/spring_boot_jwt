package com.auth.demo.repository;

import com.auth.demo.entity.PublicContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicContentRepository extends JpaRepository<PublicContent, Long> {
    // Find all users
    List<PublicContent> findAll();
}
