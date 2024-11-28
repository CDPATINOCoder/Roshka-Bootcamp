import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Carta[]> jugadas = new ArrayList<Carta[]>();
        List<Carta> m1List = new ArrayList<>();
        List<Carta> m2List = new ArrayList<>();

        Carta[] m1 = new Carta[5];
        m1[0] = new Carta("2s");
        m1[1] = new Carta("4h");
        m1[2] = new Carta("6c");
        m1[3] = new Carta("9h");
        m1[4] = new Carta("3d");

        Carta.reiniciarCartasCreadas();

        Carta[] m2 = new Carta[5];
        m2[0] = new Carta("2s");
        m2[1] = new Carta("5h");
        m2[2] = new Carta("3d");
        m2[3] = new Carta("6c");
        m2[4] = new Carta("9d");

        jugadas.add(m1);
        jugadas.add(m2);

        String ganador = ganadores(jugadas);
        if (!ganador.contains("Empate")) {
            System.out.println("El ganador es: " + ganador);
        } else {
            System.out.println("Es un empate absoluto");
        }
    }

    public static String ganadores(List<Carta[]> jugadas) {
        Map<String, Integer> pesoJugada = new HashMap<>();
        pesoJugada.put("Carta Alta", 0);
        pesoJugada.put("Par", 1);
        pesoJugada.put("Par Doble", 2);
        pesoJugada.put("Trío", 3);
        pesoJugada.put("Escalera", 4);
        pesoJugada.put("Color", 5);
        pesoJugada.put("Full", 6);
        pesoJugada.put("Poker", 7);
        pesoJugada.put("Escalera Color", 8);

        //Lista para almacenar las jugadas retornadas
        List<String> resultado = new ArrayList<>();

        System.out.println("Cantidad de jugadas: " + jugadas.size());

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

        //listas para almacenar palos de las manos
        List<String> palosMano1 = mano1.stream()
                .map(carta -> carta.palo)
                .collect(Collectors.toList());

        HashSet<String> contadorPalosMano1 = new HashSet<>(palosMano1);

        List<String> palosMano2 = mano2.stream()
                .map(carta -> carta.palo)
                .collect(Collectors.toList());

        HashSet<String> contadorPalosMano2 = new HashSet<>(palosMano2);

       //creamos Maps para realizar conteos
       Map<Integer, Integer> conteoMano1 = new HashMap<>();
       Map<Integer, Integer> conteoMano2 = new HashMap<>();

       for (int cartaValor : mano1Sorted) {
           conteoMano1.put(cartaValor, conteoMano1.getOrDefault(cartaValor, 0) + 1);
       }

       for (int cartaValor : mano2Sorted) {
           conteoMano2.put(cartaValor, conteoMano2.getOrDefault(cartaValor, 0) +1);
       }

        //almacenamos el valor de peso jugada dentro de estas varibales para realizar comparaciones
        int pesoMano1 = pesoJugada.get(resultado.get(0));
        int pesoMano2 = pesoJugada.get(resultado.get(1));

        System.out.println(resultado);

       if (pesoMano1 > pesoMano2) return "Mano 1";
       if (pesoMano2 > pesoMano1) return "Mano 2";

       String ganador = null;

       switch(pesoMano1){
           case 8:
               if (Collections.max(mano1Sorted) > Collections.max(mano2Sorted)) ganador = "Mano 1";
               if (Collections.max(mano2Sorted) > Collections.max(mano1Sorted)) ganador = "Mano 2";
               if (Collections.max(mano1Sorted) == Collections.max(mano2Sorted)) ganador = "Empate";
               break;
           case 7:
               if (Collections.max(mano1Sorted) > Collections.max(mano2Sorted)) ganador = "Mano 1";
               if (Collections.max(mano2Sorted) > Collections.max(mano1Sorted)) ganador = "Mano 2";
               if (Collections.max(mano1Sorted) == Collections.max(mano2Sorted)) ganador = "Empate";
               break;
           case 6:
               for (Map.Entry<Integer, Integer> entryMano1 : conteoMano1.entrySet()) {

                   for (Map.Entry<Integer, Integer> entryMano2 : conteoMano2.entrySet()) {
                       if (entryMano1.getValue() == 3 && entryMano2.getValue() == 3) {
                           if (entryMano1.getKey() > entryMano2.getKey()) {
                               ganador = "Mano 1";
                               break;
                           } else if (entryMano2.getKey() > entryMano1.getKey()) {
                               ganador = "Mano 2";
                               break;
                           }
                       }
                   }


                   if (ganador == null) {
                       for (Map.Entry<Integer, Integer> entryMano2 : conteoMano2.entrySet()) {
                           if (entryMano1.getValue() == 2 && entryMano2.getValue() == 2) {
                               if (entryMano1.getKey() > entryMano2.getKey()) {
                                   ganador = "Mano 1";
                                   break;
                               } else if (entryMano2.getKey() > entryMano1.getKey()) {
                                   ganador = "Mano 2";
                                   break;
                               } else {
                                   ganador = "Empate";
                                   break;
                               }
                           }
                       }
                   }

               }
               break;
           case 5:
               if (Collections.max(mano1Sorted) > Collections.max(mano2Sorted)) ganador = "Mano 2";
               if (Collections.max(mano2Sorted) > Collections.max(mano1Sorted)) ganador = "Mano 1";
               if (Collections.max(mano1Sorted) == Collections.max(mano2Sorted)) ganador = "Empate";
               break;
           case 4:
               if (Collections.max(mano1Sorted) > Collections.max(mano2Sorted)) ganador = "Mano 1";
               if (Collections.max(mano2Sorted) > Collections.max(mano1Sorted)) ganador = "Mano 2";
               if (Collections.max(mano1Sorted) == Collections.max(mano2Sorted)) ganador = "Empate";
               break;
           case 3:

               for (Map.Entry<Integer, Integer> entryMano1 : conteoMano1.entrySet()) {
                for (Map.Entry<Integer, Integer> entryMano2 :conteoMano2.entrySet()){
                    if (entryMano1.getValue() == 3 && entryMano2.getValue() == 3) {
                        if (entryMano1.getKey() > entryMano2.getKey()) {
                            ganador = "Mano 1";
                            break;
                        } else if (entryMano2.getKey() > entryMano1.getKey()) {
                            ganador = "Mano 2";
                        }
                        }
                    }
                }
               if (ganador == null) {
                   List<Integer> restoMano1 = new ArrayList<>();
                   List<Integer> restoMano2 = new ArrayList<>();

                   if (restoMano1.isEmpty() && restoMano2.isEmpty()) {
                       for (Map.Entry<Integer, Integer> entry : conteoMano1.entrySet()) {
                           if (entry.getValue() != 3) {
                               restoMano1.add(entry.getKey());
                           }
                       }
                       for (Map.Entry<Integer, Integer> entry : conteoMano2.entrySet()) {
                           if (entry.getValue() != 3) {
                               restoMano2.add(entry.getKey());
                           }
                       }
                   }

                   Collections.sort(restoMano1);
                   Collections.sort(restoMano2);
                   Collections.reverse(restoMano1);
                   Collections.reverse(restoMano2);

                   if (restoMano1.get(0) > restoMano2.get(0)) {
                       ganador = "Mano 1";
                   } else if (restoMano2.get(0) > restoMano1.get(0)) {
                       ganador = "Mano 2";
                   } else if (restoMano1.get(1) > restoMano2.get(1)) {
                       ganador = "Mano 1";
                   } else if (restoMano2.get(1) > restoMano1.get(1)) {
                       ganador = "Mano 2";
                   } else {
                       ganador = "Empate";
                   }
               }
               break;
           case 2:
               List<Integer> valoresMano1 = new ArrayList<>();
               List<Integer> valoresMano2 = new ArrayList<>();
               List<Integer> restoMano1 = new ArrayList<>();
               List<Integer> restoMano2 = new ArrayList<>();

               for (Map.Entry<Integer, Integer> entry : conteoMano1.entrySet()) {
                   if (entry.getValue() == 2) {
                       valoresMano1.add(entry.getKey());
                   } else {
                       restoMano1.add(entry.getKey());
                   }
               }
               for (Map.Entry<Integer, Integer> entry : conteoMano2.entrySet()) {
                   if (entry.getValue() == 2) {
                       valoresMano2.add(entry.getKey());
                   } else {
                       restoMano2.add(entry.getKey());
                   }

               }

               Collections.sort(valoresMano1);
               Collections.sort(valoresMano2);
               Collections.reverse(valoresMano1);
               Collections.reverse(valoresMano2);

               if (valoresMano1.get(0) > valoresMano2.get(0)) {
                   ganador = "Mano 1";
               } else if (valoresMano2.get(0) > valoresMano1.get(0)) {
                   ganador = "Mano 2";
               } else if (valoresMano1.get(1) > valoresMano2.get(1)) {
                   ganador = "Mano 1";
               } else if (valoresMano2.get(1) > valoresMano1.get(1)) {
                   ganador = "Mano 2";
               } else if (restoMano1.get(0) > restoMano2.get(0)) {
                   ganador = "Mano 1";
               }  else if (restoMano2.get(0) > restoMano1.get(0)) {
                   ganador = "Mano 2";
               } else {
                   ganador = "Empate";
               }

               break;
           case 1:

               List<Integer> valorParMano1 = new ArrayList<>();
               List<Integer> valorParMano2= new ArrayList<>();
               List<Integer> restantesMano1 = new ArrayList<>();
               List<Integer> restantesMano2 = new ArrayList<>();

               for (Map.Entry<Integer, Integer> entry : conteoMano1.entrySet()) {
                   if (entry.getValue() == 2) {
                       valorParMano1.add(entry.getKey());
                   } else {
                       restantesMano1.add(entry.getKey());
                   }
               }
               for (Map.Entry<Integer, Integer> entry : conteoMano2.entrySet()) {
                   if (entry.getValue() == 2) {
                       valorParMano2.add(entry.getKey());
                   } else {
                       restantesMano2.add(entry.getKey());
                   }
               }

               Collections.reverse(restantesMano1);
               Collections.reverse(restantesMano2);

               if (valorParMano1.get(0) > valorParMano2.get(0)) {
                   ganador = "Mano 1";
               } else if (valorParMano2.get(0) > valorParMano1.get(0)) {
                   ganador = "Mano 2";
               } else if (valorParMano1.get(0) == valorParMano2.get(0)) {
                   for (int i = 0; i < restantesMano1.size(); i++) {
                       if (restantesMano1.get(i) > restantesMano2.get(i)) {
                           ganador = "Mano 1";
                       } else if (restantesMano2.get(i) > restantesMano1.get(i)) {
                           ganador = "Mano 2";
                       }
                   }
               }
               if (ganador == null) ganador = "Empate";
               break;
           case 0:
              Collections.reverse(mano1Sorted);
              Collections.reverse(mano2Sorted);

              for (int i = 0; i < mano1Sorted.size(); i++) {
                  if (mano1Sorted.get(i) > mano2Sorted.get(i)) {
                      ganador = "Mano 1";
                  } else if (mano2Sorted.get(i) > mano1Sorted.get(i)) {
                      ganador = "Mano 2";
                  }
              }
              if (ganador == null) ganador = "Empate";
       }

       return ganador;

    }


}