/**
 * @author Paula Cruzado Escura
 * The student class, is intended to manage student data, collects information about a student
 */
public class Alumne {

    private String id; //student identifier
    private String classroom; //students class
    private String name;  //student's name
    private String lastName;  //student's last name
    private String lastName2; //second student's last name
    private float[] notes; //note vector

    //constructor amb tots els parametres
    public Alumne (String id, String classroom, String name, 
        String lastName, String lastName2, float[] notes) {

            this.id = id;
            this.classroom = classroom;
            this.name = name;
            this.lastName = lastName;
            this.lastName2 = lastName2;
            this.notes = notes.clone();
    }

    //constructor 
    public Alumne() {

    }


    public void setId (String id) {
        this.id = id;
    }
    
    public void setClassroom (String classroom) {
        this.classroom = classroom;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public void setLastNameTwo (String lastName) {
        this.lastName2 = lastName;
    }

    public void setNotes (float[] notes) {
        //this.notes = notes tindria la mateixa referencia en memoria, seria el mateix vector, volem duplicar
        this.notes = notes.clone(); 
    }

    public String getId () {
        return id;
    }

    public String getClassroom () {
        return classroom;
    }

    public String getName () {
        return name;
    }

    public String getLastName () {
        return lastName;
    }

    public String getLastNameTwo () {
        return lastName2;
    }

    public float[] getNotes () {
        return notes;
    }
}
