import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ruleta extends Juego {

    public Ruleta(Tamagochi tamagochi) {
        super(tamagochi);
    }

    @Override
    public void jugar() {
        Utils.escribirConEfecto("Iniciando el juego de la Ruleta...", 30);
    }

    public void jugarRuleta(int apuesta, int[] numerosSeleccionados) {
        if (apuesta > tamagochi.getDinero()) {
            Utils.escribirConEfecto("\n" + tamagochi.getNombre() + " no tiene suficiente dinero para apostar.", 30);
            return;
        }

        tamagochi.cambiarDinero(-apuesta);

        Set<Integer> bolasResultados = new HashSet<>();
        int gananciaTotal = 0;

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int bolaResultado;
            do {
                bolaResultado = random.nextInt(20) + 1;
            } while (bolasResultados.contains(bolaResultado));
            bolasResultados.add(bolaResultado);
            Utils.escribirConEfecto("\nLa bola " + (i + 1) + " ha caído en el número: " + bolaResultado, 30);

            boolean acertado = false;
            for (int numero : numerosSeleccionados) {
                if (bolaResultado == numero) {
                    acertado = true;
                    break;
                }
            }

            if (acertado) {
                int perdida = apuesta * 3;
                gananciaTotal -= perdida;
                Utils.escribirConEfecto("Ganancia --> -" + perdida + " $", 30);
            } else {
                int ganancia = apuesta * 2;
                gananciaTotal += ganancia;
                Utils.escribirConEfecto("Ganancia --> " + ganancia + " $", 30);
            }
        }

        int totalDinero = -apuesta + gananciaTotal;
        tamagochi.cambiarDinero(totalDinero);

        Utils.escribirConEfecto(totalDinero > 0
                ? "\n" + tamagochi.getNombre() + " ha ganado en total --> " + totalDinero + " $"
                : "\n" + tamagochi.getNombre() + " ha perdido en total --> " + (-totalDinero) + " $", 30);

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
