
package cena;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread
{
    private  int id;
    private  Semaphore[] palillos_semaforo;
    private  int[][] palillosFilosofo;
    private  int palilloIzquierdo;
    private  int palilloDerecho;
    private  Random tiempoAleatorio = new Random();
    
    public  Filosofo(int id, Semaphore[] palillos_semaforo, int[][] palillosFilosofo) 
    {
        this.id = id;
        this.palillos_semaforo = palillos_semaforo;
        this.palillosFilosofo = palillosFilosofo;
        this.palilloIzquierdo = palillosFilosofo[id][0];
        this.palilloDerecho = palillosFilosofo[id][1];
    }
    
    public void comer()
    {
        if (palillos_semaforo[palilloIzquierdo].tryAcquire()) 
        {
            if (palillos_semaforo[palilloDerecho].tryAcquire()) 
            {
                System.out.println("El filosofo " + id + " estan comiendo.");
            
            try {
                sleep(tiempoAleatorio.nextInt(1000) + 500);
            }
            catch (InterruptedException ex)
                {
                System.out.println("Error : " + ex.toString());
                }
        System.out.println("El filosofo " + id + " ha terminado de comer.Libera los palillos " + palilloIzquierdo + " y " + palilloDerecho);
        palillos_semaforo[palilloDerecho].release();
        }
        palillos_semaforo[palilloIzquierdo].release();
    }else 
    { 
        System.out.println("El filosofo " + id + " esta hambriento.");
    }
    }
    public void pensar() 
    {
        System.out.println("El filosofo " + id + " esta pensando.");
        try 
        {
            Filosofo.sleep(tiempoAleatorio.nextInt(1000) + 100);
        } catch (InterruptedException ex) {
            System.out.println("Error en el metodo pensar(): " + ex.toString());
        }
    }
    //bucle infinito
    public void run() 
    {
        while (true) 
        {
            pensar();
            comer();
        }
    }
    
}



