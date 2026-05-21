package com.juego.patrones.decorator;

import com.juego.model.PersonajeConEstrategia;
import com.juego.patrones.strategy.EstrategiaAtaque;
import com.juego.model.Personaje;

/**
 * PATRÓN DECORATOR — Clase base abstracta
 * 
 * ¿Cómo funciona el Decorator?
 * 1. Hereda de PersonajeConEstrategia (ES UN personaje)
 * 2. TAMBIÉN TIENE UN PersonajeConEstrategia (el decorado)
 * 
 * Esto permite "envolver" un personaje y modificar su comportamiento.
 * El "cliente" (el juego) ve un PersonajeConEstrategia normal,
 * sin saber que hay un decorator por dentro.
 * 
 * ¿Por qué hereda Y tiene referencia?
 * Para que sea transparente: puedes usar el decorado
 * en cualquier lugar donde se espera un PersonajeConEstrategia.
 */
public abstract class DecoradorPersonaje extends PersonajeConEstrategia {

    // El personaje que estamos "decorando" (envolviendo)
    protected PersonajeConEstrategia personajeDecorado;

    public DecoradorPersonaje(PersonajeConEstrategia personaje) {
        super(personaje.getNombre(), personaje.getPuntosDeVida());
        this.personajeDecorado = personaje;
        // Heredamos la estrategia del personaje original
        this.setEstrategiaAtaque(personaje.getEstrategiaAtaque());
    }

    @Override
    public void atacar(Personaje oponente) {
        // Por defecto, delega al personaje decorado
        personajeDecorado.atacar(oponente);
    }
}