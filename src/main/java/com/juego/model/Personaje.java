package com.juego.model;

/**
 * Clase base que representa a un personaje del juego.
 * 
 * ¿Por qué existe esta clase?
 * Todo personaje tiene nombre, puntos de vida, y puede atacar o recibir daño.
 * Encapsulamos esos datos y comportamientos aquí.
 * 
 * Nota: En el refinamiento, la lógica de ataque saldrá de aquí
 * y se moverá al patrón Strategy. Por ahora, la dejamos simple.
 */
public class Personaje {

    private String nombre;
    private int puntosDeVida;
    private int puntosDeVidaMaximos;

    /**
     * Constructor básico.
     * Todo personaje empieza con 100 HP.
     * 
     * @param nombre El nombre del personaje
     */
    public Personaje(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.puntosDeVidaMaximos = 100;
    }

    /**
     * Constructor con HP personalizado.
     * Lo usaremos en el patrón Builder más adelante.
     * 
     * @param nombre          Nombre del personaje
     * @param puntosDeVida    HP inicial y máximo
     */
    public Personaje(String nombre, int puntosDeVida) {
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
        this.puntosDeVidaMaximos = puntosDeVida;
    }

    /**
     * Reduce los puntos de vida del personaje.
     * Nunca puede bajar de 0 (no tiene sentido tener HP negativo).
     * 
     * @param dano Cantidad de daño recibido (debe ser positivo)
     */
    public void recibirDano(int dano) {
        if (dano < 0) return; // Ignoramos daño negativo (trampa)
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0;
        }
    }

    /**
     * ¿El personaje sigue vivo?
     * Está vivo si tiene al menos 1 punto de vida.
     */
    public boolean estaVivo() {
        return this.puntosDeVida > 0;
    }

    // ─── Getters ────────────────────────────────────────────────────────────

    public String getNombre() {
        return nombre;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public int getPuntosDeVidaMaximos() {
        return puntosDeVidaMaximos;
    }

    @Override
    public String toString() {
        return nombre + " [HP: " + puntosDeVida + "/" + puntosDeVidaMaximos + "]";
    }
}