# Fábrica de Autopartes – Resolución con Backtracking y Greedy

## 🧩 Descripción del problema

En una fábrica de autopartes se cuenta con un conjunto de máquinas, cada una capaz de producir una cantidad fija de piezas. El objetivo es producir una cantidad total de piezas, utilizando la menor cantidad de **puestas en funcionamiento** posibles.

### Reglas:
- Cada máquina produce una cantidad fija de piezas.
- Solo una máquina puede funcionar a la vez.
- Una misma máquina puede utilizarse varias veces.

Dado un archivo de configuración con la cantidad total de piezas a producir y las máquinas disponibles, se debe determinar una combinación óptima que minimice el número de puestas en funcionamiento usando:

- **Backtracking**: búsqueda exhaustiva con podas.
- **Greedy**: selección golosa basada en una heurística.

---

## 📂 Estructura del archivo de entrada

Formato del archivo (`config.txt` o similar):

12  
M1,7  
M2,3  
M3,4  
M4,1


- La primera línea indica la **cantidad total de piezas a producir**.
- Cada línea siguiente representa una máquina con su nombre y la cantidad de piezas que produce.

---

## ⚙️ Ejecución y resultados esperados

### 📌 Backtracking
- **Solución obtenida**: Secuencia óptima de máquinas.
- **Cantidad de piezas producidas**: Total producido al alcanzar el objetivo.
- **Puestas en funcionamiento**: Número mínimo de máquinas necesarias.
- **Costo computacional**: Cantidad de **estados generados** durante la búsqueda.

### 📌 Greedy
- **Solución obtenida**: Secuencia generada con una heurística golosa.
- **Cantidad de piezas producidas**: Total producido (puede ser óptimo o no).
- **Puestas en funcionamiento**: Cantidad de máquinas usadas en esta estrategia.
- **Costo computacional**: Cantidad de **candidatos considerados**.

---

## 💡 Estrategia de resolución

### 🔍 Backtracking

## Arbol de busqueda

![Texto alternativo](arbol de busqueda.png)


```java
/*
 * Estrategia de resolución:
 * - Se construye un árbol de decisión donde cada nodo representa una secuencia parcial de máquinas.
 * - En cada nivel se agrega una máquina y se calcula la suma acumulada de piezas.
 * - Estado final: suma de piezas acumuladas == objetivo.
 * - Estado inválido (poda): suma acumulada > objetivo.
 * - Se guarda la mejor solución (menor cantidad de máquinas).
 * - Se mide el costo como cantidad de estados generados.
 */

