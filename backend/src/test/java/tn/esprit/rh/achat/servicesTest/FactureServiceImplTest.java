package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

import java.util.*;

class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;
    
    @Mock
    private OperateurRepository operateurRepository;
    
    @Mock
    private DetailFactureRepository detailFactureRepository;
    
    @Mock
    private FournisseurRepository fournisseurRepository;
    
    @Mock
    private ProduitRepository produitRepository;
    
    @Mock
    private ReglementServiceImpl reglementService;
    
    @InjectMocks
    private FactureServiceImpl factureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mock objects
    }

    @Test
    void testRetrieveAllFactures() {
        // Given
        List<Facture> factures = new ArrayList<>();
        Facture facture1 = new Facture();
        facture1.setIdFacture(1L);
        factures.add(facture1);
        
        when(factureRepository.findAll()).thenReturn(factures);
        
        // When
        List<Facture> result = factureService.retrieveAllFactures();
        
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(factureRepository, times(1)).findAll(); // Verifies that findAll was called once
    }

    @Test
    void testAddFacture() {
        // Given
        Facture facture = new Facture();
        facture.setMontantFacture(100.0f);
        
        when(factureRepository.save(facture)).thenReturn(facture);
        
        // When
        Facture result = factureService.addFacture(facture);
        
        // Then
        assertNotNull(result);
        assertEquals(100.0f, result.getMontantFacture());
        verify(factureRepository, times(1)).save(facture); // Verifies save was called once
    }

    @Test
    void testCancelFacture() {
        // Given
        Long factureId = 1L;
        Facture facture = new Facture();
        facture.setIdFacture(factureId);
        
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));
        
        // When
        factureService.cancelFacture(factureId);
        
        // Then
        assertTrue(facture.getArchivee().equals(true));
        verify(factureRepository, times(1)).save(facture);
    }

    @Test
    void testRetrieveFacture() {
        // Given
        Long factureId = 1L;
        Facture facture = new Facture();
        facture.setIdFacture(factureId);
        
        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));
        
        // When
        Facture result = factureService.retrieveFacture(factureId);
        
        // Then
        assertNotNull(result);
        assertEquals(factureId, result.getIdFacture());
        verify(factureRepository, times(1)).findById(factureId);
    }

    @Test
    void testPourcentageRecouvrement() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date();
        float totalFactures = 5000f;
        float totalRecouvrement = 2000f;
        
        when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(totalFactures);
        when(reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(totalRecouvrement);
        
        // When
        float result = factureService.pourcentageRecouvrement(startDate, endDate);
        
        // Then
        assertEquals(40.0f, result);
    }
}
