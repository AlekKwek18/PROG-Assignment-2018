
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
import javax.swing.JOptionPane;

public class DMITStudentUser {

    //Main method to activiate the JOptionPane
    public static void main(String[] args) {
        DMITStudents.initaliseArray();
        String strOption;
        int Option = 0;
        /**
         * User inputs the number in strOption
         * strOption is then converted into Option
         * Where Option will be used for switch option for option
         * there is a do-while for validation
         * if strOption is equal to "", which is no input, it will loop until there is an input
         * if a user enters a letter, the try catch statement will catch the NumberFormatException and make strOption = 0;
         * This value in the switch case will tell the user that they must enter a number instead of a letter
         */
        do {
            strOption = JOptionPane.showInputDialog(null, "Enter your option: "
                    + "\n1. Display Students"
                    + "\n2. Search Student"
                    + "\n3. Delete Student"
                    + "\n4. Add New Student"
                    + "\n5. Edit Student Info"
                    + "\n6. Display Students in table form"
                    + "\n7. Exit",
                    "DMIT Students", JOptionPane.INFORMATION_MESSAGE);

            if (strOption == null) {
                return;
            }
            try {
                Option = Integer.parseInt(strOption);
            } catch (NumberFormatException e) {
                DMITStudents.errorSound();
                strOption = "0";
            }
            Option = Integer.parseInt(strOption);

            switch (Option) {
                case 1:
                    DMITStudents.viewStudents();
                    break;
                case 2:
                    DMITStudents.searchStudent();
                    break;
                case 3:
                    DMITStudents.deleteStudents();
                    break;
                case 4:
                    DMITStudents.addStudent();
                    break;
                case 5:
                    DMITStudents.editInfo();
                    break;
                case 6:
                    DMITStudents.displayStudentTable();
                    break;
                case 7:
                    DMITStudents.programTerminated();
                    return;
                default:
                    DMITStudents.invalidOption();
                    break;
            }
        } while (strOption != "");
    }
}
