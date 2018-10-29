public class Jugador
{
    char fichaAsociada;
    String nombre;

    public Jugador(char letra, String nombre)
    {
        fichaAsociada = letra;
        this.nombre = nombre;
    }

    public char getFicha()
    {
        return fichaAsociada;
    }
    
    public String getNombre()
    {
        return nombre;
    }
}
