import java.util.concurrent.Semaphore;

public class CenaFilosofos {

    // número de filósofos y de palillos
    public static final int NUM_FILOSOFOS = 5;

    // número de veces que cada filósofo va a comer antes de terminar
    public static final int COMIDAS_POR_FILOSOFO = 3;

    public static void main(String[] args) {

        // he creado un semáforo por cada palillo que son 5 en total, con 1 permiso cada uno
        Semaphore[] palillos = new Semaphore[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            palillos[i] = new Semaphore(1, true);
        }

        Semaphore mayordomo = new Semaphore(NUM_FILOSOFOS - 1, true);

        // he creado los hilos de los filósofos
        Thread[] hilos = new Thread[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {

            // el palillo de la izquierda e derecha del filósofo i
            Semaphore palilloIzq = palillos[i];
            Semaphore palilloDer = palillos[(i + 1) % NUM_FILOSOFOS];

            Filosofo filosofo = new Filosofo(
                    i,
                    palilloIzq,
                    palilloDer,
                    mayordomo,
                    COMIDAS_POR_FILOSOFO
            );

            Thread hilo = new Thread(filosofo, "Filosofo-" + i);
            hilos[i] = hilo;
            hilo.start();
        }

        // espera a que todos los filósofos terminen
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("El hilo principal ha sido interrumpido mientras esperaba a los filósofos.");
            }
        }

        System.out.println("Todos los filósofos han comido. Fin del programa.");
    }
}
