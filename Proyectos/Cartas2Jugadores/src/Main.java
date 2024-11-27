import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Carta[]> jugadas = new ArrayList<Carta[]>();
        List<Carta> m1List = new ArrayList<>();
        List<Carta> m2List = new ArrayList<>();

        Carta[] m1 = new Carta[5];
        m1[0] = new Carta("AH");
        m1[1] = new Carta("AD");
        m1[2] = new Carta("TH");
        m1[3] = new Carta("TC");
        m1[4] = new Carta("6S");

        Carta.reiniciarCartasCreadas();

        Carta[] m2 = new Carta[5];
        m2[0] = new Carta("AH");
        m2[1] = new Carta("KD");
        m2[2] = new Carta("QH");
        m2[3] = new Carta("3C");
        m2[4] = new Carta("3S");

        jugadas.add(m1);
        jugadas.add(m2);

        ganadores(jugadas);

    }

    public static String ganadores(List<Carta[]> jugadas) {
        System.out.println("Cantidad de jugadas: " + jugadas.size());

        //Lista para almacenar las jugadas retornadas
        List<String> resultado = new ArrayList<>();


        //Iteramos sobre los las jugadas para manipular los dos arreglos
        for (Carta[] mano : jugadas) {

            //convertimos el arreglo a una lista para poder pasarla como argumento a determinarJugada()
            List<Carta> listaCartas = Arrays.asList(mano);

            System.out.println("JUGADA=============================");

            //iteramos la listaCartas para imprimir valores
            for (Carta carta : listaCartas) {
                System.out.println(carta.getCarta());
            }

            //dentro de resultado almacenamos el resultado de las jugadas para las 2 manos
            resultado.add(JuegoPoker.determinarJugada(listaCartas));

        }

        //Creamos dos listas para las dos manos
        List<Carta> mano1 = Arrays.asList(jugadas.get(0));
        List<Carta> mano2 = Arrays.asList(jugadas.get(1));

        //convertimos los valores Representados por letras a números y los ordenamos
        List<Integer> mano1Sorted = mano1.stream()
            .map(carta -> JuegoPoker.valorNumerico(carta.valor))
            .sorted().collect(Collectors.toList());

        List<Integer> mano2Sorted = mano2.stream()
            .map(carta -> JuegoPoker.valorNumerico(carta.valor))
            .sorted().collect(Collectors.toList());

        //Creamos sets en caso de que hayan colors
       HashSet<Integer> mano1Set = new HashSet<>(mano1Sorted);
       HashSet<Integer> mano2Set = new HashSet<>(mano2Sorted);

       //creamos Maps para realizar conteos
       Map<Integer, Integer> conteoMano1 = new HashMap<>();
       Map<Integer, Integer> conteoMano2 = new HashMap<>();

       for (int cartaValor : mano1Sorted) {
           conteoMano1.put(cartaValor, conteoMano1.getOrDefault(cartaValor, 0) + 1);
       }

       for (int cartaValor : mano2Sorted) {
           conteoMano2.put(cartaValor, conteoMano2.getOrDefault(cartaValor, 0) +1);
       }


        System.out.println(resultado);

        return "0";

    }


}