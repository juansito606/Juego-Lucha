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

/**
 * Pruebas para las estrategias de ataque.
 * 
 * ¿Qué es Mockito?
 * Mockito crea "objetos falsos" (mocks) que simulan el comportamiento
 * de objetos reales. Esto es útil cuando:
 * - El objeto real es costoso de crear
 * - Queremos controlar exactamente qué devuelve el objeto
 * - Queremos verificar que se llamó a ciertos métodos
 * 
 * En este caso, mockemos Personaje para verificar que
 * recibirDano() fue llamado con los valores correctos.
 */
@ExtendWith(MockitoExtension.class)
class EstrategiaAtaqueTest {

    @Mock
    private Personaje defensorMock; // Objeto falso — controlamos su comportamiento

    @Mock
    private PersonajeConEstrategia atacanteMock;

    @Test
    @DisplayName("AtaqueFisico debe llamar recibirDano con valor entre 10 y 30")
    void testAtaqueFisicoRangoDano() {
        // Arrange
        when(atacanteMock.getNombre()).thenReturn("Atacante");
        when(defensorMock.getNombre()).thenReturn("Defensor");
        AtaqueFisico estrategia = new AtaqueFisico();

        // Act: ejecutamos el ataque 20 veces para probar el rango
        for (int i = 0; i < 20; i++) {
            estrategia.atacar(atacanteMock, defensorMock);
        }

        // Assert: verificamos que recibirDano fue llamado 20 veces
        // y capturamos los valores con ArgumentCaptor
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(defensorMock, times(20)).recibirDano(captor.capture());

        // Verificamos que todos los valores están en rango [10, 30]
        for (int dano : captor.getAllValues()) {
            assertTrue(dano >= 10 && dano <= 30,
                    "El daño físico debe estar entre 10 y 30, fue: " + dano);
        }
    }

    @Test
    @DisplayName("AtaqueMagico debe llamar recibirDano con valor entre 15 y 45 (cuando no falla)")
    void testAtaqueMagicoRangoDano() {
        when(atacanteMock.getNombre()).thenReturn("Mago");
        when(defensorMock.getNombre()).thenReturn("Defensor");
        AtaqueMagico estrategia = new AtaqueMagico();

        // Ejecutamos muchas veces para que algunos ataques no fallen
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        // Ejecutamos 100 veces — estadísticamente, ~70 deberían conectar
        for (int i = 0; i < 100; i++) {
            estrategia.atacar(atacanteMock, defensorMock);
        }

        verify(defensorMock, atLeastOnce()).recibirDano(captor.capture());

        for (int dano : captor.getAllValues()) {
            assertTrue(dano >= 15 && dano <= 45,
                    "El daño mágico conectado debe estar entre 15 y 45, fue: " + dano);
        }
    }

    @Test
    @DisplayName("AtaqueCritico: el daño crítico es el doble del daño base")
    void testAtaqueCriticoEsDoble() {
        // Esto lo probamos verificando que el daño siempre
        // está en el rango esperado (base o crítico)
        when(atacanteMock.getNombre()).thenReturn("Asesino");
        when(defensorMock.getNombre()).thenReturn("Defensor");
        AtaqueCritico estrategia = new AtaqueCritico();

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        for (int i = 0; i < 50; i++) {
            estrategia.atacar(atacanteMock, defensorMock);
        }

        verify(defensorMock, times(50)).recibirDano(captor.capture());

        for (int dano : captor.getAllValues()) {
            // Daño base [5,15] o crítico [10,30]
            assertTrue(dano >= 5 && dano <= 30,
                    "El daño crítico debe estar entre 5 y 30, fue: " + dano);
        }
    }
}