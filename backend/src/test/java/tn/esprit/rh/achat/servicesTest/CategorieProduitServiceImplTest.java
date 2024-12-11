package tn.esprit.rh.achat.servicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // JUnit 5 extension for Mockito
public class CategorieProduitServiceImplTest {

    @Mock
    private CategorieProduitRepository categorieProduitRepository; // Mock the repository

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService; // Inject mocks into the service

    private CategorieProduit categorieProduit;

    @BeforeEach
    public void setUp() {
        // Set up the mock object
        categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(1L);
        categorieProduit.setLibelleCategorie("Category 1");
    }

    @Test
    public void testRetrieveAllCategorieProduits() {
        // Arrange
        when(categorieProduitRepository.findAll()).thenReturn(Arrays.asList(categorieProduit));

        // Act
        var result = categorieProduitService.retrieveAllCategorieProduits();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Category 1", result.get(0).getLibelleCategorie());
        verify(categorieProduitRepository, times(1)).findAll(); // Verify repository method call
    }

    @Test
    public void testAddCategorieProduit() {
        // Arrange
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);

        // Act
        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);

        // Assert
        assertNotNull(result);
        assertEquals("Category 1", result.getLibelleCategorie());
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    public void testDeleteCategorieProduit() {
        // Act
        categorieProduitService.deleteCategorieProduit(1L);

        // Assert
        verify(categorieProduitRepository, times(1)).deleteById(1L); // Verify deleteById was called
    }

    @Test
    public void testUpdateCategorieProduit() {
        // Arrange
        when(categorieProduitRepository.save(any(CategorieProduit.class))).thenReturn(categorieProduit);

        // Act
        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);

        // Assert
        assertNotNull(result);
        assertEquals("Category 1", result.getLibelleCategorie());
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    @Test
    public void testRetrieveCategorieProduit() {
        // Arrange
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.of(categorieProduit));

        // Act
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Category 1", result.getLibelleCategorie());
        verify(categorieProduitRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveCategorieProduitNotFound() {
        // Arrange
        when(categorieProduitRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(1L);

        // Assert
        assertNull(result);
        verify(categorieProduitRepository, times(1)).findById(1L);
    }
}

