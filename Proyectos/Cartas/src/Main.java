import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("1H"));
        cartas.add(new Carta("2D"));
        cartas.add(new Carta("3C"));
        cartas.add(new Carta("2S"));
        cartas.add(new Carta("1S"));

        String jugadaDevuelta = JuegoPoker.determinarJugada(cartas);
        System.out.println("La jugada es: " + jugadaDevuelta);

    }


}