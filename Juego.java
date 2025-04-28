public abstract class Juego {
    protected Tamagochi tamagochi;

    public Juego(Tamagochi tamagochi) {
        this.tamagochi = tamagochi;
    }

    public abstract void jugar();
}
