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
public class DMITStudentUser{
    //Main method to activiate the JOptionPane
    public static void main(String[] args){     
        DMITStudents.setColor();
        DMITStudents.initaliseArray();
        String strOption;
        int Option = 0;
        do{
        strOption = JOptionPane.showInputDialog(null,"Enter your option: "
                + "\n1. Display Students"
                + "\n2. Search Student"
                + "\n3. Delete Student"
                + "\n4. Add New Student"
                + "\n5. Edit Student Info"
                + "\n6. Exit",
                "DMIT Students",JOptionPane.INFORMATION_MESSAGE);

        if (strOption == null){
          System.exit(6);
        }
        try{
        Option = Integer.parseInt(strOption);  
        }catch(NumberFormatException e){
            strOption = "0";
        }
        Option = Integer.parseInt(strOption);
        
        if(Option == 1){
            DMITStudents.viewStudents();
        }else if(Option == 2){
            DMITStudents.searchStudent();
        }else if(Option == 3){
            DMITStudents.deleteStudents();
        }else if(Option == 4){
            DMITStudents.addStudent();
        }else if(Option == 5){
            DMITStudents.editInfo();
        }else if(Option == 6){
            DMITStudents.programTerminated();
            
        }else{
            DMITStudents.invalidOption();
        }
        }
        while(strOption != "");
    }
}