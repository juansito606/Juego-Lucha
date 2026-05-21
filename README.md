# Juego de Lucha - Patrones de Diseño en Java

![Java CI con Maven](https://github.com/TU_USUARIO/juego-lucha-patrones/actions/workflows/ci.yml/badge.svg)

Juego de lucha por turnos implementado en Java, con refinamiento
arquitectónico usando patrones de diseño creacionales y estructurales.

## Patrones de Diseño Implementados

### Factory Method (Creacional)
Permite crear diferentes tipos de personajes sin usar `new` directamente.
- `FabricaGuerrero` → Personaje con 120 HP y ataque físico
- `FabricaMago` → Personaje con 80 HP y ataque mágico
- `FabricaAsesino` → Personaje con 90 HP y ataques críticos

### Strategy (Estructural/Comportamiento)
Encapsula diferentes algoritmos de ataque, intercambiables en tiempo de ejecución.
- `AtaqueFisico` → Daño entre 10 y 30
- `AtaqueMagico` → Daño entre 15 y 45, 30% de fallo
- `AtaqueCritico` → Daño base o doble (40% crítico)

### Decorator (Estructural)
Agrega funcionalidad a un personaje sin modificar su clase.
- `PersonajeConEspada` → +10 daño por ataque

## Tecnologías

- Java 17
- Maven 3.8+
- JUnit 5.10
- Mockito 5.5
- JaCoCo (cobertura de código)
- GitHub Actions (CI/CD)
- GitHub Codespaces (entorno de desarrollo)

## Cómo ejecutar

```bash
# Compilar
mvn clean compile

# Ejecutar pruebas
mvn test

# Ver reporte de cobertura
mvn jacoco:report
# Abrir: target/site/jacoco/index.html
```

## Estructura del proyecto

```
src/
├── main/java/com/juego/
│   ├── model/
│   │   ├── Personaje.java
│   │   └── PersonajeConEstrategia.java
│   ├── patrones/
│   │   ├── factory/
│   │   │   ├── FabricaPersonaje.java (interfaz)
│   │   │   ├── FabricaGuerrero.java
│   │   │   ├── FabricaMago.java
│   │   │   └── FabricaAsesino.java
│   │   ├── strategy/
│   │   │   ├── EstrategiaAtaque.java (interfaz)
│   │   │   ├── AtaqueFisico.java
│   │   │   ├── AtaqueMagico.java
│   │   │   └── AtaqueCritico.java
│   │   └── decorator/
│   │       ├── DecoradorPersonaje.java
│   │       └── PersonajeConEspada.java
│   └── juego/
│       ├── JuegoLucha.java
│       └── Main.java
└── test/java/com/juego/
    ├── model/PersonajeTest.java
    ├── patrones/
    │   ├── EstrategiaAtaqueTest.java
    │   ├── FabricaPersonajeTest.java
    │   └── DecoradorTest.java
    └── juego/JuegoLuchaTest.java
```