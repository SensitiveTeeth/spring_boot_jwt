package com.auth.demo.resource;

import com.auth.demo.entity.PublicContent;
import com.auth.demo.repository.PublicContentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicContentResource {

    private PublicContentRepository publicContentRepository;
    public PublicContentResource(PublicContentRepository publicContentRepository) {
        this.publicContentRepository = publicContentRepository;
    }

    @GetMapping("/test/all")
    public List<PublicContent> retrievePublicContent() {
        return this.publicContentRepository.findAll();
    }
}
