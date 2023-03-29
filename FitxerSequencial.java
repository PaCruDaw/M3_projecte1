import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

class FitxerSequencial {
    
    //donem un nom constant al fitxer
    //afegim la ruta de forma relativa
    public static final String DADES = "fitxers/dades_alumnes.txt";
    static final int N = 2; //number of notes found in the file 3

    //array dinamic s'objectes
    static ArrayList<Alumne> alumnes = new ArrayList<Alumne>();

    public static void main(String[] args) {

        lleixirFitxer();


    }

    public static void lleixirFitxer() {
        //Alumne alum;
        try {
            File myData = new File(DADES);
            Scanner lectura = new Scanner (myData);
            int i= 0;
            while (lectura.hasNextLine()) {
                String linea = lectura.nextLine();
                String[] data = linea.split(" ");

                //volem poder fer aquesta conversio ancara que afegim mes notes
                String[] data1 = Arrays.copyOfRange(data,(data.length - (N+1)),data.length);
                //OJO diference de C++ debe darse valor inicial
                float [] notes = new float[data1.length];
    
                //no podem utilitzar i esta dins el bucle, volem tipus float no string
                for (int j = 0; j < data1.length; j++) {
                    notes[j] = Float.parseFloat(data1[j]);
                } 

                Alumne alum = new Alumne(data[0], data[1], data[2], data[3],data[4], notes);
                System.out.println(data1.length);
                System.out.println(notes[2]);

                i++;
            }
            lectura.close();
        } catch (FileNotFoundException e) {
            System.out.println("A sorgit un error.");
            e.printStackTrace();
        }
        

    }
}