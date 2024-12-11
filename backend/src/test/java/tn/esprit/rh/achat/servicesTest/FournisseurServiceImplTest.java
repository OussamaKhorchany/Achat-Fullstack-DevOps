package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.*;

class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;
    
    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;
    
    @Mock
    private ProduitRepository produitRepository;
    
    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;
    
    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock objects
    }

    @Test
    void testRetrieveAllFournisseurs() {
        // Given
        List<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur fournisseur1 = new Fournisseur();
        fournisseur1.setIdFournisseur(1L);
        fournisseurs.add(fournisseur1);
        
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);
        
        // When
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();
        
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(fournisseurRepository, times(1)).findAll(); // Verifies that findAll was called once
    }

    @Test
    void testAddFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);

        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        
        // When
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);
        
        // Then
        assertNotNull(result);
        assertNotNull(result.getDetailFournisseur());
        assertEquals(fournisseur, result);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void testUpdateFournisseur() {
        // Given
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        fournisseur.setDetailFournisseur(detailFournisseur);

        when(detailFournisseurRepository.save(detailFournisseur)).thenReturn(detailFournisseur);
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        
        // When
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);
        
        // Then
        assertNotNull(result);
        verify(detailFournisseurRepository, times(1)).save(detailFournisseur);
        verify(fournisseurRepository, times(1)).save(fournisseur);
    }

    @Test
    void testDeleteFournisseur() {
        // Given
        Long fournisseurId = 1L;
        
        // When
        fournisseurService.deleteFournisseur(fournisseurId);
        
        // Then
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }

    @Test
    void testRetrieveFournisseur() {
        // Given
        Long fournisseurId = 1L;
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(fournisseurId);
        
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));
        
        // When
        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurId);
        
        // Then
        assertNotNull(result);
        assertEquals(fournisseurId, result.getIdFournisseur());
        verify(fournisseurRepository, times(1)).findById(fournisseurId);
    }

    @Test
    public void testAssignSecteurActiviteToFournisseur() {
        // Mock or create a Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L); // Set required fields
        fournisseur.setSecteurActivites(new HashSet<>()); // Initialize secteurActivites

        // Mock or create a SecteurActivite object
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setIdSecteurActivite(1L);

        // Simulate assigning SecteurActivite to Fournisseur
        fournisseur.getSecteurActivites().add(secteurActivite);

        // Assertions to validate
        assertNotNull(fournisseur.getSecteurActivites());
        assertTrue(fournisseur.getSecteurActivites().contains(secteurActivite));
    }
}
