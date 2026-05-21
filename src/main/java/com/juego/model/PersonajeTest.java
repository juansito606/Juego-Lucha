package com.juego.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Personaje.
 * 
 * Convenciones de JUnit 5:
 * - @Test: marca un método como prueba
 * - @DisplayName: nombre legible de la prueba
 * - @BeforeEach: se ejecuta ANTES de cada prueba (setup)
 * 
 * Patrón AAA (Arrange-Act-Assert):
 * - Arrange: preparar los datos
 * - Act: ejecutar la acción a probar
 * - Assert: verificar el resultado
 */
class PersonajeTest {

    private Personaje guerrero;

    @BeforeEach
    void setUp() {
        // Arrange: se ejecuta antes de CADA prueba
        // Así cada prueba empieza con un personaje fresco
        guerrero = new Personaje("Thor");
    }

    @Test
    @DisplayName("Debe crear personaje con nombre y 100 HP")
    void testCreacionPersonaje() {
        // Assert: verificamos el estado inicial
        assertEquals("Thor", guerrero.getNombre(),
                "El nombre debe ser 'Thor'");
        assertEquals(100, guerrero.getPuntosDeVida(),
                "El HP inicial debe ser 100");
        assertTrue(guerrero.estaVivo(),
                "El personaje recién creado debe estar vivo");
    }

    @Test
    @DisplayName("Debe reducir HP correctamente al recibir daño")
    void testRecibirDano() {
        // Act
        guerrero.recibirDano(30);
        // Assert
        assertEquals(70, guerrero.getPuntosDeVida(),
                "100 - 30 debe ser 70");
    }

    @Test
    @DisplayName("HP no debe bajar de 0 aunque el daño exceda el HP")
    void testHpNoNegativo() {
        // Act: daño mayor que el HP total
        guerrero.recibirDano(150);
        // Assert
        assertEquals(0, guerrero.getPuntosDeVida(),
                "El HP mínimo es 0, no puede ser negativo");
        assertFalse(guerrero.estaVivo(),
                "Con 0 HP el personaje no debe estar vivo");
    }

    @Test
    @DisplayName("No debe reducir HP con daño negativo")
    void testDanoNegativoIgnorado() {
        // Act: intentamos "curar" con daño negativo (no debe funcionar)
        guerrero.recibirDano(-20);
        // Assert: el HP no debe cambiar
        assertEquals(100, guerrero.getPuntosDeVida(),
                "El daño negativo debe ser ignorado");
    }

    @Test
    @DisplayName("Personaje con 1 HP está vivo; con 0 HP, muerto")
    void testLimitesDeVida() {
        guerrero.recibirDano(99);
        assertEquals(1, guerrero.getPuntosDeVida());
        assertTrue(guerrero.estaVivo(), "Con 1 HP debe estar vivo");

        guerrero.recibirDano(1);
        assertEquals(0, guerrero.getPuntosDeVida());
        assertFalse(guerrero.estaVivo(), "Con 0 HP debe estar muerto");
    }

    @Test
    @DisplayName("Constructor con HP personalizado funciona correctamente")
    void testConstructorConHpPersonalizado() {
        Personaje tanque = new Personaje("Tanque", 200);
        assertEquals(200, tanque.getPuntosDeVida());
        assertEquals(200, tanque.getPuntosDeVidaMaximos());
    }
}