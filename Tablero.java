import java.io.IOException;

/**
 * @author Pabli
 */
public class Tablero
{
    int tablero[][];
    int tam;

    public Tablero(int valorFilaColumna)
    {
        tablero = new int[valorFilaColumna][valorFilaColumna];
        tam = valorFilaColumna;

        for(int i = 0; i < tam; i++)
        {
            for(int j = 0; j < tam; j++)
            {
                tablero[i][j] = ' ';
            }
        }
    }
    
    public int getTam()
    {
        return tam;
    }

    public boolean comprobarMovimiento(int fila, int columna)
    {
        return (tablero[fila][columna] == ' ' && ( columna < tam && fila >= 0) && (fila < tam && columna >= 0));
    }

    public void realizarMovimiento(int fila, int columna, Jugador jTurno)
    {
        if(jTurno.getFicha()== 'X')
            tablero[fila][columna] = 'X';
        else
            tablero[fila][columna] = 'O';
    }

    public void imprimirTablero()
    {
        for(int i = 0; i < tam; i++)
        {
            for(int j = 0; j < tam; j++)
            {
                System.out.print("|"+(char)tablero[i][j]+"|");
            }
            System.out.println();
        }
    }

    public boolean comprobarLleno()
    {
        for(int i = 0; i < tam; i++)
        {
            for(int j = 0; j < tam; j++)
            {
                if(tablero[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public boolean comprobarKenRaya(int K, int fila, int columna)
    {
	boolean kEnRaya;
	int K2 = K - 1;
            
        kEnRaya = comprobarKenRayaHorizontal(K2, fila, columna);
        if(!kEnRaya)
        {    
            kEnRaya = comprobarKenRayaVertical(K2, fila, columna);
            if(!kEnRaya)
            {
                kEnRaya = comprobarKenRayaDiagonal(K2, fila, columna);
            }
        }
        
        return kEnRaya;
    }
    
    private boolean comprobarKenRayaHorizontal(int K, int fila, int columna)
    {
        int comprobacion_Horizontal = 0;
        int i;
        int tama = tam-1;
        if (columna != tama)
	{
	    if (tablero[fila][columna] == tablero[fila][columna + 1])
	    {
	    	for (i = 0; columna != tama && tablero[fila][columna] == tablero[fila][columna + 1] && i <= K; columna++, i++)
		{
                    comprobacion_Horizontal++;
                    if (comprobacion_Horizontal == K)
			return true;
    		}
	    }
	}
	comprobacion_Horizontal = 0;
	if (columna != 0)
	{
	    if (tablero[fila][columna] == tablero[fila][columna - 1])
	    {
	    	for (i = 0; columna != 0 && tablero[fila][columna] == tablero[fila][columna - 1] && i <= K; columna--, i++)
	    	{
                    comprobacion_Horizontal++;
                    if (comprobacion_Horizontal == K)
	    		return true;
                }
	    }
	}
        return false;
    }
    
    private boolean comprobarKenRayaVertical(int K, int fila, int columna)
    {
        int comprobacion_Vertical = 0;
        int i;
        int tama = tam - 1;
        
        if (fila != tama)
	{
	    if (tablero[fila][columna] == tablero[fila + 1][columna])
	    {
	    	for (i = 0; fila != tama && tablero[fila][columna] == tablero[fila + 1][columna] && i <= K; fila++, i++)
	    	{
                    comprobacion_Vertical++;
                    if (comprobacion_Vertical == K)
	    		return true;
	    	}
	    }
	}
        
	comprobacion_Vertical = 0;
	if (fila != 0)
	{
            if (tablero[fila][columna] == tablero[fila - 1][columna])
	    {
	    	for (i = 0; fila != 0 && tablero[fila][columna] == tablero[fila - 1][columna] && i <= K; fila--, i++)
	    	{
                    comprobacion_Vertical++;
                    if (comprobacion_Vertical == K)
	    		return true;
	    	}
	    }
	}
        return false;
    }
    
    private boolean comprobarKenRayaDiagonal(int K, int fila, int columna)
    {
        int comprobacion_Diagonal = 0;
        int i;
        int tama = tam - 1;
        
        if (columna != tama && fila != tama)
	{
	    if (tablero[fila][columna] == tablero[fila + 1][columna + 1])
	    {
	    	for (i = 0; columna != tama && fila != tama && tablero[fila][columna] == tablero[fila + 1][columna + 1] && i <= K; fila++, columna++, i++)
	    	{
                    comprobacion_Diagonal++;
		    if (comprobacion_Diagonal == K)
			return true;
    		}
            }
	}
        
	comprobacion_Diagonal = 0;
	if (fila != 0 && columna != 0)
	{
	    if (tablero[fila][columna] == tablero[fila - 1][columna - 1])
	    {
	    	for (i = 0; fila != 0 && columna != 0 && tablero[fila][columna] == tablero[fila - 1][columna - 1] && i <= K; fila--, columna--, i++)
                {
                    comprobacion_Diagonal++;
                    if (comprobacion_Diagonal == K)
	    		return true;
	    	}
	    }
        }
        
	comprobacion_Diagonal = 0;
	if (fila != 0 && columna != tama)
	{
	    if (tablero[fila][columna] == tablero[fila - 1][columna + 1])
	    {
	    	for (i = 0; fila != 0 && columna != tama && tablero[fila][columna] == tablero[fila - 1][columna + 1] && i <= K; fila--, columna++, i++)
	    	{
                    comprobacion_Diagonal++;
                    if (comprobacion_Diagonal == K)
	    		return true;
                    }
	    }
	}
        
	comprobacion_Diagonal = 0;
        if (fila != tama && columna != 0)
	{
	    if (tablero[fila][columna] == tablero[fila + 1][columna - 1])
	    {
	    	for (i = 0; fila != tama && columna != 0 && tablero[fila][columna] == tablero[fila + 1][columna - 1] && i <= K; fila++, columna--, i++)
	    	{
                    comprobacion_Diagonal++;
                    if (comprobacion_Diagonal == K)
	    		return true;
	    	}
	    }
	}
        return false;
    }
}
