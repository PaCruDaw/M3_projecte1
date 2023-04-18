import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class FitxerSequencial {    
    //donem un nom constant al fitxer
    //afegim la ruta de forma relativa
    public static final String DADES = "fitxers/dades_alumnes.txt";
    public static final String MITJANES = "fitxers/mitjanes_alumnes.txt";
    public static final String APROBSUSP = "fitxers/listat_clasificat.txt";

    //nombre de notes que hi han al fitxer -1 
    static final int N = 2; 

    //array dinamic s'objectes
    static ArrayList<Alumne> alumnes = new ArrayList<Alumne>();

    public static void main(String[] args) {
    
        lleixirFitxer();
        String op;
        do {
            menu();
            op = System.console().readLine();
            switch (op) {
                case "a":
                    mostraMitjaPantalla();
                    break;
                case "b":
                    llistarAprovatSuspes();
                    break;
                case "c":
                    crearFitxerMitjanes();
                    crearFitxerAprovatSuspes();
                    break;
                case "d":
                    ordenacioCognom(alumnes);
                    break;
                case "e":
                    coneixerNotaMijanaMaxima ();
                    break;
                case "s":
                    op="s";
                    break;
                default:
                    System.out.println("Introdueixca una opció valida");
            }
        } while (op !="s");            
    }

    /**
     * 
     */
    public static void menu() {
        System.out.print("Menú de opcions:\n"
            + "\ta) Mostra la mitjana dels alumnes.\n"
            + "\tb) Mostra els llistats d'aprovats i suspesos.\n"
            + "\tc) Crear fitxers.\n"
            + "\td) Ordenar els alumnes per cognom amb la seva nota mitjana.\n"
            + "\te) Coneixer el alumne amb nota mitjana mes alta.\n"
            + "\ts) Sortir.\n"
            + "Introdueix una opció del menú:"
        );
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
                String[] data = linea.split("\\| ");

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
            System.out.println("\tLa nota media del alumne "+ alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
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
                System.out.println("\t" + alumnes.get(i).getName() + " " + alumnes.get(i).getLastName() + " "
                            + alumnes.get(i).getLastNameTwo() + ": " + media);
            }       
        }
        System.out.println("Els seguents alumnes no han superat el curs:");
        for (int i = 0; i < alumnes.size(); i++) {
            float media = Estadistiques.media(alumnes.get(i).getNotes());
            if (media < 5) {
                System.out.println("\t" + alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                            + alumnes.get(i).getLastNameTwo() + ": " + media);
            }       
        }

    }

    /**
     * Method that return name of student with better average grade
     */
    public static void coneixerNotaMijanaMaxima () {
        float max_med = Estadistiques.media(alumnes.get(0).getNotes());
        int alum = 0;
        for (int i =1 ; i < alumnes.size(); i++) {
            float media = Estadistiques.media(alumnes.get(i).getNotes());
            if (media > max_med) {
                alum = i;
                max_med = media;
            }
        } 
        System.out.println("\tEl alumne en millor mitjana es: " + alumnes.get(alum).getName() 
                    +" "+ alumnes.get(alum).getLastName() + " " + alumnes.get(alum).getLastNameTwo() + " amb un " + max_med);
    }

    /**
     * Create a file with students and their average grade
     */
    public static void crearFitxerMitjanes () {
        try {
            FileWriter escritura = new FileWriter(MITJANES);

            escritura.write("La nota mitjana dels alumnes es:\n");
            for (int i = 0; i < alumnes.size(); i++) {
                float media = Estadistiques.media(alumnes.get(i).getNotes());
                escritura.write(alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                            + alumnes.get(i).getLastNameTwo() + ": " + media+"\n");     
            }
            escritura.close();
        } catch (IOException e) {
            System.out.println("A sorgit un error.");
            e.printStackTrace();
        }

    }

    /**
     * Method that create a fife classifies students between passed and failed
     */
    public static void crearFitxerAprovatSuspes () {
        try {
            FileWriter escritura = new FileWriter(APROBSUSP);

            escritura.write("Els seguents alumnes han superat el curs:\n");
            for (int i = 0; i < alumnes.size(); i++) {
                float media = Estadistiques.media(alumnes.get(i).getNotes());
                if (media > 5) {
                    escritura.write(alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                                + alumnes.get(i).getLastNameTwo() + ": " + media+"\n");
                }       
            }
            escritura.write("\nEls seguents alumnes no han superat el curs:\n");
            for (int i = 0; i < alumnes.size(); i++) {
                float media = Estadistiques.media(alumnes.get(i).getNotes());
                if (media < 5) {
                    escritura.write(alumnes.get(i).getName() +" "+ alumnes.get(i).getLastName() +" "
                                + alumnes.get(i).getLastNameTwo() + ": " + media+"\n");
                }       
            }
            escritura.close();
        } catch (IOException e) {
            System.out.println("A sorgit un error.");
            e.printStackTrace();
        }
    }


    public static void ordenacioCognom (ArrayList<Alumne> alum) {
        alum.sort(Comparator.comparing(Alumne::getLastName)
            .thenComparing(Alumne::getLastNameTwo).thenComparing(Alumne::getName)); 

        for (int i = 0; i < alum.size(); i++) {
            float media = Estadistiques.media(alum.get(i).getNotes());
            System.out.println("\t" + alum.get(i).getName() +" "+ alum.get(i).getLastName() +" "
                            + alum.get(i).getLastNameTwo() + ": " + media );
        }       
    }
    
}