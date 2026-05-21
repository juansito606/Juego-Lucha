package com.juego.patrones.strategy;

import com.juego.model.Personaje;

/**
 * PATRÓN STRATEGY — Interfaz
 * 
 * ¿Qué hace este patrón?
 * Define una FAMILIA de algoritmos (formas de atacar),
 * los encapsula en clases separadas, y los hace intercambiables.
 * 
 * ¿Por qué una interfaz?
 * Porque todos los tipos de ataque deben tener el método atacar(),
 * pero cada uno lo implementa distinto. La interfaz obliga a que
 * todos cumplan ese "contrato".
 * 
 * ¿Cómo se conecta con Personaje?
 * El Personaje va a TENER UNA EstrategiaAtaque (composición).
 * Cuando alguien llame personaje.atacar(), el personaje
 * delega el trabajo a su estrategia actual.
 */
public interface EstrategiaAtaque {

    /**
     * Ejecuta el ataque del atacante sobre el defensor.
     * 
     * @param atacante El personaje que ataca
     * @param defensor El personaje que recibe el golpe
     */
    void atacar(Personaje atacante, Personaje defensor);
}