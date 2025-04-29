public class Utils {
    public static void escribirConEfecto(String texto, int milisegundosPorCaracter) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(milisegundosPorCaracter);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); 
    }
}
