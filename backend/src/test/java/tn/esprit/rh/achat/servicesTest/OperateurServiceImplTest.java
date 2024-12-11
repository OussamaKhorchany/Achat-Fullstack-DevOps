package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.*;

class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testRetrieveAllOperateurs() {
        // Given
        List<Operateur> operateurs = new ArrayList<>();
        Operateur operateur1 = new Operateur();
        operateur1.setIdOperateur(1L);
        operateurs.add(operateur1);

        when(operateurRepository.findAll()).thenReturn(operateurs);

        // When
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(operateurRepository, times(1)).findAll(); // Verifying the call to findAll
    }

    @Test
    void testAddOperateur() {
        // Given
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);

        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur result = operateurService.addOperateur(operateur);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdOperateur());
        verify(operateurRepository, times(1)).save(operateur); // Verifying the save operation
    }

    @Test
    void testDeleteOperateur() {
        // Given
        Long operateurId = 1L;

        // When
        operateurService.deleteOperateur(operateurId);

        // Then
        verify(operateurRepository, times(1)).deleteById(operateurId); // Verifying the delete operation
    }

    @Test
    void testUpdateOperateur() {
        // Given
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(1L);

        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // When
        Operateur result = operateurService.updateOperateur(operateur);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdOperateur());
        verify(operateurRepository, times(1)).save(operateur); // Verifying the save operation for update
    }

    @Test
    void testRetrieveOperateur() {
        // Given
        Long operateurId = 1L;
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(operateurId);

        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        // When
        Operateur result = operateurService.retrieveOperateur(operateurId);

        // Then
        assertNotNull(result);
        assertEquals(operateurId, result.getIdOperateur());
        verify(operateurRepository, times(1)).findById(operateurId); // Verifying the findById operation
    }
}
