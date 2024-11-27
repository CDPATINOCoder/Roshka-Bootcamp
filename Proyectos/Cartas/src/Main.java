import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Carta> cartas = new ArrayList<>();

        Carta carta = new Carta();
        Carta carta1 = new Carta();
        Carta carta2 = new Carta();
        Carta carta3 = new Carta();
        Carta carta4 = new Carta();
        System.out.println(carta.getCarta());
        System.out.println(carta2.getCarta());
        System.out.println(carta3.getCarta());
        System.out.println(carta4.getCarta());
        System.out.println(carta1.getCarta());

        String jugadaDevuelta = JuegoPoker.determinarJugada(cartas);
        System.out.println("La jugada es: " + jugadaDevuelta);

    }


}