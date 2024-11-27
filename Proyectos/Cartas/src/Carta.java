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
    }

    public static String getRandomElemento(Set<String> set, Random random) {
        int index = random.nextInt(set.size());
        return set.stream().skip(index).findFirst().orElse(null);
    }

    public String getCarta () {
        return this.valor + this.palo;
    }
}

