import java.util.Random;
import java.util.Scanner;

public class Juego26 extends Juego {

    public Juego26(Tamagochi tamagochi) {
        super(tamagochi);
    }

    @Override
    public void jugar() {
        Utils.escribirConEfecto("Iniciando el juego del 26...", 30);
    }

    public void jugar26(int apuesta) {
        if (apuesta > tamagochi.getDinero()) {
            Utils.escribirConEfecto("\n" + tamagochi.getNombre() + " no tiene suficiente dinero para apostar.", 30);
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

        int ganancia = puntosJugador == 26 ? apuesta * 2 : -apuesta;
        tamagochi.cambiarDinero(ganancia);
        Utils.escribirConEfecto(ganancia > 0
                ? "\n" + tamagochi.getNombre() + " ha ganado --> " + ganancia + " $"
                : "\n" + tamagochi.getNombre() + " ha perdido --> " + (apuesta) + " $", 30);

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
