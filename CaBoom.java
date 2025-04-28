import java.util.Random;
import java.util.Scanner;

public class CaBoom {
    private Tamagochi tamagochi;

    public CaBoom(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public void jugarMapa(int apuesta) {
        if (apuesta > tamagochi.getDinero()) {
            System.out.println("");
            System.out.println(tamagochi.getNombre() + " no tiene suficiente dinero para apostar.");
            return;
        }

        tamagochi.cambiarDinero(-apuesta);

        String[][] mapa = new String[5][5];
        boolean[][] visitado = new boolean[5][5];
        Random random = new Random();
        int bombasColocadas = 0;

        // Colocar bombas en el mapa
        while (bombasColocadas < 4) {
            int i = random.nextInt(5);
            int j = random.nextInt(5);
            if (mapa[i][j] == null) {
                mapa[i][j] = "bomba";
                bombasColocadas++;
            }
        }

        // Inicializar celdas vacías
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mapa[i][j] == null) {
                    mapa[i][j] = "vacío";
                }
                visitado[i][j] = false;
            }
        }

        int[] posicion = {0, 0};
        double ganancia = 0;

        Scanner scanner = new Scanner(System.in);

        // Lógica del juego
        while (true) {
            visitado[posicion[0]][posicion[1]] = true;
            System.out.println("");
            System.out.println("Mapa:");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (i == posicion[0] && j == posicion[1]) {
                        System.out.print("[X] ");
                    } else if (visitado[i][j]) {
                        System.out.print("[O] ");
                    } else {
                        System.out.print("[ ] ");
                    }
                }
                System.out.println();
            }

            System.out.println("");
            System.out.print("Introduce una dirección (arriba, abajo, izquierda, derecha) o 'parar' para detener: ");
            String direccion = scanner.nextLine();
            if (direccion.equals("parar")) {
                break;
            }

            // Movimiento del jugador
            switch (direccion) {
                case "arriba":
                    if (posicion[0] > 0 && !visitado[posicion[0] - 1][posicion[1]]) {
                        posicion[0]--;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "abajo":
                    if (posicion[0] < 4 && !visitado[posicion[0] + 1][posicion[1]]) {
                        posicion[0]++;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "izquierda":
                    if (posicion[1] > 0 && !visitado[posicion[0]][posicion[1] - 1]) {
                        posicion[1]--;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                case "derecha":
                    if (posicion[1] < 4 && !visitado[posicion[0]][posicion[1] + 1]) {
                        posicion[1]++;
                    } else {
                        System.out.println("Movimiento no válido.");
                    }
                    break;
                default:
                    System.out.println("");
                    System.out.println("Dirección no válida.");
                    break;
            }

            // Verificar si cae en una bomba
            if (mapa[posicion[0]][posicion[1]].equals("bomba")) {
                System.out.println("");
                System.out.println("¡BOOOM! Has encontrado una bomba, has perdido tu dinero.");
                ganancia = -apuesta;
                break;
            } else {
                ganancia += 1.3 * apuesta;
            }
            System.out.println("");
            System.out.println("Ganancia actual: " + ganancia + " $");
        }

        tamagochi.cambiarDinero((int) ganancia);
        if (ganancia > 0) {
            System.out.println(tamagochi.getNombre() + " ha ganado " + ganancia + " $");
        } else {
            System.out.println(tamagochi.getNombre() + " ha perdido " + (-ganancia) + " $");
        }

        System.out.println("GANANCIA: " + ganancia + " $");

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
