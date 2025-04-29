import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private Tamagochi tamagochi;
    private Ruleta ruleta;
    private Juego26 juego26;
    private Gemelos gemelos;
    private CaBoom caBoom;

    public Main() {
        this.tamagochi = null;
        this.ruleta = null;
        this.juego26 = null;
        this.gemelos = null;
        this.caBoom = null;
    }

    private void mostrarMensajeBienvenida() {
        Utils.escribirConEfecto("\n-----------------------------------------------------------------------------------------------------------------------------------------------", 10);
        Utils.escribirConEfecto("¡BIENVENIDO AL TAMAGOCHI L-U-D-O-P-A-T-A!", 20);
        Utils.escribirConEfecto("- En este juego, tu objetivo es convertirte en el hombre más rico del mundo.", 20);
        Utils.escribirConEfecto("- Para ello deberás gestionar tu dinero, sueño y hambre para alcanzar la meta.", 20);
        Utils.escribirConEfecto("- ¡Ten cuidado, cada día tendrás que pagar una deuda para que no te coja la policía!", 20);
        Utils.escribirConEfecto("- Para pagar las deudas y convertirte en el hombre más rico podrás ganar dinero jugando a varios juegos: La Ruleta, Al 26, Gemelos o CaBoom.", 20);
        Utils.escribirConEfecto("¡BUENA SUERTE!", 20);
        Utils.escribirConEfecto("-----------------------------------------------------------------------------------------------------------------------------------------------\n", 10);
    }

    private void iniciarJuego(Scanner scanner) {
        Utils.escribirConEfecto("¿Cómo quieres que se llame tu Tamagochi?: ", 30);
        String nombre = scanner.nextLine();
        this.tamagochi = new Tamagochi(nombre);
        this.ruleta = new Ruleta(tamagochi);
        this.juego26 = new Juego26(tamagochi);
        this.gemelos = new Gemelos(tamagochi);
        this.caBoom = new CaBoom(tamagochi);
    }

    private void mostrarMenu() {
        Utils.escribirConEfecto("\n¿Qué quieres hacer ahora?", 30);
        Utils.escribirConEfecto("1) Ir a dormir", 30);
        Utils.escribirConEfecto("2) Dar de comer", 30);
        Utils.escribirConEfecto("3) Jugar --> La Ruleta", 30);
        Utils.escribirConEfecto("4) Jugar --> Al 26", 30);
        Utils.escribirConEfecto("5) Jugar --> Gemelos", 30);
        Utils.escribirConEfecto("6) Jugar --> CaBoom", 30);
        Utils.escribirConEfecto("7) Pagar deuda", 30);
        Utils.escribirConEfecto("8) Salir\n", 30);
    }

    private boolean ejecutarOpcion(int opcion, Scanner scanner) {
        try {
            switch (opcion) {
                case 1:
                    int horas = 0;
                    while (horas < 1 || horas > 3) {
                        Utils.escribirConEfecto("\n¿Cuántas horas quieres que duerma tu Tamagochi?", 30);
                        Utils.escribirConEfecto("1) 3 horas (-25 de sueño)", 30);
                        Utils.escribirConEfecto("2) 6 horas (-50 de sueño)", 30);
                        Utils.escribirConEfecto("3) 9 horas (-80 de sueño)", 30);
                        Utils.escribirConEfecto("\nSelecciona una opción:", 30);
                        horas = scanner.nextInt();
                    }
                    tamagochi.dormir(horas == 1 ? 3 : horas == 2 ? 6 : 9);
                    break;
                case 2:
                    int opcionComida = 0;
                    while (opcionComida < 1 || opcionComida > 3) {
                        Utils.escribirConEfecto("\n¿Qué quieres que coma tu Tamagochi?", 30);
                        Utils.escribirConEfecto("1) Sopa    (-10 de hambre: " + (int) (tamagochi.getDinero() * 0.15) + " $)", 30);
                        Utils.escribirConEfecto("2) Fideos  (-30 de hambre: " + (int) (tamagochi.getDinero() * 0.35) + " $)", 30);
                        Utils.escribirConEfecto("3) Bistec  (-60 de hambre: " + (int) (tamagochi.getDinero() * 0.50) + " $)", 30);
                        Utils.escribirConEfecto("\nSelecciona una opción:", 30);
                        opcionComida = scanner.nextInt();
                    }
                    tamagochi.comer(opcionComida);
                    break;
                case 3:
                    Utils.escribirConEfecto("\n¿Cuánto dinero quieres apostar?: ", 30);
                    int apuestaRuleta = scanner.nextInt();
                    Set<Integer> numerosSeleccionadosSet = new HashSet<>();
                    Utils.escribirConEfecto("\nAhora tienes que seleccionar los números con los que quieres jugar:", 30);
                    for (int i = 0; i < 5; i++) {
                        int numero;
                        do {
                            Utils.escribirConEfecto("Número " + (i + 1) + " (1-20): ", 30);
                            numero = scanner.nextInt();
                        } while (numerosSeleccionadosSet.contains(numero) || numero < 1 || numero > 20);
                        numerosSeleccionadosSet.add(numero);
                    }
                    int[] numerosSeleccionados = numerosSeleccionadosSet.stream().mapToInt(Integer::intValue).toArray();
                    ruleta.jugarRuleta(apuestaRuleta, numerosSeleccionados);
                    break;
                case 4:
                    Utils.escribirConEfecto("\n¿Cuánto dinero quieres apostar?: ", 30);
                    int apuesta26 = scanner.nextInt();
                    juego26.jugar26(apuesta26);
                    break;
                case 5:
                    Utils.escribirConEfecto("\n¿Cuánto dinero quieres apostar?: ", 30);
                    int apuestaGemelos = scanner.nextInt();
                    Utils.escribirConEfecto("\n¿Cuántos dados quieres lanzar?: ", 30);
                    int dados = scanner.nextInt();
                    int[] numerosSeleccionadosGemelos = new int[dados];
                    Utils.escribirConEfecto("\nSelecciona los números con los que quieres jugar para cada dado:", 30);
                    for (int i = 0; i < dados; i++) {
                        Utils.escribirConEfecto("Número para dado " + (i + 1) + " (1-6): ", 30);
                        numerosSeleccionadosGemelos[i] = scanner.nextInt();
                    }
                    gemelos.jugarDados(apuestaGemelos, dados, numerosSeleccionadosGemelos);
                    break;
                case 6:
                    Utils.escribirConEfecto("\n¿Cuánto dinero quieres apostar?: ", 30);
                    int apuestaCaBoom = scanner.nextInt();
                    caBoom.jugarMapa(apuestaCaBoom);
                    break;
                case 7:
                    tamagochi.pagarDeuda();
                    break;
                case 8:
                    Utils.escribirConEfecto("\n¡GRACIAS POR JUGAR! Hasta la próxima.", 30);
                    return false;
                default:
                    Utils.escribirConEfecto("\nOpción no válida. Inténtalo de nuevo.", 30);
            }
        } catch (Exception e) {
            Utils.escribirConEfecto("Error de entrada. Por favor, inténtalo de nuevo.", 30);
            scanner.nextLine(); // Limpiar buffer
        }
        return true;
    }

    public void run() {
        mostrarMensajeBienvenida();
        try (Scanner scanner = new Scanner(System.in)) {
            iniciarJuego(scanner);

            boolean jugando = true;
            while (jugando) {
                tamagochi.mostrarEstado();
                mostrarMenu();
                Utils.escribirConEfecto("Selecciona una opción: ", 30);
                int opcion = scanner.nextInt();
                jugando = ejecutarOpcion(opcion, scanner);
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
