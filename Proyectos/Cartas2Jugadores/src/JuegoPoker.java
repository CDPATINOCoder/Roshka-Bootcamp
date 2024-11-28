import java.util.*;
import java.util.stream.Collectors;
public class JuegoPoker {

    public static String determinarJugada(List<Carta> cartas) {

        //Uso stream para convertir todos mis objetos a string y pasarlos a valorNumerico();
        //.map me sirve para transformar de manera más rapida
        List<Integer> valoresEnNumeros = cartas.stream()
                .map(carta -> valorNumerico(carta.valor))
                .sorted().collect(Collectors.toList());

        List<String> valoresString = new ArrayList(numericoAString(valoresEnNumeros));


        //Lo mismo para enlistar todos palos en una lista de palos
        List<String> palos = cartas.stream()
                .map(carta -> carta.palo)
                .collect(Collectors.toList());
        HashSet<String> palosContador = new HashSet<>(palos);

        //con un contador de tipo HashMap almacenamos los la cantidad de veces que se repiten los valores
        //dentro de valoresEnNumeros
        HashMap<Integer, Integer> conteoValores = new HashMap<>();
        for (int valor : valoresEnNumeros) {
            conteoValores.put(valor, conteoValores.getOrDefault(valor, 0) + 1);
        }

        if (esEscaleraColor(valoresEnNumeros, palosContador)) return "Escalera Color";
        if (esPoker(conteoValores)) return "Poker";
        if (esFull(conteoValores)) return "Full";
        if (esColor(palosContador)) return "Color";
        if (esEscalera(valoresEnNumeros)) return "Escalera";
        if (esTrio(conteoValores)) return "Trío";
        if (esParDoble(conteoValores)) return "Par Doble";
        if (esPar(conteoValores)) return "Par";
        return "Carta Alta";
    }

    //Metodo para representar las letras en valores númericos y realizar mejor las comparaciones
    public static int valorNumerico(String valor) {
        switch(valor) {
            case "A" : return 14;
            case "T" : return 10;
            case "J" : return 11;
            case "Q" : return 12;
            case "K" : return 13;
            default : return Integer.parseInt(valor);
        }
    }

    public static List<String> numericoAString(List<Integer> valorNumerico) {
        List<String> valoresString = new ArrayList<>();
        for (int i = 0; i < valorNumerico.size(); i++) {
            switch (valorNumerico.get(i)) {
                case 10:
                    valoresString.add("T");
                    break;
                case 11:
                    valoresString.add("J");
                    break;
                case 12:
                    valoresString.add("Q");
                    break;
                case 13:
                    valoresString.add("K");
                    break;
                default:
                    valoresString.add(String.valueOf(valorNumerico.get(i)));
            }
        }
        return valoresString;
    }

    //Metodo para verificar si el mazo forma una Escalera Color
    //Si esEscalera() y esColor son verdaderas entonces devuelve verdadero
    public static boolean esEscaleraColor(List<Integer> valores, HashSet<String> palos) {
        return esEscalera(valores) && esColor(palos);
    }

    //Metodo para verificar si el mazo forma un Color
    //Si la lista que contiene unicamente palos (S, C, H, D) es de tamaño uno quiere decir que unicamente existe un palo, es decir son todos iguales
    public static boolean esColor(HashSet<String> palosContador) {
        return palosContador.size() == 1;
    }

    //(0) (1) (2)
    // 2   3   5

    public static boolean esEscalera(List<Integer> valores) {
        for (int i = 0; i < valores.size() - 1; i++) {
            if (valores.get(i + 1) != valores.get(i) + 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean esPoker(HashMap<Integer, Integer> conteoValores) {
        return conteoValores.containsValue(4);
    }

    public static boolean esFull(HashMap<Integer, Integer> conteoValores) {
        return conteoValores.containsValue(3) && conteoValores.containsValue(2);
    }

    public static boolean esTrio(HashMap<Integer, Integer> conteoValores) {
        return conteoValores.containsValue(3);
    }

    public static boolean esParDoble(HashMap<Integer, Integer> conteoValores) {
        int pares = 0;
        for (int valor : conteoValores.values()) {
            if (valor == 2) pares++;
        }
        return pares == 2;
    }

    public static boolean esPar(HashMap<Integer, Integer> conteoValores) {
        return conteoValores.containsValue(2);
    }

    public static String getCartaAlta(List<String> valoresString) {
        String cartaAlta = "0";
        for (int i = 0; i < valoresString.size(); i++) {
            switch (valoresString.get(i)) {
                case "A":
                    cartaAlta = "14";
                    break;
                case "K":
                    cartaAlta = "13";
                    break;
                case "Q":
                    cartaAlta = "12";
                    break;
                case "J":
                    cartaAlta = "11";
                    break;
                case "T":
                    cartaAlta = "10";
                    break;
                default:
                    int valorNumerico = Integer.parseInt(valoresString.get(i));
                    if (valorNumerico > Integer.parseInt(cartaAlta)) {
                        cartaAlta = String.valueOf(valorNumerico);
                    }
            }
        }

        switch (cartaAlta) {
            case "14":
                return "A";
            case "13":
                return "K";
            case "12":
                return "Q";
            case "11":
                return "J";
            case "10":
                return "T";
        }

        return cartaAlta;
    }


}
