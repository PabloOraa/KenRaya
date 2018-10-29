import java.util.Scanner;
import Clases.Tablero;
import Clases.Jugador;

/**
 * @author Pabli
 */
public class main 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int K = 3;
        String nombre;
        Tablero tablero = new Tablero(0);
        boolean KenRaya = false,tabl = false, valK = false, salir = false;
        int fila,columna;
        
        System.out.print("Introduce el nombre del primer jugador: ");
        nombre = sc.nextLine();
        Jugador j1 = new Jugador('X', nombre);
        System.out.print("Introduce el nombre del segundo jugador: ");
        nombre = sc.nextLine();
        Jugador j2 = new Jugador('O', nombre);
        Jugador jugador = j1;
        
        while(!salir)
        {
            try
            {
                if(!tabl)
                {
                    System.out.print("Tamanio del tablero: ");  
                    int tam = Integer.parseInt(sc.nextLine());
                    tablero = new Tablero(tam);
                    tabl = true;
                }
        
                if(!valK)
                    do 
                    {
                        System.out.print("El valor K, sabiendo que debe ser menor que el tamanio del tablero y mayor que 0: ");
                        K = Integer.parseInt(sc.nextLine());
                    } while (K > tablero.getTam() || K < 1);
                valK = true;
        
                while (!tablero.comprobarLleno() && !KenRaya)
                {
                    System.out.print("Jugador " + jugador.getNombre() + " elige posicion del tablero\n");
                    System.out.print("Fila: ");
                    fila = Integer.parseInt(sc.nextLine());

                    System.out.print("Columna: ");
                    columna = Integer.parseInt(sc.nextLine());

                    try
                    {
                        if (tablero.comprobarMovimiento(fila, columna))
                        {
                            tablero.realizarMovimiento(fila, columna, jugador);
                            tablero.imprimirTablero();
                            KenRaya = tablero.comprobarKenRaya(K, fila, columna);
            
                            if (jugador.getFicha() == j1.getFicha() && !KenRaya)
                                jugador = j2;
                            else if(KenRaya);
                            else 
                                jugador = j1;
                        }
                        else 
                            System.out.print("ERROR\n");
                    }
                    catch(ArrayIndexOutOfBoundsException e)
                    {
                        System.out.println("Introduce un valor que no sea inferior a 0 ni superior a " + tablero.getTam());
                    }
                }
                salir = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Tienes que introducir numeros, no letras.");
            }
            catch(NegativeArraySizeException e)
            {
                System.out.println("El tamaño del tablero no puede ser neativo.");
            }
        }

	if (KenRaya)
            System.out.println("Ganador jugador " + jugador.getNombre());
	else
            System.out.println("Empate");
    }
}
