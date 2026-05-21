package com.juego.patrones.factory;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.AtaqueFisico;

/**
 * PATRÓN FACTORY METHOD — Implementación concreta 1
 * 
 * Esta fábrica crea Guerreros: personajes con mucha vida
 * y ataque físico fuerte.
 * 
 * ¿Cómo se conecta con Strategy?
 * Al crear el personaje, la fábrica también le asigna
 * su estrategia de ataque. ¡Los patrones se combinan!
 */
public class FabricaGuerrero implements FabricaPersonaje {

    @Override
    public PersonajeConEstrategia crearPersonaje(String nombre) {
        // Los guerreros tienen 120 HP (más que el base de 100)
        PersonajeConEstrategia guerrero = new PersonajeConEstrategia(nombre, 120);
        // Y usan ataque físico
        guerrero.setEstrategiaAtaque(new AtaqueFisico());

        System.out.println("⚔️  Guerrero creado: " + guerrero);
        return guerrero;
    }
}