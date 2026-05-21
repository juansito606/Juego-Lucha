package com.juego.patrones.factory;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.AtaqueCritico;

/**
 * PATRÓN FACTORY METHOD — Implementación concreta 3
 * 
 * Asesinos: HP media, pero con probabilidad de golpes críticos devastadores.
 */
public class FabricaAsesino implements FabricaPersonaje {

    @Override
    public PersonajeConEstrategia crearPersonaje(String nombre) {
        PersonajeConEstrategia asesino = new PersonajeConEstrategia(nombre, 90);
        asesino.setEstrategiaAtaque(new AtaqueCritico());

        System.out.println("🗡️  Asesino creado: " + asesino);
        return asesino;
    }
}