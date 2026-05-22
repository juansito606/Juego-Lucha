package com.juego.patrones;

import com.juego.model.Personaje;
import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para las estrategias de ataque.
 * Usamos objetos reales en vez de mocks porque Mockito
 * tiene restricciones con clases concretas en Java 17+.
 */
class EstrategiaAtaqueTest {

    private PersonajeConEstrategia atacante;
    private Personaje defensor;

    @BeforeEach
    void setUp() {
        atacante = new PersonajeConEstrategia("Atacante");
        defensor = new Personaje("Defensor", 99999); // HP altísimo para no morir
    }

    @Test
    @DisplayName("AtaqueFisico causa daño entre 10 y 30")
    void testAtaqueFisicoRangoDano() {
        AtaqueFisico estrategia = new AtaqueFisico();

        for (int i = 0; i < 30; i++) {
            Personaje objetivo = new Personaje("Obj", 99999);
            int vidaAntes = objetivo.getPuntosDeVida();
            estrategia.atacar(atacante, objetivo);
            int dano = vidaAntes - objetivo.getPuntosDeVida();

            assertTrue(dano >= 10 && dano <= 30,
                    "Daño físico debe ser entre 10 y 30, fue: " + dano);
        }
    }

    @Test
    @DisplayName("AtaqueMagico cuando conecta causa daño entre 15 y 45")
    void testAtaqueMagicoRangoDano() {
        AtaqueMagico estrategia = new AtaqueMagico();
        boolean alMenosUnoConecto = false;

        for (int i = 0; i < 100; i++) {
            Personaje objetivo = new Personaje("Obj", 99999);
            int vidaAntes = objetivo.getPuntosDeVida();
            estrategia.atacar(atacante, objetivo);
            int dano = vidaAntes - objetivo.getPuntosDeVida();

            if (dano > 0) {
                alMenosUnoConecto = true;
                assertTrue(dano >= 15 && dano <= 45,
                        "Daño mágico debe ser entre 15 y 45, fue: " + dano);
            }
        }

        assertTrue(alMenosUnoConecto,
                "En 100 ataques mágicos al menos uno debe conectar");
    }

    @Test
    @DisplayName("AtaqueCritico causa daño entre 5 y 30")
    void testAtaqueCriticoRangoDano() {
        AtaqueCritico estrategia = new AtaqueCritico();

        for (int i = 0; i < 50; i++) {
            Personaje objetivo = new Personaje("Obj", 99999);
            int vidaAntes = objetivo.getPuntosDeVida();
            estrategia.atacar(atacante, objetivo);
            int dano = vidaAntes - objetivo.getPuntosDeVida();

            assertTrue(dano >= 5 && dano <= 30,
                    "Daño crítico debe ser entre 5 y 30, fue: " + dano);
        }
    }
}