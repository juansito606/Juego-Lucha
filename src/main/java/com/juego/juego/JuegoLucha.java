package com.juego.juego;

import com.juego.model.PersonajeConEstrategia;

/**
 * Orquesta la batalla entre dos personajes.
 * 
 * Esta clase NO sabe cómo se crean los personajes (eso es trabajo de Factory).
 * NO sabe cómo calculan el daño (eso es Strategy).
 * Solo sabe cómo se desarrolla una pelea por turnos.
 * 
 * Esto se llama SEPARACIÓN DE RESPONSABILIDADES — cada clase
 * hace UNA SOLA cosa bien. (Principio SRP de SOLID)
 */
public class JuegoLucha {

    private PersonajeConEstrategia jugador1;
    private PersonajeConEstrategia jugador2;
    private int turnoActual;

    public JuegoLucha(PersonajeConEstrategia jugador1, PersonajeConEstrategia jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.turnoActual = 1;
    }

    /**
     * Ejecuta UN turno de la batalla.
     * El jugador1 ataca al jugador2 y viceversa (si sigue vivo).
     * 
     * @return true si la batalla debe continuar, false si terminó
     */
    public boolean ejecutarTurno() {
        System.out.println("\n═══════════════════════════════");
        System.out.println("         TURNO " + turnoActual);
        System.out.println("═══════════════════════════════");
        System.out.println(jugador1);
        System.out.println(jugador2);
        System.out.println("───────────────────────────────");

        // Jugador 1 ataca
        jugador1.atacar(jugador2);

        // ¿Murió el jugador 2?
        if (!jugador2.estaVivo()) {
            anunciarGanador(jugador1, jugador2);
            return false; // Batalla terminada
        }

        // Jugador 2 contraataca
        jugador2.atacar(jugador1);

        // ¿Murió el jugador 1?
        if (!jugador1.estaVivo()) {
            anunciarGanador(jugador2, jugador1);
            return false; // Batalla terminada
        }

        turnoActual++;
        return true; // La batalla continúa
    }

    /**
     * Ejecuta la batalla completa hasta que uno de los dos muera.
     */
    public void iniciarBatalla() {
        System.out.println("\n╔═══════════════════════════════╗");
        System.out.println("║     ¡QUE COMIENCE LA LUCHA!   ║");
        System.out.println("╚═══════════════════════════════╝");
        System.out.println(jugador1.getNombre() + " VS " + jugador2.getNombre());

        while (ejecutarTurno()) {
            // El loop continúa hasta que ejecutarTurno() devuelva false
        }
    }

    private void anunciarGanador(PersonajeConEstrategia ganador, PersonajeConEstrategia perdedor) {
        System.out.println("\n╔═══════════════════════════════╗");
        System.out.println("║         ¡BATALLA TERMINADA!   ║");
        System.out.println("╚═══════════════════════════════╝");
        System.out.println("🏆 " + ganador.getNombre() + " GANA con "
                + ganador.getPuntosDeVida() + " HP restantes!");
        System.out.println("💀 " + perdedor.getNombre() + " ha sido derrotado.");
    }

    // Getters útiles para las pruebas
    public PersonajeConEstrategia getJugador1() { return jugador1; }
    public PersonajeConEstrategia getJugador2() { return jugador2; }
    public int getTurnoActual() { return turnoActual; }
}