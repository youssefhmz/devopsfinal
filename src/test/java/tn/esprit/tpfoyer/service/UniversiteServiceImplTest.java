package tn.esprit.tpfoyer.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;


    @InjectMocks
    private UniversiteServiceImpl universiteService;


    @Test
    public void testRetrieveAllUniversites() {
        Universite universite1 = new Universite();
        Universite universite2 = new Universite();
        when(universiteRepository.findAll()).thenReturn(List.of(universite1, universite2));

        List<Universite> universites = universiteService.retrieveAllUniversites();

        assertNotNull(universites);
        assertEquals(2, universites.size());
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.findById(anyLong())).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1L);

        assertNotNull(result);
    }


    @Test
    public void testAddUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
    }


    @Test
    public void testModifyUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.modifyUniversite(universite);

        assertNotNull(result);
    }


    @Test
    public void testRemoveUniversite() {
        Long universiteId = 1L;

        universiteService.removeUniversite(universiteId);

        verify(universiteRepository, times(1)).deleteById(universiteId);
    }

}
