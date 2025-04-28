import java.util.Random;

public class Gemelos {
    private Tamagochi tamagochi;

    public Gemelos(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public void jugarDados(int apuesta, int dados, int[] numerosSeleccionados) {
        if (apuesta > tamagochi.getDinero()) {
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " no tiene suficiente dinero para apostar.");
            return;
        }

        tamagochi.cambiarDinero(-apuesta);

        Random random = new Random();
        int[] resultado = new int[dados];
        for (int i = 0; i < dados; i++) {
            resultado[i] = random.nextInt(6) + 1;
            System.out.println("");
            System.out.println("Dado " + (i + 1) + " ha sacado --> " + resultado[i]);
        }

        int ganancia = 0;
        for (int i = 0; i < dados; i++) {
            int dado = resultado[i];
            int numero = numerosSeleccionados[i];
            if (dado == numero) {
                ganancia += apuesta * 3;
            } else if (dado == numero - 1 || dado == numero + 1) {
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
