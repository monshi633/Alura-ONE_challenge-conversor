<h1 align="center"> Conversor de unidades </h1>
<h2 align="center"> monedas y  temperaturas</h2>

<p align="center">
  <img src="https://img.shields.io/badge/Alura_ONE-Challenge%232-orange">
  <img src="https://img.shields.io/badge/Status-finalizado-blue"><br>
  <img src="https://img.shields.io/badge/JRE-20-red">
</p>

## Tecnologias-utilizadas
* Java
* JavaSwing - windowBuilder
* [AnyAPI](https://anyapi.io/app/currency-exchange-api) - Conversión de monedas
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) - JSON management
* [Flatlaf](https://www.formdev.com/flatlaf) - Look and Feel

## Introducción
<p>Este conversor recibe un valor de entrada que debe ser numérico y mayor a 0, y devuelve su equivalente en la unidad seleccionada.<br>
El usuario se encuentra con dos opciones para elegir, monedas o temperaturas. Una vez seleccionadas, elije las unidades a convertir.</p>

## Uso de API
<p>Este programa usa una API gratuita para convertir monedas en tiempo eral, por lo que la selección de tipos de cambio y la velocidad de las consultas se encuentran limitadas.<br>
Está desarrollada la opción de quitar el botón de convertir y que las unidades se conviertan instantáneamente pero sólo funciona correctamente con las temperaturas (debido a que no requiere consultas externas).</p>

## Uso del programa
### Inicio

![1_inicio](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/3b2370f5-6ecb-4e66-813e-90a71b474cdf)

<em>Selección de Temperatura</em>

![2_temp](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/ce0a4172-6ab2-42d7-9a60-106996163ea0)

<em>Distintas temperaturas</em>

![3_selector](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/66f2807c-2f88-4224-8e9f-ccff3a17ed9d)

<em>Distintas monedas</em>

![4_selector](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/9e25234f-1655-4215-a293-b9e03f4e9820)

<p>No es posible seleccionar la misma unidad en ambas cajas. Al intentar pasar de, por ejemplo, Celsius a Celsius, la primera caja cambia automáticamente al valor siguiente en las opciones para evitar una conversión redundante</p>
<p>Así mismo, el botón de Convertir no se habilita hasta que haya un valor correcto ingesado en el campo de texto</p>

![5_convertir](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/64b53216-3b27-4053-aa69-09ce2bf2bef4)


### Validación de input
<em>Parte del proceso de validación consiste en identificar si el valor ingresado es un número negativo</em>

![6_error](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/dd637b7a-4b7b-4002-817c-92ee50907bbc)

<em>o si se utilizaron caracteres no admitidos</em>

![7_error](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/5effe7c3-a01d-42cd-bd16-227d281b1eb6)

### Resultado
<p>Presionado el botón de Convertir, se realiza la conversión del valor ingresado por las unidades seleccionadas y devuelve un valor con hasta 3 dígitos después del entero</p>

![8_resultado](https://github.com/monshi633/Alura-ONE_challenge-conversor/assets/116769785/76142909-59c2-4703-a5c8-ff246d9bf778)


## Posibles mejoras
* Adición de más tipos de conversión (peso, medida, bytes, etc)
* Elección de una API con mayores tipos de cambio y/o mayor cantidad de consultas por minuto
* Conversión automática (eliminar necesidad de botón)
* Doble input para agilizar la conversión de unidades (o botón para invertir unidades)
