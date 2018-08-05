/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog.ca1;

/**
 *
 * @author guozh
 */
//
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.util.Date;
//import static programming.assignment.Student.student;

public class DMITStudents {

    //Array index will be set to 3 as location 0 to 2 has been initized.
    private static int MainIndex = 3;
    //Set and initialize array of Student class name,course,gender and contact respectively with a size of 4
    private static Student[] student = new Student[MainIndex + 1];
    //Set and initalize array of Temp class name,course,gender and contact respectively with a size of 5
    private static Student[] Temp = new Student[MainIndex + 2];

    // A method that will play a sound in java
    /**
     * InputStream data type called music is created File(x) is the wave file
     * music = File(x) AudioStream sound is created, allowing music to play
     */
    public static void playMusic(String x) {
        try {
            InputStream music;
            music = new FileInputStream(new File(x));
            AudioStream sound = new AudioStream(music);
            AudioPlayer.player.start(sound);
        } catch (Exception e) {

        }
    }

    // A method that will play the Windows 10 Error sound
    /**
     * Windows 10 Error sound will be played using the playMusic() method
     */
    public static void errorSound() {
        playMusic("Windows 10 Error Sound.wav");
    }

    // Display student but in a table form
    /**
     * Display student records in a tabular format Using JTable, an array
     * StudentLabel is created, displaying the headers of the table a 2d array.
     * displaystudent is created, inputting the student object in the array
     * within 0 and mainindex range JOptionPane is used to display the Jtable
     * First, displayStudents.setAutoCreateRowSorter(true); allows the user to
     * sort the table
     * displayStudents.getTableHeader().setReorderingAllowed(false); disables
     * dragging of column
     */
    public static void displayStudentTable() {

        Object[][] displayStudent = new Object[MainIndex][5];
        int a = 0;
        String[] Studentlabel = {"S/N", "Name", "Gender", "Course", "Contact"};
        for (int i = 0; i < MainIndex; i++) {
            a = 0;
            while (a < 5) {
                displayStudent[i][a] = i + 1;
                a++;
                displayStudent[i][a] = student[i].getName();
                a++;
                displayStudent[i][a] = student[i].getGender();
                a++;
                displayStudent[i][a] = student[i].getCourse();
                a++;
                displayStudent[i][a] = student[i].getMobile();
                a++;
            }
        }
        JTable displayStudents = new JTable(displayStudent, Studentlabel);
        displayStudents.setEnabled(false);
        displayStudents.setAutoCreateRowSorter(true);
        displayStudents.getTableHeader().setReorderingAllowed(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(displayStudents), "DMIT", JOptionPane.INFORMATION_MESSAGE);

    }

    // A method that will shift the content of an old array to a new temp array eith locations one unit higher than the old array. The old array will then have 2 extra locations added
    /**
     * initial length of student is 4 initial length of Temp is 5 when a user
     * adds a new student, the MainIndex is 4 Hence, the length + 2 is 6. This
     * does not create the temp array yet as this is the beginning as the new
     * name is within the range of the MainIndex[4] and size of student array[4]
     * After the initial adding of students, the subsequent adding of students
     * will provoke this method, this method will then recreate the Temp array
     * that is the same size as the Index Index is the same as MainIndex : index
     * = MainIndex After the Temp array has been created, the index will
     * increment by 1 The contents of Temp will then be moved to the new sized
     * student array The process repeats of every new student added In summary,
     * If MainIndex + 2 = 6 --> Do not recreate any array { Temp size -->
     * MainIndex student size --> MainIndex+1 } for every new student added
     */
    public static void changingArray() {
        int index = MainIndex; // get current index 
        index++;
        //initial length of the array
        if (MainIndex + 2 == 6) {
            //Do nothing
        } else {
            Temp = new Student[index];
        }
        for (int i = 0; i < MainIndex; i++) {
            Temp[i] = new Student(student[i].getName(), student[i].getCourse(), student[i].getMobile(), student[i].getGender(), student[i].getTime());
        }
        index++;
        student = new Student[index];
        for (int i = 0; i < MainIndex; i++) {
            student[i] = new Student(Temp[i].getName(), Temp[i].getCourse(), Temp[i].getMobile(), Temp[i].getGender(), Temp[i].getTime());
        }
    }

