import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class FitxerSequencial {
    
    //donem un nom constant al fitxer
    //afegim la ruta de forma relativa
    public static final String DADES = "fitxers/dades_alumnes.txt";
    static final int N = 2; //number of notes found in the file 3

    public static void main(String[] args) {

        lleixirFitxer();


    }

    public static void lleixirFitxer() {
        try {
            File myData = new File(DADES);
            Scanner lectura = new Scanner (myData);
            while (lectura.hasNextLine()) {
                String data = lectura.nextLine();
                System.out.println(data);
            }
            lectura.close();
        } catch (FileNotFoundException e) {
            System.out.println("A sorgit un error.");
            e.printStackTrace();
        }
        

    }
}