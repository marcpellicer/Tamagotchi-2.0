import java.util.Random;

public class Juego26 extends Juego {

    public Juego26(Tamagochi tamagochi) {
        super(tamagochi);
    }

    @Override
    public void jugar26(int apuesta) {
        if (apuesta > tamagochi.getDinero()) {
            Utils.escribirConEfecto("\n" + tamagochi.getNombre() + " no tiene suficiente dinero para apostar.", 30);
            return;
        }

        tamagochi.cambiarDinero(-apuesta);
        int puntosJugador = 0;
        int puntosMaquina = 0;
        Random random = new Random();

        while (true) {
            int dadoJugador = random.nextInt(6) + 1;
            puntosJugador += dadoJugador;
            if (puntosJugador > 26) puntosJugador -= dadoJugador;

            int dadoMaquina = random.nextInt(6) + 1;
            puntosMaquina += dadoMaquina;
            if (puntosMaquina > 26) puntosMaquina -= dadoMaquina;

            if (puntosJugador == 26 || puntosMaquina == 26) break;
        }

        int ganancia = GameRules.calcularGanancia("Juego26", puntosJugador, apuesta);
        GameRules.aplicarCambiosEstado(tamagochi, ganancia, 10, 6);

        Utils.escribirConEfecto(ganancia > 0 ?
                tamagochi.getNombre() + " ha ganado $" + ganancia :
                tamagochi.getNombre() + " ha perdido $" + (-ganancia), 30);
    }
}
