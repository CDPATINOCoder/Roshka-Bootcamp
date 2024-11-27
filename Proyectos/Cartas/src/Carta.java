public class Carta {
    public String valor;
    public String palo;
    public Carta()
    {

    }

    public Carta(String completo)
    {
        this.valor = String.valueOf(completo.charAt(0)).toUpperCase();
        this.palo = String.valueOf(completo.charAt(1)).toUpperCase();
    }

    String valorPalo()
    {
        return this.valor + this.palo;
    }


}

