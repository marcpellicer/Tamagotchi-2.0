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
            Utils.escribirConEfecto("\nPresiona Enter para lanzar el dado: ", 30);
            scanner.nextLine();
            int dadoJugador = random.nextInt(6) + 1;
            puntosJugador += dadoJugador;
            Utils.escribirConEfecto("Jugador 1 (Tamagochi) ha sacado --> " + dadoJugador, 30);
            if (puntosJugador > 26) {
                puntosJugador -= dadoJugador;
            }

            int dadoMaquina = random.nextInt(6) + 1;
            puntosMaquina += dadoMaquina;
            Utils.escribirConEfecto("Jugador 2 (Máquina) ha sacado --> " + dadoMaquina, 30);
            if (puntosMaquina > 26) {
                puntosMaquina -= dadoMaquina;
            }

            Utils.escribirConEfecto("\nJugador 1 (Tamagochi): " + puntosJugador, 30);
            Utils.escribirConEfecto("Jugador 2 (Máquina): " + puntosMaquina, 30);

            if (puntosJugador == 26 || puntosMaquina == 26) {
                break;
            }
        }

        
        if (puntosJugador == puntosMaquina) {
            tamagochi.cambiarDinero(apuesta); // Recuperar dinero inicial en caso de empate
            Utils.escribirConEfecto("\n¡Empate! Recuperas tu apuesta inicial.", 30);
        } else if (puntosJugador == 26) {
            tamagochi.cambiarDinero(apuesta * 2);
            Utils.escribirConEfecto("\n¡Has ganado! Gancia en el juego --> " + (apuesta * 2) + " $", 30);
        } else {
            tamagochi.cambiarDinero(-apuesta * 3);
            Utils.escribirConEfecto("\n¡Has perdido! Pérdida en el juego --> " + (apuesta * 3) + " $", 30);
        }

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
