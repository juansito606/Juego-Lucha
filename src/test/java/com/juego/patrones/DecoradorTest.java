package com.juego.patrones;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.decorator.PersonajeConEspada;
import com.juego.patrones.factory.FabricaGuerrero;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para el patrón Decorator.
 *
 * Verificamos que el decorador agrega daño adicional
 * sin cambiar la clase Personaje original.
 */
class DecoradorTest {

    private PersonajeConEstrategia guerreroBase;
    private PersonajeConEstrategia guerreroConEspada;

    @BeforeEach
    void setUp() {
        FabricaGuerrero fabrica = new FabricaGuerrero();
        guerreroBase = fabrica.crearPersonaje("Guerrero");
        guerreroConEspada = new PersonajeConEspada(guerreroBase, "Espada de Fuego");
    }

    @Test
    @DisplayName("El personaje decorado mantiene el nombre original")
    void testNombreConservado() {
        assertEquals("Guerrero", guerreroConEspada.getNombre(),
                "El decorator debe preservar el nombre del personaje original");
    }

    @Test
    @DisplayName("El ataque con espada causa más daño que sin espada")
    void testEspadaAumentaDano() {
        PersonajeConEstrategia defensor1 = new PersonajeConEstrategia("D1", 10000);
        PersonajeConEstrategia defensor2 = new PersonajeConEstrategia("D2", 10000);

        int ataques = 20;
        for (int i = 0; i < ataques; i++) {
            guerreroBase.atacar(defensor1);
        }
        for (int i = 0; i < ataques; i++) {
            guerreroConEspada.atacar(defensor2);
        }

        int danioSinEspada = 10000 - defensor1.getPuntosDeVida();
        int danioConEspada  = 10000 - defensor2.getPuntosDeVida();

        assertTrue(danioConEspada > danioSinEspada,
                "El guerrero con espada debe causar más daño total");
    }

    @Test
    @DisplayName("PersonajeConEspada es una instancia de PersonajeConEstrategia")
    void testDecoradorEsPersonaje() {
        assertTrue(guerreroConEspada instanceof PersonajeConEstrategia,
                "El decorator debe ser un PersonajeConEstrategia (polimorfismo)");
    }
}