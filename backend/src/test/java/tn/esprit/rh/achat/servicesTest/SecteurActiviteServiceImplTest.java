package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;

import java.util.*;

class SecteurActiviteServiceImplTest {

    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    private SecteurActiviteServiceImpl secteurActiviteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testRetrieveAllSecteurActivite() {
        // Given
        List<SecteurActivite> secteurActivites = new ArrayList<>();
        SecteurActivite secteurActivite1 = new SecteurActivite();
        secteurActivite1.setIdSecteurActivite(1L);
        secteurActivites.add(secteurActivite1);

        when(secteurActiviteRepository.findAll()).thenReturn(secteurActivites);

        // When
        List<SecteurActivite> result = secteurActiviteService.retrieveAllSecteurActivite();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(secteurActiviteRepository, times(1)).findAll(); // Verifying the call to findAll
    }

    @Test
    void testAddSecteurActivite() {
        // Given
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);

        when(secteurActiviteRepository.save(secteurActivite)).thenReturn(secteurActivite);

        // When
        SecteurActivite result = secteurActiviteService.addSecteurActivite(secteurActivite);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdSecteurActivite());
        verify(secteurActiviteRepository, times(1)).save(secteurActivite); // Verifying the save operation
    }

    @Test
    void testDeleteSecteurActivite() {
        // Given
        Long id = 1L;

        // When
        secteurActiviteService.deleteSecteurActivite(id);

        // Then
        verify(secteurActiviteRepository, times(1)).deleteById(id); // Verifying the delete operation
    }

    @Test
    void testUpdateSecteurActivite() {
        // Given
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);

        when(secteurActiviteRepository.save(secteurActivite)).thenReturn(secteurActivite);

        // When
        SecteurActivite result = secteurActiviteService.updateSecteurActivite(secteurActivite);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdSecteurActivite());
        verify(secteurActiviteRepository, times(1)).save(secteurActivite); // Verifying the save operation
    }

    @Test
    void testRetrieveSecteurActivite() {
        // Given
        Long secteurActiviteId = 1L;
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(secteurActiviteId);

        when(secteurActiviteRepository.findById(secteurActiviteId)).thenReturn(Optional.of(secteurActivite));

        // When
        SecteurActivite result = secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);

        // Then
        assertNotNull(result);
        assertEquals(secteurActiviteId, result.getIdSecteurActivite());
        verify(secteurActiviteRepository, times(1)).findById(secteurActiviteId); // Verifying the findById operation
    }
}
