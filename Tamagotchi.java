import java.util.Random;

public class Tamagochi {
    private String nombre;
    private int dinero;
    private int sueno;
    private int hambre;
    private int dia;
    private int deuda;
    private int horaDelDia;
    private Random random;

    
    public Tamagochi(String nombre) {
        this.nombre = nombre;
        this.dinero = 50;
        this.sueno = 0;
        this.hambre = 0;
        this.dia = 1;
        this.horaDelDia = 0;
        this.random = new Random();
        this.deuda = generarDeuda();
    }

    
    public int getDinero() {
        return dinero;
    }

    public String getNombre() {
        return nombre;
    }

    
    public void cambiarDinero(int cantidad) {
        dinero += cantidad;
    }

    public void cambiarSueno(int cantidad) {
        sueno = Math.min(100, Math.max(0, sueno + cantidad));
    }

    public void cambiarHambre(int cantidad) {
        hambre = Math.min(100, Math.max(0, hambre + cantidad));
    }

    public void avanzarTiempo(int minutos) {
        horaDelDia += minutos;
        if (horaDelDia >= 1440) { 
            dia++;
            deuda = generarDeuda();
            horaDelDia -= 1440;
        }
    }

    public int getDeuda() {
        return deuda;
    }

    public void pagarDeuda() {
        if (dinero >= deuda) {
            dinero -= deuda;
            deuda = 0;
            System.out.println("\n" + nombre + " ha pagado su deuda!");
        } else {
            System.out.println("\n" + nombre + " no tiene suficiente dinero para pagar su deuda.");
        }
        cambiarSueno(5);
        cambiarHambre(3);
        avanzarTiempo(30);
        evaluarEstado();
    }

    public void dormir(int horas) {
        System.out.println("\n" + nombre + " se despide. ¡Se va a dormir por " + horas + " horas!");
        sueno = Math.max(0, sueno - (horas == 9 ? 80 : horas == 6 ? 50 : 25));
        hambre = Math.min(100, hambre + 10);
        avanzarTiempo(horas * 60);
        evaluarEstado();
    }

    public void evaluarEstado() {
        if (dinero <= 0) {
            System.out.println("Oh, Oh! " + nombre + " ha sido arrestado por la policía.");
            throw new IllegalStateException("Juego terminado");
        } else if (sueno >= 100) {
            System.out.println("Oh, Oh! " + nombre + " se ha quedado dormido en un casino y le han robado todo el dinero.");
            throw new IllegalStateException("Juego terminado");
        } else if (hambre >= 100) {
            System.out.println("Oh, Oh! " + nombre + " ha muerto por no alimentarse.");
            throw new IllegalStateException("Juego terminado");
        }
    }

    public void mostrarEstado() {
        System.out.println("\nEstado de " + nombre + ":");
        System.out.println("Día --> " + dia);
        System.out.println("Hora --> " + (horaDelDia / 60) + ":" + (horaDelDia % 60));
        System.out.println("Sueño --> " + sueno);
        System.out.println("Hambre--> " + hambre);
        System.out.println("Dinero --> " + dinero + " $");
        System.out.println("Deuda --> " + deuda + " $");
    }

    public void comer(int opcion) {
        int costo = calcularCostoComida(opcion);
        int hambreReducida = calcularHambreReducida(opcion);

        if (costo == 0 || hambreReducida == 0) {
            System.out.println("\nOpción de comida no válida.");
            return;
        }

        if (dinero >= costo) {
            System.out.println("");
            System.out.println(nombre + " dice que la comida está ¡BRUTAL!.");
            cambiarHambre(-hambreReducida);
            cambiarDinero(-costo);
        } else {
            System.out.println("");
            System.out.println(nombre + " no tiene suficiente dinero para comer.");
        }

        cambiarSueno(8);
        avanzarTiempo(30);
        evaluarEstado();
    }

    private int calcularCostoComida(int opcion) {
        switch (opcion) {
            case 1:
                return (int) (dinero * 0.15);
            case 2:
                return (int) (dinero * 0.35);
            case 3:
                return (int) (dinero * 0.50);
            default:
                return 0;
        }
    }

    private int calcularHambreReducida(int opcion) {
        switch (opcion) {
            case 1:
                return 10;
            case 2:
                return 30;
            case 3:
                return 60;
            default:
                return 0;
        }
    }

    private int generarDeuda() {
        return random.nextInt(16) + 5; 
    }
}
