import java.util.*;
import java.util.Random;
public class Carta {
    //Definimos como constantes los valores y palos posibles para una carta
    private static final Set<String> VALORES_POSIBLES = Set.of("A", "K", "Q", "J", "T", "2", "3", "4", "5", "6", "7", "8", "9");
        private static final Set<String> PALOS_POSIBLES = Set.of("S", "H", "D", "C");

    //Definimos en un set las cartas creadas
    private static final Set<String> CARTAS_CREADAS = new HashSet();

    public String valor;
    public String palo;

    //constructor aleatorio
    public Carta () {
        Random random = new Random();
        while (true) {
            String valorRandom = getRandomElemento(VALORES_POSIBLES, random);
            String paloRandom = getRandomElemento(PALOS_POSIBLES, random);
            String cartaCompleta = valorRandom + paloRandom;

            if (!CARTAS_CREADAS.contains(cartaCompleta)) {
                this.valor = valorRandom;
                this.palo = paloRandom;
                CARTAS_CREADAS.add(cartaCompleta);
                break;
            }
        }
//        System.out.println(getCarta());
    }

    //constructor manual
    public Carta(String cartaCompletaManual) {
        if (esCartaValida(cartaCompletaManual)) {
            this.valor = String.valueOf(cartaCompletaManual.charAt(0)).toUpperCase();
            this.palo = String.valueOf(cartaCompletaManual.charAt(1)).toUpperCase();
            CARTAS_CREADAS.add(valor + palo);
        } else {
            System.out.println("Cerrando programa...");
            System.exit(1);
        }
    }

    public static boolean esCartaValida(String cartaCompletaManual) {
        if (cartaCompletaManual.isEmpty()) {
            System.out.println("La carta ingresada no puede estar vacía");
            return false;
        }
        if (cartaCompletaManual.length() != 2) {
            System.out.println(cartaCompletaManual + " no es una carta valida");
            System.out.println("La carta ingresada debe ser de 2 Cáracteres. Por ejemplo: '2C'");
            return false;
        }
        if (!VALORES_POSIBLES.contains(String.valueOf(cartaCompletaManual.charAt(0)))) {
            System.out.println(cartaCompletaManual + " no es una carta valida");
            System.out.println("El valor de la carta solo puede agarrar: " + VALORES_POSIBLES);
            return false;
        }
        if (!PALOS_POSIBLES.contains(String.valueOf(cartaCompletaManual.charAt(1)))) {
            System.out.println(cartaCompletaManual + " no es una carta valida");
            System.out.println("El palo de la carta solo puede agarrar: " + PALOS_POSIBLES);
            return false;
        }
        if (CARTAS_CREADAS.contains(cartaCompletaManual)) {
            System.out.println(cartaCompletaManual + " no es una carta valida");
            System.out.println("Esta carta ya fue agregada, no pueden repetirse");
            return false;
        }
        return true;
    }

    public static String getRandomElemento(Set<String> set, Random random) {
        int index = random.nextInt(set.size());
        return set.stream().skip(index).findFirst().orElse(null);
    }



    public static void reiniciarCartasCreadas() {
        CARTAS_CREADAS.clear(); //
    }

    public String getCarta () {
        return this.valor + this.palo;
    }

    @Override
    public String toString() {
        return getCarta(); // Usa getCarta para proporcionar la representación de la carta
    }
}

