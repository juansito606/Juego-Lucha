package com.juego.patrones.decorator;

import com.juego.model.Personaje;
import com.juego.model.PersonajeConEstrategia;

/**
 * PATRÓN DECORATOR — Implementación concreta
 * 
 * Agrega una espada al personaje: cada ataque hace 10 puntos
 * de daño ADICIONAL al daño normal de su estrategia.
 * 
 * ¿Cómo funciona la magia?
 * 1. Se llama a atacar() en el PersonajeConEspada
 * 2. Primero ejecuta el ataque NORMAL del personaje decorado
 * 3. DESPUÉS aplica el daño bonus de la espada
 * 
 * El personaje no sabe que tiene espada — la espada "envuelve" al personaje.
 */
public class PersonajeConEspada extends DecoradorPersonaje {

    private static final int DANO_ESPADA = 10;
    private String nombreEspada;

    public PersonajeConEspada(PersonajeConEstrategia personaje, String nombreEspada) {
        super(personaje);
        this.nombreEspada = nombreEspada;
    }

    @Override
    public void atacar(Personaje oponente) {
        // Primero el ataque normal
        super.atacar(oponente);
        // Luego el daño extra de la espada
        oponente.recibirDano(DANO_ESPADA);
        System.out.println("  + " + getNombre() + " usa '" + nombreEspada
                + "' para " + DANO_ESPADA + " daño adicional! 🗡️");
    }

    public String getNombreEspada() {
        return nombreEspada;
    }
}