import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ruleta {
    private Tamagochi tamagochi;

    public Ruleta(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public void jugarRuleta(int apuesta, int[] numerosSeleccionados) {
        if (apuesta > tamagochi.getDinero()) {
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " no tiene suficiente dinero para apostar.");
            return;
        }

        tamagochi.cambiarDinero(-apuesta);

        Set<Integer> bolasResultados = new HashSet<>();
        int ganancia = 0;

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int bolaResultado;
            do {
                bolaResultado = random.nextInt(20) + 1;
            } while (bolasResultados.contains(bolaResultado));
            bolasResultados.add(bolaResultado);
            System.out.println("");
            System.out.println("La bola " + (i + 1) + " ha caído en el número: " + bolaResultado);

            boolean acertado = false;
            for (int numero : numerosSeleccionados) {
                if (bolaResultado == numero) {
                    acertado = true;
                    break;
                }
            }

            if (acertado) {
                ganancia += apuesta * 2;
            } else {
                ganancia -= apuesta;
            }
        }

        tamagochi.cambiarDinero(ganancia);
        if (ganancia > 0) {
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " ha ganado " + ganancia + " $");
        } else {
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
