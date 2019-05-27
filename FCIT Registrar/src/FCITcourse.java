
package fcit_registrar;

public class FCITcourse {
    
    private String courseNumber;
    private int roster[];
    private int maxStudents;
    private int numStudents;
    private int rank;
    private static int counterCourses = 0  ;

    public static int getCounterCourses() {
        return counterCourses;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public static void setCounterCourses(int counterCourses) {
        FCITcourse.counterCourses = counterCourses;
    }
    
    public FCITcourse(String courseNumber, int roster[], int maxStudents, int numStudents)  {
        this.courseNumber = courseNumber;
        this.roster = roster;
        this.maxStudents = maxStudents;
        this.numStudents = numStudents;
    }
    
    public FCITcourse()  {
        this.courseNumber = "";
        this.roster = new int[3];
        this.maxStudents = 0;
        this.numStudents = 0;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int[] getRoster() {
        return roster;
    }

    public void setRoster(int[] roster) {
        this.roster = roster;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }
    
    
}