    // A method that will end the process 
    // return in the DMITStudentUser will close the program
    public static void programTerminated() {
        JOptionPane.showMessageDialog(null,
                "Program terminated."
                + "\nThank You!");
    }

    // Method that will allow user to edit Student's info
    /**
     * Method ask the user to input a name to edit the students and store it in
     * String name. The name.equals(student[index].getName()) finds the student
     * by comparing the name and the specific student array location. If it does
     * not find the name, it will increase index by 1 and the whole process
     * repeats Even the process starts, there is a validation name.matches("^[
     * A-Za-z]+$") , it means that user can only input spacing and letters, it
     * cannot input special characters like numbers If name is null, it implies
     * that the user has clicked on the "Cancel" button. Hence, it will return
     * to the main menu If the index == MainIndex - 1, the process will stop as
     * it has reached the end of the array. Hence, it will tell the user that
     * the name does not exist If the name is found, it will show all the
     * details of the student: name;gender;course;mobile number as well as the
     * time and date that the user has edit If there is no edit, it will display
     * as "--" Users can choose to edit the name,gender,mobile number or course.
     * Moreover, if the user amend the name but is the same as the original
     * name, it will tell the user that the name has already been added
     */
    public static void editInfo() {

        String edit = "";
        int flag = 0;
        String name = "";
        int option;
        String stroption;
        int index = 0;
        Date date = new Date();
        while (!name.matches("^[ A-Za-z]+$")) {
            name = JOptionPane.showInputDialog(null,
                    "Please enter student's name to edit",
                    "DMIT Student", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                return;
            }
        }

        do {
            for (index = 0; index < MainIndex; index++) {
                if (name.equalsIgnoreCase(student[index].getName())) {
                    Student editStudent = new Student();

                    do {

                        stroption = JOptionPane.showInputDialog(null,
                                "Name      :" + student[index].getName() + "\n"
                                + "Gender    :" + student[index].getGender() + "\n"
                                + "Course    :" + student[index].getCourse() + "\n"
                                + "Mobile     :" + student[index].getMobile() + "\n"
                                + "Which componemnt would you like to amend? \n"
                                + "1) Name \n"
                                + "2) Gender \n"
                                + "3) Course \n"
                                + "4) Mobile \n"
                                + "5) Exit \n"
                                + "Last edited:" + student[index].getTime(),
                                "DMIT Students", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            option = Integer.parseInt(stroption);
                        } catch (NumberFormatException e) {
                            stroption = "0";
                        }
                        option = Integer.parseInt(stroption);
                        String editName;
                        String editCourse;
                        int editNumber;
                        char editGender;
                        String msg;
                        if (option == 1) {
                            msg = "Please type student's new name:";
                            editName = checkString(msg);

                            if (editName.equals("*") || checkDuplicate(editName) == true) {
                            } else {
                                student[index] = new Student(editName, student[index].getCourse(), student[index].getMobile(), student[index].getGender(), date.toString());
                            }

                        } else if (option == 2) {
                            msg = "Please type new gender:";
                            editGender = checkChar(msg);
                            if (editGender == '*') {
                            } else {
                                student[index] = new Student(student[index].getName(), student[index].getCourse(), student[index].getMobile(), editGender, date.toString());
                            }
                        } else if (option == 3) {
                            msg = "Please enter course name:";
                            editCourse = checkString(msg);
                            if (editCourse == "*") {
                            } else {
                                student[index] = new Student(student[index].getName(), editCourse, student[index].getMobile(), student[index].getGender(), date.toString());
                            }
                        } else if (option == 4) {
                            msg = "Please enter your new number:";
                            editNumber = checkNum(msg);
                            if (editNumber == 0) {
                            } else {
                                student[index] = new Student(student[index].getName(), student[index].getCourse(), editNumber, student[index].getGender(), date.toString());
                            }
                        } else if (option == 5) {
                            return;
                        }
                    } while (stroption != "");
                    return;
                } else {
                    if (index == MainIndex - 1) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot find info of " + name, "DMIT Students",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    flag = 1;
                }
            }
        } while (flag == 1);
    }

