package gestionefile;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franco Miscia
 * @version 02/02/2024
 */
public class GestioneFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1)LETTURA
        Lettore lettore = new Lettore("user.json",false);
        lettore.start();
        try {
            lettore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        } 

        //2)ELABORAZIONE
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username");
        String userName = myObj.nextLine();
        System.out.println("Enter password");
        String password = myObj.nextLine();
                
        
        
        //3) SCRITTURA
        Scrittore scrittore = new Scrittore("output.csv",userName+";"+password);
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //3) AZIONE DEL COPIARE
        Lettore lettore2 = new Lettore("output.csv",true);
        lettore2.start();
        try {
            lettore2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
