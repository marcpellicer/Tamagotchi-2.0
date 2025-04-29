import java.util.Random;
import java.util.Scanner;

public class CaBoom {
    private Tamagochi tamagochi;

    public CaBoom(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public void jugarMapa(int apuesta) {
        if (apuesta > tamagochi.getDinero()) {
            Utils.escribirConEfecto("\n" + tamagochi.getNombre() + " no tiene suficiente dinero para apostar.", 30);
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

        while (true) {
            visitado[posicion[0]][posicion[1]] = true;
            Utils.escribirConEfecto("\nMapa:", 30);
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

            Utils.escribirConEfecto("\nIntroduce una dirección (arriba, abajo, izquierda, derecha) o 'parar' para detener: ", 30);
            String direccion = scanner.nextLine();
            if (direccion.equals("parar")) {
                break;
            }

            switch (direccion) {
                case "arriba":
                    if (posicion[0] > 0 && !visitado[posicion[0] - 1][posicion[1]]) {
                        posicion[0]--;
                    } else {
                        Utils.escribirConEfecto("Movimiento no válido.", 30);
                    }
                    break;
                case "abajo":
                    if (posicion[0] < 4 && !visitado[posicion[0] + 1][posicion[1]]) {
                        posicion[0]++;
                    } else {
                        Utils.escribirConEfecto("Movimiento no válido.", 30);
                    }
                    break;
                case "izquierda":
                    if (posicion[1] > 0 && !visitado[posicion[0]][posicion[1] - 1]) {
                        posicion[1]--;
                    } else {
                        Utils.escribirConEfecto("Movimiento no válido.", 30);
                    }
                    break;
                case "derecha":
                    if (posicion[1] < 4 && !visitado[posicion[0]][posicion[1] + 1]) {
                        posicion[1]++;
                    } else {
                        Utils.escribirConEfecto("Movimiento no válido.", 30);
                    }
                    break;
                default:
                    Utils.escribirConEfecto("\nDirección no válida.", 30);
                    break;
            }

            
            if (mapa[posicion[0]][posicion[1]].equals("bomba")) {
                Utils.escribirConEfecto("\n¡BOOOM! Has encontrado una bomba, has perdido tu dinero.", 30);
                ganancia = -apuesta;
                break;
            } else {
                ganancia += 1.3 * apuesta;
            }
            Utils.escribirConEfecto("\nGanancia actual: " + ganancia + " $", 30);
        }

        tamagochi.cambiarDinero((int) ganancia);
        if (ganancia > 0) {
            Utils.escribirConEfecto(tamagochi.getNombre() + " ha ganado " + ganancia + " $", 30);
        } else {
            Utils.escribirConEfecto(tamagochi.getNombre() + " ha perdido " + (-ganancia) + " $", 30);
        }

        Utils.escribirConEfecto("GANANCIA: " + ganancia + " $", 30);

        tamagochi.cambiarSueno(10);
        tamagochi.cambiarHambre(6);
        tamagochi.avanzarTiempo(30);
        tamagochi.evaluarEstado();
    }
}
