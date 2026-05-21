package com.juego.juego;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.decorator.PersonajeConEspada;
import com.juego.patrones.factory.*;

/**
 * Punto de entrada del programa.
 * Aquí demostramos todos los patrones funcionando juntos.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   JUEGO DE LUCHA - PATRONES DE DISEÑO ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // ── PATRÓN FACTORY: Creamos personajes sin usar "new" directamente ──
        System.out.println("--- Creando personajes con Factory Method ---");
        FabricaPersonaje fabricaGuerrero = new FabricaGuerrero();
        FabricaPersonaje fabricaMago = new FabricaMago();

        PersonajeConEstrategia thor = fabricaGuerrero.crearPersonaje("Thor");
        PersonajeConEstrategia merlin = fabricaMago.crearPersonaje("Merlin");

        // ── PATRÓN DECORATOR: Equipamos a Thor con una espada ──
        System.out.println("\n--- Equipando armas con Decorator ---");
        PersonajeConEstrategia thorConEspada = new PersonajeConEspada(thor, "Mjolnir");
        System.out.println("Thor ahora porta Mjolnir ⚡");

        // ── El juego usa los personajes sin importar si están decorados o no ──
        System.out.println("\n--- Iniciando batalla ---");
        JuegoLucha batalla = new JuegoLucha(thorConEspada, merlin);
        batalla.iniciarBatalla();
    }
}