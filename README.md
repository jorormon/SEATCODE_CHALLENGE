# Nombre del proyecto

Licencia](https://img.shields.io/badge/license-MIT-blue.svg)  
Estado de compilación](https://img.shields.io/badge/build-passing-brightgreen.svg)

## Índice

- Resumen](#resumen)
- Características](#características)
- Capturas de pantalla](#screenshots)
- Primeros pasos](#getting-started)
    - Requisitos previos](#prerequisites)
    - Instalación](#installation)
- Uso](#usage)
- Construido con](#built-with)
- Consideraciones

## Visión general

Este es un proyecto para el SEAT CODE CHALLENGE. Es una aplicación que muestra cómo la nave espacial
sale de la Tierra y llega a Marte. El juego comienza en Marte y el rover se mueve alrededor de Marte en la
ruta indicada desde la Tierra con el objetivo de encontrar agua. ¿La encontrará?

## Características

- Paseo Marciano del Rover (Iniciar, parar y reiniciar la marcha)

## Capturas de pantalla

Se incluye un video del funcionamiento de la app:

![Screenshot](video/videoExample.mp4)

## Primeros pasos

Instrucciones para ayudar a otros a configurar y ejecutar el proyecto localmente.

### Prerrequisitos

- Android Studio](https://developer.android.com/studio) instalado
- Versión mínima del SDK: 28
- Versión SDK objetivo: 34
- Versión de Kotlin: 2.1.20
- Koin: 4.0.3
- lottie : 6.6.6
- mockitoCore : 5.17.0

### Instalación

1. Clonar el repositorio
   ```bash
   git clone https://github.com/jorormon/SEATCODE_CHALLENGE
2. Abre el proyecto en Android Studio
3. Sincroniza el proyecto con los archivos de Gradle
4. Ejecuta la app en un emulador o dispositivo físico

## Construido con

- Jetpack Compose](https://developer.android.com/jetpack/compose) - Kit de herramientas de interfaz de usuario
- Kotlin](https://kotlinlang.org/) - Lenguaje de programación


### Consideraciones

Inyección de dependencias:
Se ha considerado usar Koin para la inyección de dependencias debido a su simplicidad y facilidad de integración con proyectos Kotlin y Jetpack Compose.
Koin provee dependencias en tiempo de ejecución lo cual puede ocasionar problemas, ya que no detectas los fallos en la inyección hasta que no accedes a la pantalla concreta. En cambio Hilt, funciona en tiempo de compilación, lo cual proporciona seguridad a la hora de ejecutar una aplicación ya que sabes que no vas a tener problemas con la inyección mientras se esté ejecutando.

Presentation Architecture:
Se ha considerado utilizar la arquitectura MVI ya que nos permite gestionar de manera adecuada el estado de la pantalla con un único estado de la vista y esto a su vez nos permite testear el mismo. 
El flujo de datos con MVI es unidireccional: desde el modelo a la vista y de vuelta como Intents. Esto reduce las posibilidades de errores y condiciones de carrera, ya que es más fácil de razonar sobre la manera en que los datos fluyen por la aplicación.

Clean Architecture: 
Siguiendo los patrones de Clean Architecture se ha creado una arquitectura con los modulos app domain data framework, presentation y el modulo di que sirve como bridge para proporcionar todas las dependencias y permitir que no se rompa el patrón al proveer las dependencias.
Se ha creado un modulo presentation que nos permita separar la logica de presentación de las vistas (app), de esta manera mantenemos abstraidas las vistas en base a un modelo de vista. El modulo de app no verá al modulo de domain asi evitaremos caer en el error de usar modelos en las vistas que no correspondan.

Framework:
Para la lectura del json se ha realizado utilizando la libreria kotlinx.serialization ya que nos permite transformar esta estructura en un objeto de datos. En el caso de que hubiera que cambiar esto a una petición API REST, podriamos utilizar Ktor o Retrofit.
Como hemos planteado el proyecto con Clean Architecture, tenerlo abstraido nos da la ventaja de que este cambio solo afectara al modulo framework.


Integración continua:
Para integración continua, no se ha implementado debido a la naturaleza del proyecto. En un proyecto real lo realizaría con Fastlane, que es una herramienta para automatización de código y que junto con GitHub Actions nos permite definir flujos de trabajo automatizados directamente en el repositorio, lo cual nos permite ejecutar scripts de Fastlane de manera continua.

