package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

/**
 * PATRÓN STRATEGY — Implementación concreta 2
 * 
 * Ataque mágico: daño entre 15 y 45, pero tiene 30% de probabilidad
 * de fallar (el mago pierde concentración).
 * 
 * Este es el PODER del patrón Strategy: podemos tener comportamientos
 * completamente distintos sin tocar la clase Personaje.
 */
public class AtaqueMagico implements EstrategiaAtaque {

    private static final int MIN_DANO = 15;
    private static final int MAX_DANO = 45;
    private Random random;

    public AtaqueMagico() {
        this.random = new Random();
    }

    @Override
    public void atacar(Personaje atacante, Personaje defensor) {
        // 30% de probabilidad de fallar
        if (random.nextDouble() < 0.30) {
            System.out.println(atacante.getNombre() + " intenta un ATAQUE MÁGICO "
                    + "pero pierde la concentración. ¡Falla!");
            return;
        }

        int dano = random.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
        defensor.recibirDano(dano);
        System.out.println(atacante.getNombre() + " usa ATAQUE MÁGICO sobre "
                + defensor.getNombre() + " causando " + dano + " puntos de daño mágico. ✨");
    }
}