import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {

    private final int id;
    private final Semaphore palilloIzq;
    private final Semaphore palilloDer;
    private final Semaphore mayordomo;
    private final int comidasObjetivo;
    private final Random random;

    private int comidasRealizadas = 0;

    public Filosofo(int id,
                    Semaphore palilloIzq,
                    Semaphore palilloDer,
                    Semaphore mayordomo,
                    int comidasObjetivo) {
        this.id = id;
        this.palilloIzq = palilloIzq;
        this.palilloDer = palilloDer;
        this.mayordomo = mayordomo;
        this.comidasObjetivo = comidasObjetivo;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (comidasRealizadas < comidasObjetivo) {
            pensar();
            intentarComer();
        }
        System.out.println("Filósofo " + id + " ha terminado sus comidas.");
    }

    private void pensar() {
        System.out.println("Filósofo " + id + " está pensando.");
        dormirUnMomento(1000, 2000);
    }

    private void intentarComer() {
        System.out.println("Filósofo " + id + " tiene hambre e intenta comer.");

        try {
            // el mayordomo limita el número de filósofos que pueden intentar comer
            mayordomo.acquire();

            // he elegido un orden global para tomar los palillos y así evitar espera circular
            Semaphore primero;
            Semaphore segundo;

            // uso el System.identityHashCode para definir un orden consistente
            if (System.identityHashCode(palilloIzq) < System.identityHashCode(palilloDer)) {
                primero = palilloIzq;
                segundo = palilloDer;
            } else {
                primero = palilloDer;
                segundo = palilloIzq;
            }
            // coge los palillos
            primero.acquire();
            System.out.println("Filósofo " + id + " ha cogido su primer palillo.");
            segundo.acquire();
            System.out.println("Filósofo " + id + " ha cogido su segundo palillo.");

            try {
                comer();
            } finally {
                // suelto los palillos en orden contrario
                segundo.release();
                primero.release();
                System.out.println("Filósofo " + id + " ha soltado ambos palillos.");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Filósofo " + id + " fue interrumpido.");
        } finally {
            // libero el permiso del mayordomo
            mayordomo.release();
        }
    }

    private void comer() {
        comidasRealizadas++;
        System.out.println(
                "Filósofo " + id + " está comiendo. (Comida " +
                comidasRealizadas + " de " + comidasObjetivo + ")"
        );
        dormirUnMomento(500, 1500);
    }

    private void dormirUnMomento(int minMs, int maxMs) {
        int tiempo = minMs + random.nextInt(maxMs - minMs + 1);
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
