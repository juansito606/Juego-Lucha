package com.juego.patrones.factory;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.AtaqueMagico;

/**
 * PATRÓN FACTORY METHOD — Implementación concreta 2
 * 
 * Esta fábrica crea Magos: menos vida, pero ataques más poderosos
 * (aunque a veces fallan).
 */
public class FabricaMago implements FabricaPersonaje {

    @Override
    public PersonajeConEstrategia crearPersonaje(String nombre) {
        // Los magos tienen 80 HP (menos resistentes)
        PersonajeConEstrategia mago = new PersonajeConEstrategia(nombre, 80);
        // Pero usan ataque mágico (más daño, pero puede fallar)
        mago.setEstrategiaAtaque(new AtaqueMagico());

        System.out.println("🧙 Mago creado: " + mago);
        return mago;
    }
}