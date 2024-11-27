import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("QD"));
        cartas.add(new Carta("4H"));
        cartas.add(new Carta("KS"));
        cartas.add(new Carta("JD"));
        cartas.add(new Carta("3S"));



        String jugadaDevuelta = JuegoPoker.determinarJugada(cartas);
        System.out.println("La jugada es: " + jugadaDevuelta);

    }


}