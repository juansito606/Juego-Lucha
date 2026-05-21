package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

/**
 * PATRÓN STRATEGY — Implementación concreta 3
 * 
 * Ataque crítico: daño base entre 5 y 15, pero tiene 40% de probabilidad
 * de hacer el doble de daño (golpe crítico).
 */
public class AtaqueCritico implements EstrategiaAtaque {

    private static final int MIN_DANO = 5;
    private static final int MAX_DANO = 15;
    private Random random;

    public AtaqueCritico() {
        this.random = new Random();
    }

    @Override
    public void atacar(Personaje atacante, Personaje defensor) {
        int danoBase = random.nextInt(MAX_DANO - MIN_DANO + 1) + MIN_DANO;
        boolean esCritico = random.nextDouble() < 0.40;
        int danoFinal = esCritico ? danoBase * 2 : danoBase;

        defensor.recibirDano(danoFinal);

        if (esCritico) {
            System.out.println(atacante.getNombre() + " ⚡ GOLPE CRÍTICO ⚡ sobre "
                    + defensor.getNombre() + " causando " + danoFinal + " daño (¡doble!)");
        } else {
            System.out.println(atacante.getNombre() + " ataca a "
                    + defensor.getNombre() + " causando " + danoFinal + " daño.");
        }
    }
}