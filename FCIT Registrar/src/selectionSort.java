
public class selectionSort {

    public static void main(String[] args) {

    int arr[] = {12,3,4,33,23,4};
        System.out.println("array beforer sorting: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("");

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = 1+i; j < arr.length; j++) {
                if(arr[j]<arr[index])
                    index=j;
            }
            
            int smaller = arr[index];
            arr[index]= arr[i];
            arr[i]= smaller;
        }
        
        System.out.println("array after sorting: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("");

    }
    
    
public static void PrintDetailsStudent(Scanner read, FCITstudent students[]) {
        
        System.out.print("   Searching Indices:  ");
        int id = read.nextInt();

       
        
            int indexStd = BinarySearch(students, id);
        

        if (indexStd != -1) {
            System.out.println("\n   Success: student found."
                    + "\n   ID:   " + id + "\n   Name: "
                    + students[indexStd].getFirstName() + " "
                    + students[indexStd].getLastName() + "\n"
                    + "   Number of Courses Registered: " + students[indexStd].getNumCourses());
            if (students[indexStd].getNumCourses() > 0) {
                System.out.println("   Courses:");
                for (int j = 0; j < students[indexStd].getNumCourses(); j++) {
                    System.out.println("      " + students[indexStd].getCourses()[j]);
                }
            }

        } else {
            System.out.println("\n   Error: cannot print student details. Student ID " + id + " is not enrolled in the system.");
        }
        System.out.println("");
    }

}

/*
//
                    if (students[indexStd].getNumCourses() > 0) {
                        System.out.println("Student ID " + id + " is currently enrolled in courses.");
                        for (int j = 0; j < students[indexStd].getNumCourses(); j++) {
                            String courseNumber = students[indexStd].getCourses()[j];
                            int indexCrsInStd = FindCrsInStd(students, indexStd, courseNumber);
                            if (indexCrsInStd != -1) {
                                int indexCrs = FindCrsByName(courses, courseNumber);
                                for (int k = 0; k < courses[indexCrs].getRoster().length; k++) {
                                    if (courses[indexCrs].getRoster()[k] == id) {
                                        System.out.println("...removed from the course roster of " + courseNumber + ".");
                                        courses[indexCrs].getRoster()[k] = 0;
                                    }
                                }
                            }

                        }
                    }
                    students[indexStd] = null;
                    //shifting
                    for (int j = indexStd; j < students.length - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[students.length - 1] = null;
                    int newNumStudents = FCITstudent.getNumStudents() - 1;
                    FCITstudent.setNumStudents(newNumStudents);




*/


