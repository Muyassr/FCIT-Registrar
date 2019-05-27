/*
Moyasr Tamim 
ID : 1539152
Section: DA
email : moytam.uni@gmail.com
*/


package fcit_registrar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FCIT_Registrar {

    public static void main(String[] args) throws Exception {

          
        File inputFile = new File("FCIT_Registrar.in");
         File outputFile = new File("FCIT_Registrar.out");
         PrintWriter write = new PrintWriter(outputFile);
        if (!inputFile.exists()) {
            write.println("Input file, " + inputFile + ", does not exist.");
            write.flush();
            write.close();
            System.exit(0);
            
        }
       

        Scanner read = new Scanner(inputFile);
       
      
        String command;
       
        FCITcourse[] courses = new FCITcourse[read.nextInt()];
        FCITstudent[] students = new FCITstudent[read.nextInt()];

        int number = read.nextInt();
        for (int i = 0; i < number; i++) {

            command = read.next();


            if (command.equals("OPENCOURSE")) {

                OpenCourse(read, courses,  write);

            } else if (command.equals("EXPANDCOURSE")) {

                ExpandCourse(read, courses,  write);

            } else if (command.equals("DELETECOURSE")) {

                DeleteCourse(read, courses, students,  write);

            } else if (command.equals("ENROLLSTUDENT")) {

                EnrollStudent(read, students,  write);

            } else if (command.equals("COURSEADD")) {

                CourseAdd(read, students, courses,  write);

            } else if (command.equals("COURSEDROP")) {

                CourseDrop(read, courses, students,  write);

                } else if (command.equals("DELETESTUDENT")) {

                
                DeleteStudent(read, courses, students,  write);

            } else if (command.equals("PRINTDETAILSCOURSE")) {

                PrinDetailsCourse(read, courses, students,  write);

                    } else if (command.equals("PRINTDETAILSSTUDENT")) {

                PrintDetailsStudent(read, students,  write);

            } else if (command.equals("PRINTCOURSES")) {

                PrintCourses(courses,  write);

            } else if (command.equals("PRINTSTUDENTS")) {

                PrintStudents(students,  write);

            }

        }
        
        write.flush();
        write.close();
        int r = 0 ;
    }

    public static int FindStdByID(FCITstudent students[], int id) {
        if (FCITstudent.getTotalStudents() > 0) {
            for (int i = 0; i < FCITstudent.getTotalStudents(); i++) {
                if (students[i].getID() == id) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int FindCrsByName(FCITcourse courses[], String courseNumber) {

        if (FCITcourse.getCounterCourses() == 0) {
            return -1;
        }

        for (int j = 0; j < FCITcourse.getCounterCourses(); j++) {
            if (courses[j].getCourseNumber().equals(courseNumber)) {
                return j;
            }
        }

        return -1;
    }

    public static int FindCrsInStd(FCITstudent student, String courseNumber) {
        if(student.getNumCourses()>0) {
             for (int j = 0; j < student.getNumCourses(); j++) {
            if (student.getCourses()[j].equals(courseNumber)) {
                return j;
            }
        }
        }
       
        return -1;
    }

    public static int FindStdInRoster(FCITcourse courses[], int id, int indexCrs) {
        for (int j = 0; j < courses[indexCrs].getRoster().length; j++) {
            if (id == courses[indexCrs].getRoster()[j]) {
                return j;
            }
        }
        return -1;
    }

    public static int BinarySearch(FCITstudent students[], int key, PrintWriter write) {

        int low = 0,
            high = FCITstudent.getTotalStudents()-1,
            mid = 0;

        while (low <= high) {

            mid = (low + high) / 2;

            write.print(mid + ", ");
            if (students[mid].getID() == key) {
                return mid;
            } else if (key < students[mid].getID()) {
                high = mid - 1;
            } else if (key > students[mid].getID()) {
                low = mid + 1;
            }

        }

        return -1;
    }

    public static void OpenCourse(Scanner read, FCITcourse courses[], PrintWriter write) {
        write.println("COMMAND: OPENCOURSE");

        String courseNumber = read.next();
        int rank = Integer.parseInt(courseNumber.split("-")[1]);
        int num = read.nextInt();
        int index = FindCrsByName(courses, courseNumber);
        if (index == -1) {
            FCITcourse newCourse = new FCITcourse();

            int roster[] = new int[num];
            newCourse.setMaxStudents(num);
            newCourse.setCourseNumber(courseNumber);
            newCourse.setRank(rank);
            newCourse.setRoster(roster);
            courses[FCITcourse.getCounterCourses()] = newCourse;
            int newCounterCourses = FCITcourse.getCounterCourses() + 1;
            FCITcourse.setCounterCourses(newCounterCourses);
            
             if (FCITstudent.getTotalStudents() > 0) {
                    //sorting 
                    for (int i = 0; i < FCITcourse.getCounterCourses() - 1; i++) {
                        int ind = i;
                        for (int j = i + 1; j < FCITcourse.getCounterCourses(); j++) {
                            if (courses[j].getRank() < courses[ind].getRank()) {
                                ind = j;
                            }
                            FCITcourse smaller = courses[ind];
                            courses[ind] = courses[i];
                            courses[i] = smaller;
                        }
                    }
                }
            
            write.println("   Success: Course " + courseNumber + " is now open with a maximum capacity of "
                    + num + " students.");

        } else {
            write.println("   Error: Cannot open course. Course is already open in the system.");
        }
        write.println("");

    }

    public static void ExpandCourse(Scanner read, FCITcourse courses[], PrintWriter write) {
        write.println("COMMAND: EXPANDCOURSE");
        String courseNumber = read.next();
        int newCpacity = read.nextInt();
        //course open ?
        int indexCrs = FindCrsByName(courses, courseNumber);
        if (-1 != indexCrs) {
            //newCapacity is bigger?
            if (newCpacity > courses[indexCrs].getMaxStudents()) {
                int newRoster[] = new int[newCpacity];
                //filling the new array
                for (int j = 0; j < courses[indexCrs].getRoster().length; j++) {
                    newRoster[j] = courses[indexCrs].getRoster()[j];
                }
                courses[indexCrs].setRoster(newRoster);
                courses[indexCrs].setMaxStudents(newCpacity);
                write.println("   Success: Course " + courseNumber
                        + " has been expanded to hold " + newCpacity + " students.");
            } else {
                write.println("   Error: Cannot expand course " + courseNumber + ".\n"
                        + "          New capacity (" + newCpacity + ") is less than or equal to current capacity ("
                        + courses[indexCrs].getRoster().length + ").");
            }
        } else {
            write.println("   Error: Cannot expand course " + courseNumber
                    + ". Course is not currently open in the system.");
        }
        write.println("");
    }

    public static void DeleteCourse(Scanner read, FCITcourse courses[], FCITstudent students[], PrintWriter write) {
        write.println("COMMAND: DELETECOURSE");
        String courseNumber = read.next();
        int indexCrs = FindCrsByName(courses, courseNumber);
        //is the course exsisting ?
        if (-1 != indexCrs) {
            for (int j = 0; j < courses[indexCrs].getNumStudents(); j++) {
                int StdID = courses[indexCrs].getRoster()[j];
                int indexStd = FindStdByID(students, StdID);
                int indexCrsInStd = FindCrsInStd(students[indexStd], courseNumber);
//                if ((StdID != 0) && (indexStd != -1) && (indexCrsInStd != -1)) {
                    // deleting course from student's courses Array 
                    students[indexStd].getCourses()[indexCrsInStd] = "nothing";
                    int newNumCourses = students[indexStd].getNumCourses() - 1;
                    students[indexStd].setNumCourses(newNumCourses);
                    //shifting
                    for (int k = indexCrsInStd; k < students[indexStd].getCourses().length - 1; k++) {
                        students[indexStd].getCourses()[k] = students[indexStd].getCourses()[k + 1];
                    }
                    students[indexStd].getCourses()[students[indexStd].getCourses().length - 1] = "nothing";

//                }
//                else {
//                    write.println("StdID: " + StdID + "\tindexStd: " + indexStd + "\tindexCrsInStd: " + indexCrsInStd);
//                    
//                }
            }
            //delete the course
            courses[indexCrs] = null;
            //shifting
            for (int i = indexCrs; i < courses.length - 1; i++) {
                courses[i] = courses[i + 1];
            }
            courses[courses.length - 1] = null;
            write.println("   Success: Course " + courseNumber + " has been deleted from the system.");
            //decrease counterCourses
            int newCounterCourses = FCITcourse.getCounterCourses() - 1;
            FCITcourse.setCounterCourses(newCounterCourses);

        } else {
            write.println("   Error: Cannot delete course. Course is not currently open in the system.");
        }
        write.println("");
    }

    public static void EnrollStudent(Scanner read, FCITstudent students[], PrintWriter write) {

        write.println("COMMAND: ENROLLSTUDENT");
        int id = read.nextInt();
        String firstName = read.next();
        String lastName = read.next();
        int indexStd = FindStdByID(students, id);
        //is he already enrolld
        if ((indexStd == -1)) {
            //reached its maximum capacity of students ?
            if (students[students.length - 1] == null) {
                FCITstudent newStudent = new FCITstudent();
                newStudent.setID(id);
                newStudent.setFirstName(firstName);
                newStudent.setLastName(lastName);
                students[FCITstudent.getTotalStudents()] = newStudent;

                int newTotalStudent = FCITstudent.getTotalStudents() + 1;
                FCITstudent.setTotalStudents(newTotalStudent);
                if (FCITstudent.getTotalStudents() > 0) {
                    //sorting 
                    for (int i = 0; i < FCITstudent.getTotalStudents() - 1; i++) {
                        int ind = i;
                        for (int j = i + 1; j < FCITstudent.getTotalStudents(); j++) {
                            if (students[j].getID() < students[ind].getID()) {
                                ind = j;
                            }
                            FCITstudent smaller = students[ind];
                            students[ind] = students[i];
                            students[i] = smaller;
                        }
                    }
                }
                write.println("   Success: " + firstName + " " + lastName + " (" + id + ") successfully enrolled in the system.");
            } else {
                write.print("   Error: cannot enroll student " + firstName + " " + lastName + " ("
                        + id + "). FCIT Registrar has already reached ");
                write.println("its maximum capacity of students.");
            }

        } else {
            write.println("   Error: cannot enroll student. Student already exists in the system.");
        }

        write.println("");
    }

    public static void CourseAdd(Scanner read, FCITstudent students[], FCITcourse courses[], PrintWriter write) {

        write.println("COMMAND: COURSEADD");
        int id = read.nextInt();
        int indexStd = FindStdByID(students, id);
        String courseNumber = read.next();
        int indexCrs = FindCrsByName(courses, courseNumber);
        //#1Student exsist ?
        if (indexStd != -1) {
            //#2Exsisting course ?
            if (indexCrs != -1) {
                //#3Student's schdule's capacity
                if (6 > students[indexStd].getNumCourses()) {
                    //#4is this course full?
                    if (courses[indexCrs].getMaxStudents() > courses[indexCrs].getNumStudents()) {
                        //add Student to course's roster
                        courses[indexCrs].getRoster()[courses[indexCrs].getNumStudents()]
                                = students[indexStd].getID();
                        int newNumStudent = courses[indexCrs].getNumStudents() + 1;
                        courses[indexCrs].setNumStudents(newNumStudent);
                        //add course to student's schedule
                        students[indexStd].getCourses()[students[indexStd].getNumCourses()]
                                = courses[indexCrs].getCourseNumber();
                        students[indexStd].setNumCourses(students[indexStd].getNumCourses() + 1);

                        write.println("   Success: Course Number " + courseNumber
                                + " has been added to the schedule of Student ID " + students[indexStd].getID() + ".");
                    } else {
                        write.println("   Error: cannot add course. Course Number "
                                + courseNumber + " has already reached maximum capacity.");
                    }
                } else {
                    write.println("   Error: cannot add course. Student ID " + id
                            + " already has six registered courses.");
                }
            } else {
                write.println("   Error: cannot add course. Course Number "
                        + courseNumber + " is not open for enrollment.");
            }
        } else {
            write.println("   Error: cannot add course. Student ID "
                    + id + " is not enrolled in the system.");
        }
        write.println("");
    }

    public static void CourseDrop(Scanner read, FCITcourse courses[], FCITstudent students[], PrintWriter write) {
        write.println("COMMAND: COURSEDROP");
        int id = read.nextInt();
        String courseNumber = read.next();

        int indexStd = FindStdByID(students, id);
        //Student exsist ?
        if (indexStd != -1) {
            int indexCrs = FindCrsByName(courses, courseNumber);
            //Exsisting course ?
            if (indexCrs != -1) {
                int indexCrsInStd = FindCrsInStd(students[indexStd], courseNumber);
                //Has the student actually registered for the course ?
                if (indexCrsInStd != -1) {
                    //remove Student from course's roster
                    int indexStdInRoster = FindStdInRoster(courses, id, indexCrs);
                    courses[indexCrs].getRoster()[indexStdInRoster] = 0;
                    // shifting
                    for (int r = indexStdInRoster; r < courses[indexCrs].getRoster().length - 1; r++) {
                        courses[indexCrs].getRoster()[r] = courses[indexCrs].getRoster()[r + 1];
                    }
                    courses[indexCrs].getRoster()[courses[indexCrs].getRoster().length - 1] = 0;
                    //decrease numStudent
                    int newNumStudent = courses[indexCrs].getNumStudents() - 1;
                    courses[indexCrs].setNumStudents(newNumStudent);
                    //remove course from student's schedule
                    students[indexStd].getCourses()[indexCrsInStd] = "nothing";
                    //shifting
                    for (int r = indexCrsInStd; r < students[indexStd].getCourses().length - 1; r++) {
                        students[indexStd].getCourses()[r] = students[indexStd].getCourses()[r + 1];
                    }
                    students[indexStd].getCourses()[students[indexStd].getCourses().length - 1] = "nothing";
                    //decrease numCourses
                    int newNumCourses = students[indexStd].getNumCourses() - 1;
                    students[indexStd].setNumCourses(newNumCourses);

                    write.println("   Success: Course " + courseNumber
                            + " dropped from the schedule of Student ID " + students[indexStd].getID() + ".");

                } else {
                    write.println("   Error: cannot drop course " + courseNumber
                            + ". Course is not found in the schedule of Student ID " + students[indexStd].getID() + ".");
                }
            } else {

                write.println("   Error: cannot drop course. Course Number "
                        + courseNumber + " is not open for enrollment.");
            }
        } else {
            write.println("   Error: cannot drop course. Student ID "
                    + id + " is not enrolled in the system.");
        }

        write.println("");
    }

    public static void DeleteStudent(Scanner read, FCITcourse courses[], FCITstudent students[], PrintWriter write) {
        write.println("COMMAND: DELETESTUDENT");
        int id = read.nextInt();
        int indexStd = FindStdByID(students, id);
        //is student enrolled in the system?
        if (indexStd != -1) {
            RemoveStdsCrs(courses, students[indexStd],  write);
            students[indexStd] = null;
            //shifting
        for (int i = indexStd; i < students.length-1; i++) {
            students[i]=students[i+1];
        }
        students[students.length-1]=null;
          
        int newTotalStudents = FCITstudent.getTotalStudents() - 1 ;
        FCITstudent.setTotalStudents(newTotalStudents);
            
            write.println("   Success: Student ID " + id + " has been deleted from the system.\n");
        } else {
            write.println("   Error: cannot delete student. Student ID " + id + " is not enrolled in the system.");
        }
        
    }

    public static void RemoveStdsCrs(FCITcourse courses[], FCITstudent student, PrintWriter write) {
        //this method is to remove the courses from the student's schedule
        //and remove the student from courses' roster

        //does the studnet have any courses? 
        if (student.getNumCourses() > 0) {
            write.println("   Student ID " + student.getID() + " is currently enrolled in courses.");
        }

        for (int i = 0; i < student.getNumCourses(); i++) {//loop over student's courses' Array to remove courses
            String courseNumber = student.getCourses()[i];
            write.println("   ...removed from the course roster of " + courseNumber + ".");
            int indexCrs = FindCrsByName(courses, courseNumber);
            for (int j = 0; j < courses[indexCrs].getRoster().length; j++) {//loop to delete ID from Roster
                if (courses[indexCrs].getRoster()[j] == student.getID()) {
                    courses[indexCrs].getRoster()[j] = 0;
                    //shifting 
                    for (int k = j; k < courses[indexCrs].getRoster().length - 1; k++) {
                        courses[indexCrs].getRoster()[k] = courses[indexCrs].getRoster()[k + 1];
                    }
                    courses[indexCrs].getRoster()[courses[indexCrs].getRoster().length - 1] = 0;
                    //decrease numStudnet
                    int newNumStudents = courses[indexCrs].getNumStudents() - 1;
                    courses[indexCrs].setNumStudents(newNumStudents);
                    break;
                }
            }

        }

    }

    public static void PrinDetailsCourse(Scanner read, FCITcourse courses[], FCITstudent students[], PrintWriter write) {
        String courseNumber = read.next();
        write.println("COMMAND: PRINTDETAILSCOURSE");

        int indexCrs = -1;

        if (FCITcourse.getCounterCourses() > 0) {
            indexCrs = FindCrsByName(courses, courseNumber);
        }

        if (indexCrs != -1) {
            write.println("   Course Number:                  " + courseNumber);
            write.println("   Number of Students Registered:  " + courses[indexCrs].getNumStudents());
            
            
            for (int j = 0; j < courses[indexCrs].getNumStudents(); j++) {
                if(j==0){
                    write.println("   Course Roster:");}
                int indexStd = FindStdByID(students, courses[indexCrs].getRoster()[j]);
                write.println("      ID " + students[indexStd].getID() + " ("
                        + students[indexStd].getFirstName() + " " + students[indexStd].getLastName() + ")");
            }

        } else {
            write.println("   Error: cannot print course details. Course Number "
                    + courseNumber + " is not open for enrollment.");
        }
        write.println("");

    }

    public static void PrintDetailsStudent(Scanner read, FCITstudent students[], PrintWriter write) {
        write.println("COMMAND: PRINTDETAILSSTUDENT");
        write.print("   Searching Indices:  ");
        int id = read.nextInt();

       
        
            int indexStd = BinarySearch(students, id, write);
        

        if (indexStd != -1) {
            write.println("\n   Success: student found."
                    + "\n   ID:   " + id + "\n   Name: "
                    + students[indexStd].getFirstName() + " "
                    + students[indexStd].getLastName() + "\n"
                    + "   Number of Courses Registered: " + students[indexStd].getNumCourses());
            if (students[indexStd].getNumCourses() > 0) {
                write.println("   Courses:");
                for (int j = 0; j < students[indexStd].getNumCourses(); j++) {
                    write.println("      " + students[indexStd].getCourses()[j]);
                }
            }

        } else {
            write.println("\n   Error: cannot print student details. Student ID " + id + " is not enrolled in the system.");
        }
        write.println("");
    }
    
    public static void PrintCourses(FCITcourse courses[], PrintWriter write) {

        write.println("COMMAND: PRINTCOURSES");
        if (FCITcourse.getCounterCourses() > 0) {
             //sorting 
                    for (int i = 0; i < FCITcourse.getCounterCourses() - 1; i++) {
                        int ind = i;
                        for (int j = i + 1; j < FCITcourse.getCounterCourses(); j++) {
                            if (courses[j].getRank() < courses[ind].getRank()) {
                                ind = j;
                            }
                            FCITcourse smaller = courses[ind];
                            courses[ind] = courses[i];
                            courses[i] = smaller;
                        }
                    }
            for (int j = 0; j < FCITcourse.getCounterCourses(); j++) {
                write.println("   " + courses[j].getCourseNumber()
                        + " (Number of Registered Students: " + courses[j].getNumStudents() + ")");
            }
        } else {
            write.println("   Error: there are no open courses in the system.");
        }
        write.println("");
    }

    public static void PrintStudents(FCITstudent students[], PrintWriter write) {
        write.println("COMMAND: PRINTSTUDENTS");
        if(FCITstudent.getTotalStudents() > 0) {
               for (int j = 0; j < FCITstudent.getTotalStudents(); j++) {
            if(students[j]==null)
                break; 
            
            write.println("   " + students[j].getID() + " - " + students[j].getFirstName()
                    + " " + students[j].getLastName() + "   (Number of Courses Registered: "
                    + students[j].getNumCourses() + ")");
        }
        } else {
            write.println("   Error: there are no students enrolled in the system.");
        }
     

        write.println("");
    }
}
