package com.farhan.bioskopapi.service.impl;

import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.repository.FilmRepository;
import com.farhan.bioskopapi.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmServiceImplTest {

    @Autowired
    private FilmService filmService;
    private final FilmRepository filmRepository = Mockito.mock(FilmRepository.class);
    FilmEntity film1;
    FilmEntity film2;

    @BeforeEach
    void setUp(){
        film1 = new FilmEntity("idn1041122", "Iron Man", true);
        film2 = new FilmEntity("idn2041122", "Thor Ragnarok", true);
        Mockito.when(filmRepository.findAll()).thenReturn(List.of(film1, film2));
        Mockito.when(filmRepository.findByStatus(true)).thenReturn(List.of(film1, film2));
    }

    @Test
    @DisplayName("test jika create film berhasil")
    void saveFilmEntity() {
        Mockito.when(filmRepository.save(film1)).thenReturn(film1);
        assertEquals(film1.getFilmName(), filmService.save(film1).getFilmName());
    }

    @Test
    @DisplayName("test mendapatkan semua film")
    void findAllFilmEntity() {
        assertNotNull(filmService.findAll());
    }

    @Test
    @DisplayName("test mendaparkan film yang tayang")
    void findByStatusFilmEntity() {
        assertNotNull(filmService.findByStatus(true));
    }
}