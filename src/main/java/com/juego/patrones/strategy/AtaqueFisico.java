package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

/**
 * PATRÓN STRATEGY — Implementación concreta 1
 * 
 * Ataque físico clásico: daño aleatorio entre 10 y 30.
 * Es el comportamiento que tenía el personaje original,
 * pero ahora está SEPARADO de la clase Personaje.
 * 
 * ¿Por qué separarlo?
 * Si mañana quieres cambiar cómo funciona el ataque físico,
 * solo modificas ESTA clase, sin tocar Personaje.
 */
public class AtaqueFisico implements EstrategiaAtaque {

    private static final int MIN_DANO = 10;
    private static final int MAX_DANO = 30;
    private Random random;

    public AtaqueFisico() {
        this.random = new Random();
    }

    @Override
    public void atacar(Personaje atacante, Personaje defensor) {
        int dano = random.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
        defensor.recibirDano(dano);
        System.out.println(atacante.getNombre() + " usa ATAQUE FÍSICO sobre "
                + defensor.getNombre() + " causando " + dano + " puntos de daño.");
    }
}