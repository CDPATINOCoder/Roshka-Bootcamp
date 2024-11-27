import java.util.*;
import java.util.stream.Collectors;
public class JuegoPoker {
    public static String determinarJugada(List<Carta> cartas) {

        //Uso stream para convertir todos mis objetos a string y pasarlos a valorNumerico();
        //.map me sirve para transformar de manera más rapida
        List<Integer> valoresEnNumeros = cartas.stream()
                .map(carta -> valorNumerico(carta.valor))
                .sorted().collect(Collectors.toList());

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
        return "Carta alta: " + Collections.max(valoresEnNumeros);
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


}
