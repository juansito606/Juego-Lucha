package com.juego.model;

import com.juego.patrones.strategy.EstrategiaAtaque;
import com.juego.patrones.strategy.AtaqueFisico;

/**
 * Personaje que usa el patrón Strategy para atacar.
 * 
 * ¿Cómo se conecta con Strategy?
 * Este personaje TIENE UNA EstrategiaAtaque. Cuando ataca,
 * le pide a su estrategia que haga el trabajo.
 * 
 * Esto se llama COMPOSICIÓN: en lugar de heredar el comportamiento,
 * lo tenemos como un atributo que podemos cambiar en tiempo de ejecución.
 * 
 * ¿Cuándo usar herencia vs composición?
 * Herencia: cuando la relación es "ES UN" (Guerrero ES UN Personaje)
 * Composición: cuando la relación es "TIENE UN" (Personaje TIENE UN ataque)
 */
public class PersonajeConEstrategia extends Personaje {

    // El tipo de ataque es un objeto separado, intercambiable
    private EstrategiaAtaque estrategiaAtaque;

    public PersonajeConEstrategia(String nombre) {
        super(nombre);
        // Por defecto, ataque físico
        this.estrategiaAtaque = new AtaqueFisico();
    }

    public PersonajeConEstrategia(String nombre, int puntosDeVida) {
        super(nombre, puntosDeVida);
        this.estrategiaAtaque = new AtaqueFisico();
    }

    /**
     * Cambia la estrategia de ataque en tiempo de ejecución.
     * Esto es el PODER del patrón Strategy: el comportamiento
     * puede cambiar SIN cambiar la clase Personaje.
     */
    public void setEstrategiaAtaque(EstrategiaAtaque estrategia) {
        this.estrategiaAtaque = estrategia;
    }

    public EstrategiaAtaque getEstrategiaAtaque() {
        return estrategiaAtaque;
    }

    /**
     * Ataca al oponente usando la estrategia actual.
     * El personaje no sabe CÓMO se calcula el daño — se lo delega a la estrategia.
     */
    public void atacar(Personaje oponente) {
        estrategiaAtaque.atacar(this, oponente);
    }
}