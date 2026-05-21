package com.juego.juego;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.factory.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de integración para JuegoLucha.
 * 
 * Aquí probamos el flujo completo: que el juego funciona
 * correctamente de principio a fin.
 */
class JuegoLuchaTest {

    @Test
    @DisplayName("Al inicio del juego, ambos jugadores están vivos")
    void testEstadoInicial() {
        PersonajeConEstrategia j1 = new FabricaGuerrero().crearPersonaje("P1");
        PersonajeConEstrategia j2 = new FabricaMago().crearPersonaje("P2");
        JuegoLucha juego = new JuegoLucha(j1, j2);

        assertTrue(juego.getJugador1().estaVivo());
        assertTrue(juego.getJugador2().estaVivo());
        assertEquals(1, juego.getTurnoActual());
    }

    @Test
    @DisplayName("La batalla termina con exactamente un ganador")
    void testBatallaTerminaConUnGanador() {
        PersonajeConEstrategia j1 = new FabricaGuerrero().crearPersonaje("Hulk");
        PersonajeConEstrategia j2 = new FabricaMago().crearPersonaje("Loki");
        JuegoLucha juego = new JuegoLucha(j1, j2);

        juego.iniciarBatalla();

        // Al final, uno vive y el otro no
        boolean unVivo = j1.estaVivo() ^ j2.estaVivo(); // XOR: exactamente uno es true
        assertTrue(unVivo, "Al final exactamente un personaje debe estar vivo");
    }

    @Test
    @DisplayName("ejecutarTurno devuelve false cuando un personaje muere")
    void testEjecutarTurnoTerminaCuandoMuere() {
        // Creamos un personaje con 1 HP para que muera en el primer ataque
        PersonajeConEstrategia fuerte = new FabricaGuerrero().crearPersonaje("Fuerte");
        PersonajeConEstrategia debil = new PersonajeConEstrategia("Débil", 1);

        JuegoLucha juego = new JuegoLucha(fuerte, debil);
        boolean continua = juego.ejecutarTurno();

        // El débil muere en el primer turno, así que no debe continuar
        assertFalse(continua, "El juego debe terminar cuando un personaje muere");
        assertFalse(debil.estaVivo(), "El personaje débil debe estar muerto");
    }
}