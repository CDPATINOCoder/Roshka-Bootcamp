import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("2D"));
        cartas.add(new Carta("6H"));
        cartas.add(new Carta("7S"));
        cartas.add(new Carta("3D"));
        cartas.add(new Carta("5S"));



        String jugadaDevueltaJugador1 = JuegoPoker.determinarJugada(cartas);
        System.out.println("La jugada es: " + jugadaDevueltaJugador1);

    }

    


}