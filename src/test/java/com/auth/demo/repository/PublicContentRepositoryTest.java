package com.auth.demo.repository;
import com.auth.demo.entity.PublicContent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PublicContentRepositoryTest {
    @InjectMocks
    private PublicContentRepository publicContentRepository; // The repository being tested

    @Mock
    private EntityManager entityManager; // Mocked entity manager

    @Test
    public void testFindAll() {
        // Create some sample User entities for testing
        PublicContent publicContent1 = new PublicContent(1L, "first content");
        PublicContent publicContent2 = new PublicContent(2L, "second content");
        List<PublicContent> publicContents = Arrays.asList(publicContent1, publicContent2);

        // Mock the behavior of the entity manager to return the sample User entities
        TypedQuery<PublicContent> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PublicContent.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(publicContents);

        // Call the findAll() method on the userRepository
        List<PublicContent> result = publicContentRepository.findAll();

        // Verify that the entity manager's createQuery and getResultList methods were called
        verify(entityManager).createQuery(anyString(), eq(PublicContent.class));
        verify(query).getResultList();

        // Assert that the returned result matches the expected users
        assertEquals(publicContents, result);
    }
}
