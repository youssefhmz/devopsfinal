package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;


    @BeforeEach
    void setUp() {
        etudiantService = new EtudiantServiceImpl(etudiantRepository);
    }


    @Test
    void retrieveAllEtudiants() {
        // Mocking the behavior of repository
        List<Etudiant> mockEtudiants = new ArrayList<>();
        mockEtudiants.add(new Etudiant(1L, "John Doe", "123456"));

        when(etudiantRepository.findAll()).thenReturn(mockEtudiants);

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        assertNotNull(etudiants);
        assertTrue(etudiants.size() > 0);
        assertEquals("John Doe", etudiants.get(0).getNomEtudiant());
    }

    @Test
    void retrieveEtudiant() {
        Long id = 1L;
        Etudiant mockEtudiant = new Etudiant(1L, "John Doe", "CIN123456");

        when(etudiantRepository.findById(id)).thenReturn(java.util.Optional.of(mockEtudiant));

        Etudiant etudiant = etudiantService.retrieveEtudiant(id);

        assertNotNull(etudiant);
        assertEquals(id, etudiant.getIdEtudiant());
        assertEquals("John Doe", etudiant.getNomEtudiant());
    }


    @Test
    void modifyEtudiant() {
        // Step 1: Create a mock Etudiant that will be modified
        Etudiant existingEtudiant = new Etudiant(1L, "John Doe", "123456"); // Change "CIN123456" to 123456

        // Step 2: Mock the repository's save method
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(existingEtudiant); // Change to any(Etudiant.class)

        // Step 3: Call the method under test
        Etudiant updatedEtudiant = etudiantService.modifyEtudiant(existingEtudiant);

        // Step 4: Assertions
        assertNotNull(updatedEtudiant);
        assertEquals(1L, updatedEtudiant.getIdEtudiant());
        assertEquals("John Doe", updatedEtudiant.getNomEtudiant()); // Ensure this matches the original name
    }


    @Test
    void removeEtudiant() {
        Long id = 1L;

        // We don't need to mock anything, assuming the method is void
        doNothing().when(etudiantRepository).deleteById(id);

        etudiantService.removeEtudiant(id);

        // Verify that the deleteById method was called
        verify(etudiantRepository, times(1)).deleteById(id);
    }

    @Test
    void recupererEtudiantParCin() {
        long cin = 123456L;
        Etudiant mockEtudiant = new Etudiant(1L, "John Doe", "123456");

        when(etudiantRepository.findEtudiantByCinEtudiant(cin)).thenReturn(mockEtudiant);

        Etudiant etudiant = etudiantService.recupererEtudiantParCin(cin);

        assertNotNull(etudiant);
        assertEquals(cin, etudiant.getCinEtudiant());
    }
}
