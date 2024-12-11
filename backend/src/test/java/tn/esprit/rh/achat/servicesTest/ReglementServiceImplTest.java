package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.*;

class ReglementServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testRetrieveAllProduits() {
        // Given
        List<Produit> produits = new ArrayList<>();
        Produit produit1 = new Produit();
        produit1.setIdProduit(1L);
        produits.add(produit1);

        when(produitRepository.findAll()).thenReturn(produits);

        // When
        List<Produit> result = produitService.retrieveAllProduits();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(produitRepository, times(1)).findAll(); // Verifying the call to findAll
    }

    @Test
    void testAddProduit() {
        // Given
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        when(produitRepository.save(produit)).thenReturn(produit);

        // When
        Produit result = produitService.addProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdProduit());
        verify(produitRepository, times(1)).save(produit); // Verifying the save operation
    }

    @Test
    void testDeleteProduit() {
        // Given
        Long produitId = 1L;

        // When
        produitService.deleteProduit(produitId);

        // Then
        verify(produitRepository, times(1)).deleteById(produitId); // Verifying the delete operation
    }

    @Test
    void testUpdateProduit() {
        // Given
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        when(produitRepository.save(produit)).thenReturn(produit);

        // When
        Produit result = produitService.updateProduit(produit);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdProduit());
        verify(produitRepository, times(1)).save(produit); // Verifying the save operation for update
    }

    @Test
    void testRetrieveProduit() {
        // Given
        Long produitId = 1L;
        Produit produit = new Produit();
        produit.setIdProduit(produitId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));

        // When
        Produit result = produitService.retrieveProduit(produitId);

        // Then
        assertNotNull(result);
        assertEquals(produitId, result.getIdProduit());
        verify(produitRepository, times(1)).findById(produitId); // Verifying the findById operation
    }

    @Test
    void testAssignProduitToStock() {
        // Given
        Long produitId = 1L;
        Long stockId = 1L;
        Produit produit = new Produit();
        Stock stock = new Stock();
        
        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        produitService.assignProduitToStock(produitId, stockId);

        // Then
        assertNotNull(produit.getStock());
        assertEquals(stock, produit.getStock());
        verify(produitRepository, times(1)).save(produit); // Verifying the save operation
    }
}
