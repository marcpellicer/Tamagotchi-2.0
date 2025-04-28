import java.util.Random;
import java.util.Scanner;

public class Juego26 {
    private Tamagochi tamagochi;

    public Juego26(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public void jugar26(int apuesta) {
        if (apuesta > tamagochi.getDinero()) {
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " no tiene suficiente dinero para apostar.");
            return;
        }

        tamagochi.cambiarDinero(-apuesta);

        int puntosJugador = 0;
        int puntosMaquina = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.print("Presiona Enter para lanzar el dado: ");
            scanner.nextLine();
            int dadoJugador = random.nextInt(6) + 1;
            puntosJugador += dadoJugador;
            System.out.println("Jugador 1 (Tamagochi) ha sacado --> " + dadoJugador);
            if (puntosJugador > 26) {
                puntosJugador -= dadoJugador;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            int dadoMaquina = random.nextInt(6) + 1;
            puntosMaquina += dadoMaquina;
            System.out.println("Jugador 2 (Máquina) ha sacado --> " + dadoMaquina);
            if (puntosMaquina > 26) {
                puntosMaquina -= dadoMaquina;
            }

            System.out.println("");
            System.out.println("Jugador 1 (Tamagochi): " + puntosJugador);
            System.out.println("Jugador 2 (Máquina): " + puntosMaquina);

            if (puntosJugador == 26 || puntosMaquina == 26) {
                break;
            }
        }

        int ganancia = 0;
        if (puntosJugador == 26) {
            ganancia = apuesta * 2;
            tamagochi.cambiarDinero(ganancia);
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " ha ganado " + ganancia + " $");
        } else {
            ganancia = -apuesta;
            tamagochi.cambiarDinero(ganancia);
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " ha perdido " + (-ganancia) + " $");
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
