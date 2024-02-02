package gestionefile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franco Miscia
 * @version 02/02/2024
 */

public class Lettore extends Thread{
    String nomeFile;
    
    // se console sarà true, il thread lettore copierà
    boolean console;
    
    public Lettore(String nomeFile,boolean console){
        this.nomeFile = nomeFile;
        this.console=console;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public String leggi(){
        StringBuilder sb=new StringBuilder();
        FileReader fr;
        int i; 
        try { 
            //1) apro il file
            fr = new FileReader(nomeFile);
            //2) leggo carattere per carattere e lo stampo 
            while ((i=fr.read()) != -1)
                sb.append(((char) i));
            sb.append(("\n\r"));
            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }
        return sb.toString();
    }
    
    public void copia(){
        
         Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Inserisci su quale file copiare");
        String nomeFile = myObj.nextLine();
        Scrittore scrittore = new Scrittore(nomeFile,this.leggi());
        Thread threadScrittore = new Thread(scrittore);
        threadScrittore.start();
        try {
            threadScrittore.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestioneFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void run(){
        if(!console){
            System.out.println(leggi());
        }else{
            copia();
        }
        
    }
}