    // Method that will check for duplicate names
    /**
     * This method will check the duplicate when a user adds a new student When
     * a user inputs a name, the name is first compared with the name in the
     * array. If there is a match, it will tell the user that the name has
     * already been added
     */
    public static boolean checkDuplicate(String name) {
        int flag = 0;
        do {
            for (int index = 0; index < MainIndex; index++) {
                if (name.equalsIgnoreCase(student[index].getName())) {
                    JOptionPane.showMessageDialog(null,
                            "This name has already been added", "DMIT", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    if (index == MainIndex - 1) {
                        return false;
                    }
                    flag = 1;
                }
            }
        } while (flag == 1);
        return false;
    }

    // A method that sets up the three inital students' information
    /**
     * When the program starts The first three student stated in the Assignment
     * outline, will be added
     */
    public static void initaliseArray() {
        String set_date = "--";
        //Data
        student[0] = new Student("Grace Teo", "DIT", 91111111, 'F', set_date);
        student[1] = new Student("Kenny Tan", "DIT", 92222222, 'M', set_date);
        student[2] = new Student("Peter Low", "DIT", 93333333, 'M', set_date);
    }

    // A Method that allows users to view Students' information
    /**
     * The output array is created to allow the contents of student array to be
     * displayed output[0] stores the header of the table:" S/N Name Gender
     * Course Contact" The first for loop sets the student array to be "null"
     * for String, 0 for numbers and '0' for char This is show that when the
     * number is displaying, it will not cause a NullPointerException The second
     * for loop process the moving of student array data to output[i] i will
     * increase for each row of student until it is equal to the size of the
     * MainIndex After the loop is done, it will display it using
     * JOptionPane.showMessageDialog
     */
    public static void viewStudents() {
        for (int i = MainIndex; i < student.length; i++) {
            student[i] = new Student("null", "null", 0, '0', "null");
        }
        String[] output = new String[MainIndex + 1];
        output[0] = " S/N  Name       Gender   Course     Contact";
        for (int i = 1; i <= MainIndex; i++) {
            if (student[i].getName() != null) {
                //int y = i +1;
                String Temp = i + " " + student[i - 1].getName() + "        "
                        + student[i - 1].getGender() + "            "
                        + student[i - 1].getCourse() + "          "
                        + student[i - 1].getMobile();
                output[i] = Temp;
            } else {
                break;
            }
        }
        JOptionPane.showMessageDialog(null, output);

    }

    // A message that display if user type a number which is not an option in JOptionPane
    /**
     * The method will be used if the user has entered any values besides the
     * numbers in the main menu
     */
    public static void invalidOption() {
        JOptionPane.showMessageDialog(null,
                "Invalid option! Please enter in the range from 1 to 7",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    // A method that allows users to find specific student's information
    /**
     * searchStudent method finds the students the user want Method ask the user
     * to input a name to edit the students and store it in String name. The
     * name.equals(student[index].getName()) finds the student by comparing the
     * name and the specific student array location. If it does not find the
     * name, it will increase index by 1 and the whole process repeats Even the
     * process starts, there is a validation name.matches("^[ A-Za-z]+$") , it
     * means that user can only input spacing and letters, it cannot input
     * special characters like numbers After the index of the student has been
     * found, it will display the student's name,gender,course and mobile number
     * If the index == MainIndex - 1,the process will stop as it has reached the
     * end of the array. Hence, it will tell the user that the name does not
     * exist
     */
    public static void searchStudent() {
        int flag = 0;
        String name;
        name = JOptionPane.showInputDialog(null,
                "Please enter student's name to search",
                "DMIT Student", JOptionPane.QUESTION_MESSAGE);
        try {
            name.matches("^[ A-Za-z]+$");
        } catch (NullPointerException e) {
            return;
        }
        while (!name.matches("^[ A-Za-z]+$")) {

            name = JOptionPane.showInputDialog(null,
                    "Please enter student's name to search",
                    "DMIT Student", JOptionPane.QUESTION_MESSAGE);
            try {
                name.matches("^[ A-Za-z]+$");
            } catch (NullPointerException e) {
                return;
            }
        }

        do {
            for (int index = 0; index < MainIndex; index++) {
                if (name.equalsIgnoreCase(student[index].getName())) {
                    JOptionPane.showMessageDialog(null,
                            "Name      :" + student[index].getName() + "\n"
                            + "Gender    :" + student[index].getGender() + "\n"
                            + "Course    :" + student[index].getCourse() + "\n"
                            + "Mobile     :" + student[index].getMobile(),
                            "DMIT Students", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {

                    if (index == MainIndex - 1) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot find info of " + name, "DMIT Students",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    flag = 1;
                }
            }
        } while (flag == 1);
    }

    // A method that adds Student's infromation - name,course,contact number and gender
    /**
     * When a user wants to add a student, it will ask for the
     * name,gender,course and mobile It will be stored in a temp variable, after
     * everything has been recorded, it will be stored in the student array
     * MainIndex will then increment by 1 When a user adds a name, it will
     * activate the CheckDuplicate method, if the name is the same as the name
     * in the array, it will tell the user that that name has already been added
     * and cannot be added anymore
     */
    public static void addStudent() {
        //Add name
        String name;
        String msg = "Please enter student's name:";
        name = checkString(msg);
        if (name.equals("*") || checkDuplicate(name) == true) {
            return;
        }
        //Add course
        msg = "Please enter student's course:";
        String course = checkString(msg);
        if (course.equals("*")) {
            return;
        }
        //Add number
        msg = "Please enter student's mobile:";
        int mobile = checkNum(msg);
        if (mobile == 0) {
            return;
        }
        //Add gender
        msg = "Please enter student's gender:";
        char gender = checkChar(msg);
        if (gender == '*') {
            return;
        }
        student[MainIndex] = new Student(name, course, mobile, gender, "null");
        MainIndex++;
        changingArray();

        JOptionPane.showMessageDialog(null, "The new student has been added successfully", "DMIT Student", JOptionPane.INFORMATION_MESSAGE);
    }

    // A method that delete a specific student's information
    /**
     * Method ask the user to input a name to edit the students and store it in
     * String name. The name.equalsIgnoreCase(student[index].getName()) finds
     * the student by comparing the name and the specific student array
     * location. If it does not find the name, it will increase index by 1 and
     * the whole process repeats Even the process starts, there is a validation
     * name.matches("^[ A-Za-z]+$") , it means that user can only input spacing
     * and letters, it cannot input special characters like numbers suppose the
     * location of the to be deleted student is i content of the next student (
     * student[i + 1] ) is shifted into Temp variables. After all the variables
     * have been moved. The Temp variables will then moved into student[i].
     * Hence, overwriting the contents of i, essentially deleting it If the i is
     * increment out of the for loop, the method will tell the user that the
     * student they are deleting does not exist
     */
    public static void deleteStudents() {
        String TempName;
        String TempCourse;
        String nameDelete;
        int TempMobile;
        char TempGender;
        String TempDate;
        int flag = 0;
        nameDelete = JOptionPane.showInputDialog(null, "Please enter student's name to delete.");
        if (nameDelete == null) {
            return;
        }
        while (!nameDelete.matches("^[ A-Za-z]+$")) {
            nameDelete = JOptionPane.showInputDialog(null, "Please enter student's name to delete.");
            if (nameDelete == null) {
                return;
            }
        }
        for (int i = 0; i < MainIndex; i++) {
            if (nameDelete.equalsIgnoreCase(student[i].getName())) {
                JOptionPane.showMessageDialog(null, nameDelete + " has been deleted successfully.", "DMIT Students", JOptionPane.INFORMATION_MESSAGE);
                for (int a = i; a < MainIndex - 1; a++) {
                    TempName = student[a + 1].getName();
                    TempCourse = student[a + 1].getCourse();
                    TempMobile = student[a + 1].getMobile();
                    TempGender = student[a + 1].getGender();
                    TempDate = student[a + 1].getTime();
                    student[a] = new Student(TempName, TempCourse, TempMobile, TempGender, TempDate);
                }
                flag = 1;
            }
        }
        if (flag == 1) {
            MainIndex = MainIndex - 1;
            changingArray();
        }
        if (flag != 1) {
            errorSound();
            JOptionPane.showMessageDialog(null, "The student name " + nameDelete + " cannot be found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method that will validate input for char data type as well as asking the user for input
    /**
     * This method will ask the user to input a char input using JOptionPane As
     * well as validating the user's input for example,
     * variable.matches("[a-zA-Z_]+") will only allow user to only input
     * characters, not numbers if a user click a "Cancel" button, it will return
     * a '*', this variable will return to the main menu method will only accept
     * 'M' or 'F'. If the user enters a small letter of the characters, it will
     * turn it into uppercase letters
     */
    public static char checkChar(String msg) {
        String variable = "";
        variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
        try {
            variable.matches("[a-zA-Z_]+");
        } catch (NullPointerException e) {
            return '*';
        }
        while ((!variable.matches("[a-zA-Z_]+"))) {
            variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
            try {
                variable.matches("[a-zA-Z_]+");
            } catch (NullPointerException e) {
                return '*';
            }
        }
        char checkFirstLetter = variable.charAt(0);
        if (checkFirstLetter == 'B' || checkFirstLetter == 'b') {
            variable = "M";
        } else if (checkFirstLetter == 'G' || checkFirstLetter == 'g') {
            variable = "F";
        } else if (checkFirstLetter == 'm' || checkFirstLetter == 'M') {
            variable = "M";
        } else if (checkFirstLetter == 'f' || checkFirstLetter == 'F') {
            variable = "F";
        } else {
            variable = "N";
        }
        char charnewgender = variable.charAt(0);
        char newgender = Character.toUpperCase(charnewgender);
        return newgender;
    }

    // A method that will validate input for int data type as well as asking the user for input
    /**
     * This method will ask the user to input a int input using JOptionPane As
     * well as validating the user's input for example,
     * variable.matches("[0-9]+") will only allow user to only input numbers,
     * not characters if a user click a "Cancel" button, it will return a 0,
     * this variable will return to the main menu method will only accept up to
     * 8 numbers long. Anything less or more will not be accepted
     */
    public static int checkNum(String msg) {
        String variable = "";
        variable = JOptionPane.showInputDialog(null, msg);
        try {
            while (!variable.matches("[0-9]+") || !(variable.length() == 8)) {
                variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
            }
        } catch (NullPointerException e) {
            return 0;
        }
        while (!variable.matches("[0-9]+") || !(variable.length() == 8)) {
            variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
            try {
                while (!variable.matches("[0-9]+") || !(variable.length() == 8)) {
                    variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
                }
            } catch (NullPointerException e) {
                return 0;
            }
        }
        int integer = Integer.parseInt(variable);
        return integer;
    }

    // A method that will validate input for String data type as well as asking the user for input
    /**
     * This method will ask the user to input a String input using JOptionPane
     * As well as validating the user's input for example,
     * variable.matches("[a-zA-Z_]+") and variable.matches("^[ A-Za-z]+$") will
     * only allow user to only input letters, not number if a user click a
     * "Cancel" button, it will return a "*", this variable will return to the
     * main menu
     *
     */
    public static String checkString(String msg) {
        String variable;
        variable = JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.QUESTION_MESSAGE);
        try {
            if (msg.equalsIgnoreCase("Please enter course")) {
                variable.matches("[a-zA-Z_]+");
            } else {
                variable.matches("^[ A-Za-z]+$");
            }
        } catch (NullPointerException e) {
            return "*";
        }
        if (msg.equalsIgnoreCase("Please enter course")) {
            while (!variable.matches("[a-zA-Z_]+")) {
                variable = JOptionPane.showInputDialog(null,
                        msg, "input", JOptionPane.QUESTION_MESSAGE);
                try {
                    if (msg.equalsIgnoreCase("Please enter course")) {
                        variable.matches("[a-zA-Z_]+");
                    } else {
                        variable.matches("^[ A-Za-z]+$");
                    }
                } catch (NullPointerException e) {
                    return "*";
                }
            }
        } else {
            while (!variable.matches("^[ A-Za-z]+$")) {
                variable = JOptionPane.showInputDialog(null,
                        msg, "input", JOptionPane.QUESTION_MESSAGE);
                try {
                    if (msg.equalsIgnoreCase("Please enter course")) {
                        variable.matches("[a-zA-Z_]+");
                    } else {
                        variable.matches("^[ A-Za-z]+$");
                    }
                } catch (NullPointerException e) {
                    return "*";
                }
            }
        }
        if (msg.equalsIgnoreCase("Please enter student's course:") || msg.equals("Please enter course name:")) {
            variable = variable.toUpperCase();
        }
        return variable;
    }
}
