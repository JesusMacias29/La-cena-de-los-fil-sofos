📘 README profesional y natural (más fluido y con comas)  
🍽️ Cena de los Filósofos — Implementación en Java con Semáforos

Este proyecto implementa el famoso problema de la “Cena de los Filósofos” utilizando Java y la clase Semaphore, con el objetivo de simular cómo varios hilos comparten recursos al mismo tiempo,
y cómo evitar situaciones de bloqueo o acceso incorrecto. La idea es representar a cinco filósofos que alternan entre pensar y comer, usando palillos que son recursos compartidos, y controlando su acceso mediante semáforos para garantizar un funcionamiento seguro y ordenado.

JESÚS MACÍAS

---

🧠 Objetivo

El propósito del ejercicio es demostrar cómo usar semáforos para:

- Controlar el acceso exclusivo a los palillos

- Evitar interbloqueos limitando cuántos filósofos pueden intentar comer al mismo tiempo

- Reducir la posibilidad de inanición usando semáforos justos

- Mostrar de manera clara cómo se comporta cada filósofo durante la ejecución

---

🏗️ Estructura del proyecto
Filosofo

Representa a cada filósofo, implementa Runnable, y define el ciclo de pensar, intentar comer y comer, usando los semáforos otorgados por la clase principal.

CenaFilosofos

Es la clase principal, crea los semáforos de los palillos y el semáforo “mayordomo”, lanza los hilos de los filósofos y coordina la ejecución general del programa.

---

▶️ Cómo ejecutarlo
Compilar
javac *.java

Ejecutar
java CenaFilosofos

---

📌 Ejemplo de salida
Filósofo 0 está pensando,
Filósofo 2 tiene hambre e intenta comer,
Filósofo 2 ha tomado sus palillos,
Filósofo 2 está comiendo,
Filósofo 2 ha terminado sus comidas,
...
Fin del programa

---

📄 Enlace para el PDF -> 

---

-> CONCLUSIÓN

Este proyecto me ha ayudado a entender mejor cómo funciona la programación multihilo en Java, y sobre todo, 
cómo gestionar recursos compartidos sin que los hilos entren en conflicto. El problema de la cena de los filósofos, 
aunque parece sencillo al principio, te obliga a pensar en situaciones reales donde varios procesos compiten por los mismos recursos, 
y es ahí donde los semáforos se vuelven esenciales. Al implementar la solución, fui comprendiendo cómo evitar el interbloqueo y la 
inanición, y cómo coordinar a varios hilos para que trabajen de forma ordenada y segura. En general, ha sido una práctica muy útil, 
porque demuestra que la concurrencia no es solo teoría, sino algo que requiere atención, organización y una buena estrategia para que 
todo funcione sin problemas.
