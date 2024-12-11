package tn.esprit.rh.achat.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.*;

class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mock objects
    }

    @Test
    void testRetrieveAllStocks() {
        // Given
        List<Stock> stocks = new ArrayList<>();
        Stock stock1 = new Stock();
        stock1.setIdStock(1L);
        stocks.add(stock1);

        when(stockRepository.findAll()).thenReturn(stocks);

        // When
        List<Stock> result = stockService.retrieveAllStocks();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(stockRepository, times(1)).findAll(); // Verifying the call to findAll
    }

    @Test
    void testAddStock() {
        // Given
        Stock stock = new Stock();
        stock.setIdStock(1L);

        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        Stock result = stockService.addStock(stock);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdStock());
        verify(stockRepository, times(1)).save(stock); // Verifying the save operation
    }

    @Test
    void testDeleteStock() {
        // Given
        Long stockId = 1L;

        // When
        stockService.deleteStock(stockId);

        // Then
        verify(stockRepository, times(1)).deleteById(stockId); // Verifying the delete operation
    }

    @Test
    void testUpdateStock() {
        // Given
        Stock stock = new Stock();
        stock.setIdStock(1L);

        when(stockRepository.save(stock)).thenReturn(stock);

        // When
        Stock result = stockService.updateStock(stock);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getIdStock());
        verify(stockRepository, times(1)).save(stock); // Verifying the save operation
    }

    @Test
    void testRetrieveStock() {
        // Given
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        // When
        Stock result = stockService.retrieveStock(stockId);

        // Then
        assertNotNull(result);
        assertEquals(stockId, result.getIdStock());
        verify(stockRepository, times(1)).findById(stockId); // Verifying the findById operation
    }

    @Test
    void testRetrieveStatusStock() {
        // Given
        List<Stock> stocks = new ArrayList<>();
        Stock stock1 = new Stock();
        stock1.setLibelleStock("Stock 1");
        stock1.setQte(5);
        stock1.setQteMin(10);
        stocks.add(stock1);

        when(stockRepository.retrieveStatusStock()).thenReturn(stocks);

        // When
        String result = stockService.retrieveStatusStock();

        // Then
        assertNotNull(result);
        assertTrue(result.contains("Stock 1"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("10"));
        verify(stockRepository, times(1)).retrieveStatusStock(); // Verifying the custom repository method call
    }
}
