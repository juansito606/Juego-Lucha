package com.juego.patrones;

import com.juego.model.Personaje;
import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstrategiaAtaqueTest {

    @Mock
    private Personaje defensorMock;

    // Atacante real, no mock
    private PersonajeConEstrategia atacante;

    @BeforeEach
    void setUp() {
        atacante = new PersonajeConEstrategia("Atacante");
    }

    @Test
    @DisplayName("AtaqueFisico debe llamar recibirDano con valor entre 10 y 30")
    void testAtaqueFisicoRangoDano() {
        when(defensorMock.getNombre()).thenReturn("Defensor");

        AtaqueFisico estrategia = new AtaqueFisico();

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        for (int i = 0; i < 20; i++) {
            estrategia.atacar(atacante, defensorMock);
        }

        verify(defensorMock, times(20)).recibirDano(captor.capture());

        for (int dano : captor.getAllValues()) {
            assertTrue(dano >= 10 && dano <= 30,
                    "El daño físico debe estar entre 10 y 30, fue: " + dano);
        }
    }

    @Test
    @DisplayName("AtaqueMagico debe llamar recibirDano con valor entre 15 y 45 cuando conecta")
    void testAtaqueMagicoRangoDano() {
        when(defensorMock.getNombre()).thenReturn("Defensor");

        AtaqueMagico estrategia = new AtaqueMagico();
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        for (int i = 0; i < 100; i++) {
            estrategia.atacar(atacante, defensorMock);
        }

        verify(defensorMock, atLeastOnce()).recibirDano(captor.capture());

        for (int dano : captor.getAllValues()) {
            assertTrue(dano >= 15 && dano <= 45,
                    "El daño mágico debe estar entre 15 y 45, fue: " + dano);
        }
    }

    @Test
    @DisplayName("AtaqueCritico debe llamar recibirDano con valor entre 5 y 30")
    void testAtaqueCriticoEsDoble() {
        when(defensorMock.getNombre()).thenReturn("Defensor");

        AtaqueCritico estrategia = new AtaqueCritico();
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        for (int i = 0; i < 50; i++) {
            estrategia.atacar(atacante, defensorMock);
        }

        verify(defensorMock, times(50)).recibirDano(captor.capture());

        for (int dano : captor.getAllValues()) {
            assertTrue(dano >= 5 && dano <= 30,
                    "El daño debe estar entre 5 y 30, fue: " + dano);
        }
    }
}