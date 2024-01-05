# Cryptocurrencies Notifier

## Tabla de contenidos

- [1. Introducción](#Introducción)
- [2. Descripción](#Descripción)
- [3. Instalación](#Instalación)
- [4. Ejecución](#Ejecución)
- [5. Autor](#Autor)

---

## 1. Introducción

El proyecto consiste en una aplicación desarrollada en Java 
que permite al usuario recibir información en tiempo real sobre criptomonedas. 
La aplicación obtiene los datos de las criptomonedas a través de una API 
y se encarga de notificar a los usuarios suscritos acerca de los precios y otros datos 
relevantes que se actualizan periódicamente.

---

## 2. Descripción 

El proyecto utiliza el patrón de diseño Observador y la arquitectura de software Modelo-Vista-Controlador
permitiendo una mejorar la escalabilidad y adaptación del código a nuevos cambios.

La aplicación tiene dos visualizaciones diferentes. Una de ellas es por CLI (Command Line Interfaces)
y la otra es por GUI (Graphical User Interface) que permite una visualización interactiva y
un cuadro de mando que permite la simulación de gráficos para representar los datos obtenidos. En el directorio raíz del proyecto se encuentran las dos versiones generadas en ficheros .JAR

----

## 3. Instalación

Para instalar el proyecto, se debe clonar el repositorio y ejecutar el siguiente comando:

```bash
git clone git@github.com:Fabrizzioperilli/CryptocurrenciesNotifier.git
```
---

## 4. Ejecución

Para ejecutar el proyecto, se debe ejecutar el siguiente comando:

```bash
mvn exec:java
```

Este comando se debe ejecutar desde la carpeta raíz del proyecto y debe tener instalado Maven. También se puede ejecutar el proyecto desde el IDE de desarrollo (IntelliJ IDEA).

---


## 5. Autor

- Fabrizzio Daniell Perilli Martín alu0101138589@ull.edu.es

---

> [!IMPORTANT]
> Asegúrate de estar conectado a internet ya que los datos se obtienen en tiempo real a través
> de la API de [Coincap](https://coincap.io).


> [!NOTE]
> Para más información sobre el proyecto, consultar la documentación en el directorio /doc.

---

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=Fabrizzioperilli_CryptocurrenciesNotifier)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Fabrizzioperilli_CryptocurrenciesNotifier&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Fabrizzioperilli_CryptocurrenciesNotifier)

![GitHub repo size](https://img.shields.io/github/repo-size/Fabrizzioperilli/https%3A%2F%2Fgithub.com%2FFabrizzioperilli%2FCryptocurrenciesNotifier.git)
