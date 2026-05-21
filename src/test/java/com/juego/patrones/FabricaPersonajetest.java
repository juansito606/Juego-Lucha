package com.juego.patrones;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.factory.*;
import com.juego.patrones.strategy.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para el patrón Factory Method.
 * 
 * Verificamos que cada fábrica crea el tipo correcto de personaje
 * con los atributos y estrategias correctas.
 */
class FabricaPersonajeTest {

    @Test
    @DisplayName("FabricaGuerrero crea personaje con 120 HP y AtaqueFisico")
    void testFabricaGuerreroCreaBien() {
        FabricaPersonaje fabrica = new FabricaGuerrero();
        PersonajeConEstrategia guerrero = fabrica.crearPersonaje("Leonidas");

        assertNotNull(guerrero, "El personaje no debe ser null");
        assertEquals("Leonidas", guerrero.getNombre());
        assertEquals(120, guerrero.getPuntosDeVida(),
                "El guerrero debe tener 120 HP");
        assertTrue(guerrero.getEstrategiaAtaque() instanceof AtaqueFisico,
                "El guerrero debe usar AtaqueFisico");
    }

    @Test
    @DisplayName("FabricaMago crea personaje con 80 HP y AtaqueMagico")
    void testFabricaMagoCreaBien() {
        FabricaPersonaje fabrica = new FabricaMago();
        PersonajeConEstrategia mago = fabrica.crearPersonaje("Gandalf");

        assertNotNull(mago);
        assertEquals("Gandalf", mago.getNombre());
        assertEquals(80, mago.getPuntosDeVida(),
                "El mago debe tener 80 HP");
        assertTrue(mago.getEstrategiaAtaque() instanceof AtaqueMagico,
                "El mago debe usar AtaqueMagico");
    }

    @Test
    @DisplayName("FabricaAsesino crea personaje con 90 HP y AtaqueCritico")
    void testFabricaAsesinoCreaBien() {
        FabricaPersonaje fabrica = new FabricaAsesino();
        PersonajeConEstrategia asesino = fabrica.crearPersonaje("Altaïr");

        assertNotNull(asesino);
        assertEquals("Altaïr", asesino.getNombre());
        assertEquals(90, asesino.getPuntosDeVida());
        assertTrue(asesino.getEstrategiaAtaque() instanceof AtaqueCritico);
    }

    @Test
    @DisplayName("Los personajes creados por la fábrica están vivos al inicio")
    void testPersonajesVivosAlCrear() {
        FabricaPersonaje fabrica = new FabricaGuerrero();
        PersonajeConEstrategia p = fabrica.crearPersonaje("Test");
        assertTrue(p.estaVivo(), "Todo personaje recién creado debe estar vivo");
    }
}