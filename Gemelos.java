import java.util.Random;

public class Gemelos extends Juego {

    public Gemelos(Tamagochi tamagochi) {
        super(tamagochi);
    }

    @Override
    public void jugar() {
        Utils.escribirConEfecto("Iniciando el juego de Gemelos...", 30);
    }

    public void jugarDados(int apuesta, int dados, int[] numerosSeleccionados) {
        if (apuesta > tamagochi.getDinero()) {
            Utils.escribirConEfecto("\n" + tamagochi.getNombre() + " no tiene suficiente dinero para apostar.", 30);
            return;
        }

        tamagochi.cambiarDinero(-apuesta);
        Random random = new Random();
        int[] resultado = new int[dados];

        for (int i = 0; i < dados; i++) {
            resultado[i] = random.nextInt(6) + 1;
            Utils.escribirConEfecto("\nDado " + (i + 1) + " ha sacado --> " + resultado[i], 30);
        }

        int ganancia = 0;
        for (int i = 0; i < dados; i++) {
            int dado = resultado[i];
            int numero = numerosSeleccionados[i];
            if (dado == numero) {
                ganancia += apuesta * 3;
            } else if (dado == numero - 1 || dado + 1 == numero) {
                ganancia += apuesta * 2;
            } else {
                ganancia -= apuesta;
            }
        }

        tamagochi.cambiarDinero(ganancia);
        Utils.escribirConEfecto(ganancia > 0
                ? "\n" + tamagochi.getNombre() + " ha ganado --> " + ganancia + " $"
                : "\n" + tamagochi.getNombre() + " ha perdido --> " + (-ganancia) + " $", 30);

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
