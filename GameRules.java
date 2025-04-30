public class GameRules {

    public static int calcularGanancia(String juego, int resultado, int apuesta) {
        switch (juego) {
            case "Ruleta":
                return resultado == 0 ? apuesta * 10 : -apuesta;
            case "Gemelos":
                return resultado > 0 ? apuesta * (2 + resultado) : -apuesta;
            case "CaBoom":
                return resultado > 0 ? (int) (apuesta * (1.5 + resultado / 10.0)) : -apuesta;
            case "Juego26":
                return resultado == 26 ? apuesta * 2 : -apuesta;
            default:
                return -apuesta; // Penalización estándar
        }
    }

    public static void aplicarCambiosEstado(Tamagochi tamagochi, int ganancia, int sueno, int hambre) {
        tamagochi.cambiarDinero(ganancia);
        tamagochi.cambiarSueno(sueno);
        tamagochi.cambiarHambre(hambre);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
