## Mutant Challenge
Proyecto que detecta si un humano es mutante basándose 
en su secuencia de ADN y genera estadísticas de las 
validaciones realizadas.

#### Desafios completados (3 de 3):
* Nivel 1: Programa en Java que cumpla con el método pedido por Magneto.
* Nivel 2: API REST
* Nivel 3: Base de datos Postgres, la cual guarde los ADN’s verificados con la API.

#### Premisas
* La secuencia de ADN debe tener un tamaño de NxN.
* La secuencia de ADN solo permite los siguientes caracteres: (A,T,C,G)


### 2. API REST

### 2.1. Servicio que valida si un humano es mutante en base a su secuencia de ADN

    curl -X POST \
    http://localhost:8081/api/mutant \
    -H 'Content-Type: application/json' \
    -d '{"dna":["ATGA","TATT","TTAA", "gAAA"] }'


Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.

* #### Parametro de entrada:
    JSON con la cadena de ADN a validar
    - Ejemplo: `{"dna":["ATGA","TATT","TTAA", "gAAA"] }` 
  
* #### Códigos de respuesta:
    - 403: Es humano.
    - 200: Es mutante.
    - 500: Error de la aplicación.
    - 400: Error en la secuencia de ADN enviada a validar.


### 2.2. Servicio que genera estadisticas de las validaciones de ADN realizadas.

    curl -X GET http://localhost:8081/api/stats 

* #### Códigos de respuesta:
    - 200: Ejecución exitosa, Ejemplo: 
    
    `{"count_mutant_dna": 4, "count_human_dna": 2, "ratio": 2}`

### 3. Pruebas

Se realizaron pruebas de integración con Junit y se generó el reporte de cobertura
con Jacoco.

* #### Reporte de pruebas
![Alt text](./src/test/coverage.png?raw=true "Title")

Nota: Comando ejecutado para generar el reporte `mvn test`.
