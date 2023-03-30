import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

class FitxerSequencial {    
    //donem un nom constant al fitxer
    //afegim la ruta de forma relativa
    public static final String DADES = "fitxers/dades_alumnes.txt";
    public static final String MITJANES = "fitxers/mitjanes_alumnes.txt";
    static final int N = 2; //number of notes found in the file 3

    //array dinamic s'objectes
    static ArrayList<Alumne> alumnes = new ArrayList<Alumne>();

    public static void main(String[] args) {
        lleixirFitxer();
        //mostraMitjaPantalla();
        llistarAprovatSuspes();
        crearFitxerMitjanes();
    }

    /**
     * 
     * Read a file and create a dynamic vector of students
     */
    public static void lleixirFitxer() {
        Alumne alum;
        try {
            File myData = new File(DADES);
            Scanner lectura = new Scanner (myData);
            while (lectura.hasNextLine()) {
                String linea = lectura.nextLine();
                String[] data = linea.split(" ");

                //volem poder fer aquesta conversio ancara que afegim mes notes
                String[] data1 = Arrays.copyOfRange(data,(data.length - (N+1)),data.length);

                float [] notes = new float[data1.length];
    
                for (int i = 0; i < data1.length; i++) {
                    notes[i] = Float.parseFloat(data1[i]);
                } 

                alum = new Alumne(data[0], data[1], data[2], data[3],data[4], notes);
                alumnes.add(alum);
            }
            lectura.close();
        } catch (FileNotFoundException e) {
            System.out.println("A sorgit un error.");
            e.printStackTrace();
        }
    }

    /**
     * Display the students and their average grade
     */
    public static void mostraMitjaPantalla () {
        for (int i = 0; i < alumnes.size(); i++) {
            float media = Estadistiques.media(alumnes.get(i).getNotes());
            System.out.println("La nota media del alumne "+ alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                            + alumnes.get(i).getLastNameTwo() + " es: " + media);
         }
    }

    /**
     * Method that classifies students between passed and failed
     */
    public static void llistarAprovatSuspes () {
        System.out.println("Els seguents alumnes han superat el curs:");
        for (int i = 0; i < alumnes.size(); i++) {
            float media = Estadistiques.media(alumnes.get(i).getNotes());
            if (media > 5) {
                System.out.println(alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                            + alumnes.get(i).getLastNameTwo() + " la seva mitja es: " + media);
            }       
        }
        System.out.println("Els seguents alumnes no han superat el curs:");
        for (int i = 0; i < alumnes.size(); i++) {
            float media = Estadistiques.media(alumnes.get(i).getNotes());
            if (media < 5) {
                System.out.println(alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                            + alumnes.get(i).getLastNameTwo() + " la seva mitja es: " + media);
            }       
        }

    }

    public static void coneixerNotaMaxima () {

    }

    public static void crearFitxerMitjanes () {


    }

    public static void crearFitxerAprovatSuspes () {

    }

}