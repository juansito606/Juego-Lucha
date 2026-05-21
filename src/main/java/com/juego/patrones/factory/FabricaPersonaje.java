package com.juego.patrones.factory;

import com.juego.model.PersonajeConEstrategia;

/**
 * PATRÓN FACTORY METHOD — Interfaz
 * 
 * ¿Qué es Factory Method?
 * Define una interfaz para crear objetos, pero deja que las
 * subclases decidan QUÉ objeto crear.
 * 
 * ¿Por qué no usar simplemente "new"?
 * Si usas "new GuerreroFuerte()" en 10 lugares de tu código,
 * y el día de mañana cambias cómo se crea un GuerreroFuerte,
 * tienes que cambiarlo en 10 lugares. Con Factory, lo cambias
 * en UN solo lugar: la fábrica.
 * 
 * Además, el código que usa personajes no necesita saber
 * los detalles de cómo se crean — solo le pide a la fábrica.
 */
public interface FabricaPersonaje {

    /**
     * Crea y devuelve un personaje listo para pelear.
     * Cada fábrica concreta decide qué tipo de personaje crear.
     * 
     * @param nombre El nombre del personaje
     * @return Un PersonajeConEstrategia configurado y listo
     */
    PersonajeConEstrategia crearPersonaje(String nombre);
}