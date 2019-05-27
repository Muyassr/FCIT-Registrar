
package fcit_registrar;

public class FCITstudent {
    
    private int ID;
    private String firstName;
    private String lastName; 
    private String[] courses;
    private int numCourses;
    private static int totalStudents = 0 ;

    public static int getTotalStudents() {
        return totalStudents;
    }

    public static void setTotalStudents(int totalStudents) {
        FCITstudent.totalStudents = totalStudents;
    }
    
    public FCITstudent(int ID, String firstName, String lastName, String[] courses, int numCourses) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = new String[6];
        this.numCourses = numCourses;
       
    }
    
    public FCITstudent() {
        this.ID = 0;
        this.firstName = "";
        this.lastName = "";
        this.courses = new String[6];
        this.numCourses = 0;
        
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

     public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public int getNumCourses() {
        return numCourses;
    }

    public void setNumCourses(int numCourses) {
        this.numCourses = numCourses;
    }

    
}
