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
public class DMITStudents{
    //Array index will be set to 3 as location 0 to 2 has been initized.
    private static int MainIndex = 3;
    //Set and initialize array of Student class name,course,gender and contact respectively with a size of 4
    private static Student[] student = new Student [MainIndex+1];
    //Set and initalize array of Temp class name,course,gender and contact respectively with a size of 5
    private static Student[] Temp = new Student [MainIndex+2];
    // A method that will play a sound in java
    /**
     * InputStream data type called music is created
     * 
     */
    public static void playMusic(String x){
        try{
        InputStream music;
        music = new FileInputStream(new File(x));
        AudioStream audios = new AudioStream(music);
        AudioPlayer.player.start(audios);
        } catch (Exception e){
            
        }
    }
    // A method that will play the Windows 10 Error sound
    public static void errorSound(){
        playMusic("Windows 10 Error Sound.wav");
    }
    // Display student but in a table form
    public static void displayStudentTable(){
  
        Object[][] displayStudent = new Object[MainIndex][5];
        int a = 0;
        String[] Studentlabel = {"S/N","Name","Gender","Course","Contact"};
        for(int i = 0;i<MainIndex;i++){
            a = 0;
        while(a < 5){
            displayStudent [i][a] = i+1;
            a++;
            displayStudent [i][a] = student[i].getName();
            a++;
            displayStudent [i][a] = student[i].getGender();
            a++;
            displayStudent [i][a] = student[i].getCourse();
            a++;
            displayStudent [i][a] = student[i].getMobile();
            a++;
        }
                }
        JTable displayStudents = new JTable(displayStudent,Studentlabel) ;
        displayStudents.setAutoCreateRowSorter(true);
        JOptionPane.showMessageDialog(null,new JScrollPane(displayStudents),"DMIT",JOptionPane.INFORMATION_MESSAGE);

                    }
    // A method that will shift the content of an old array to a new temp array eith locations one unit higher than the old array. The old array will then have 2 extra locations added
    public static void changingArray(){
        int index = MainIndex; // get current index 
         index++;
         //initial length of the array
        if(MainIndex + 2 == 6 ){
            //Do nothing
        }else {
        Temp = new Student[index];
        }
        for(int i = 0;i < MainIndex;i++){
            Temp[i] = new Student (student[i].getName(),student[i].getCourse(),student[i].getMobile(),student[i].getGender(),"null");
        }
                index++;
        student = new Student[index];
        for(int i = 0;i < MainIndex;i++ ){
            student[i] = new Student (Temp[i].getName(),Temp[i].getCourse(),Temp[i].getMobile(),Temp[i].getGender(),"null");
        }
    }
    // A method that will end the process 
    public static void programTerminated(){
       JOptionPane.showMessageDialog(null,
               "Program terminated."
                       + "\nThank You!");
          System.exit(6);
   }
    // Method that will allow user to edit Student's info
    public static void editInfo(){
        
        String edit = "";
        int flag = 0;
        String name = "";
        int option;
        String stroption;
        int index = 0;
        Date date= new Date();
        boolean edited = false;
        while(!name.matches("^[ A-Za-z]+$")){
        name = JOptionPane.showInputDialog(null,
                "Please enter student's name to edit",
                "DMIT Student",JOptionPane.QUESTION_MESSAGE);     
                if(name == null){
               return;
}
        }

        do{ 
            for(index = 0;index < MainIndex;index++){
            if (name.equals(student[index].getName())){
            Student editStudent = new Student();
            do{
                
            stroption = JOptionPane.showInputDialog(null,
                  "Name      :"+student[index].getName()+"\n"+
                  "Gender    :"+student[index].getGender()+"\n"+
                  "Course    :"+student[index].getCourse()+"\n"+
                  "Mobile     :"+student[index].getMobile()+ "\n" +
                  "Which componemnt would you like to amend? \n" +
                  "1) Name \n"+
                  "2) Gender \n"+
                  "3) Course \n"+
                  "4) Mobile \n"+
                  "5) Exit \n"+
                  "Last edited:" + student[index].getTime(),       
                  "DMIT Students",JOptionPane.INFORMATION_MESSAGE);
            try{
            option = Integer.parseInt(stroption);  
            }catch(NumberFormatException e){
            stroption = "0";}
            option = Integer.parseInt(stroption);
            String editName;
            String editCourse;
            int editNumber;
            char editGender;
            String msg;
            if(option == 1){
                msg = "Please type student's new name:";
                editName = checkString(msg);
                
                    if (editName.equals("*") || checkDuplicate(editName)==true){
            return;
            
                    }
                    student[index] = new Student (editName,student[index].getCourse(),student[index].getMobile(),student[index].getGender(),date.toString());
                
                edited = true;
                
            }else if(option == 2){
                msg = "Please type new gender:";
                editGender = checkChar(msg);
                if(editGender == '*'){
                    return;
                }
                     student[index] = new Student (student[index].getName(),student[index].getCourse(),student[index].getMobile(),editGender,date.toString());
                                edited = true;

                }else if(option == 3){
                msg = "Please enter course name:";    
                editCourse = checkString(msg);
                if(editCourse == "*"){
                    return;
                }
                     student[index] = new Student (student[index].getName(),editCourse,student[index].getMobile(),student[index].getGender(),date.toString());
                edited = true;
            }else if(option == 4){
                msg = "Please enter your new number:";
                editNumber = checkNum(msg);
                if(editNumber == 0){
                    return;
                }
                     student[index] = new Student (student[index].getName(),student[index].getCourse(),editNumber,student[index].getGender(),date.toString());
                edited = true;
            }else if(option == 5){
                return;
            }
            }while(stroption != "");
                  return;
        }else{    
                      if(index == MainIndex-1){
                     JOptionPane.showMessageDialog(null,
                   "Cannot find info of "+ name,"DMIT Students",
                   JOptionPane.INFORMATION_MESSAGE);
                   return;
                   }
                      flag = 1;
              }
            }
        }while(flag == 1);
    }
    // Method that will check for duplicate names
    public static boolean checkDuplicate(String name){
        int flag =0;
        //int index = 0;
        do{
            for(int index = 0;index < MainIndex;index++){
            if (name.equals(student[index].getName())){
          JOptionPane.showMessageDialog(null,
                  "This name has already been added","DMIT",JOptionPane.INFORMATION_MESSAGE);
                  return true;
        }else{    
                      if(index == MainIndex-1){
                   return false;
                   }
                      flag = 1;
              }
            }
        }while(flag == 1);
        return false;
    }
    // A method that sets up the three inital students' information
    public static void initaliseArray(){
        //Data
        student[0] = new Student("Grace Teo","DIT",91111111,'F',"null");
        student[1] = new Student("Kenny Tan","DIT",92222222,'M',"null");
        student[2] = new Student("Peter Low","DIT",93333333,'M',"null");
    }
    // A Method that allows users to view Students' information
    public static void viewStudents(){
        for(int i = MainIndex;i < student.length;i++){
            student[i] = new Student("null","null",0,'0',"null");
        }
        String[] output = new String[MainIndex+1];
        output[0] = " S/N  Name       Gender   Course     Contact";
        for(int i =1;i <= MainIndex;i++){
             if(student[i].getName() != null){
             //int y = i +1;
             String Temp = i+" "+student[i-1].getName() + "     "+
                     student[i-1].getCourse()+"     " +
                     student[i-1].getGender()+"     " +
                     student[i-1].getMobile();
             output[i] = Temp;
             }else{
                 break;
             }
        }
                JOptionPane.showMessageDialog(null,output);


    }
    // A message that display if user type a number which is not an option in JOptionPane
    public static void invalidOption(){
       JOptionPane.showMessageDialog(null,
               "Invalid option! Please enter in the range from 1 to 7",
               "Error",JOptionPane.ERROR_MESSAGE);
  } 
    // A method that allows users to find specific student's information
    public static void searchStudent(){
        int flag = 0;
        String name;
        name = JOptionPane.showInputDialog(null,
                "Please enter student's name to search",
                "DMIT Student",JOptionPane.QUESTION_MESSAGE); 
        try{
                name.matches("^[ A-Za-z]+$");
            }catch(NullPointerException e){
                return;
            }
        while(!name.matches("^[ A-Za-z]+$")){
           
        name = JOptionPane.showInputDialog(null,
                "Please enter student's name to search",
                "DMIT Student",JOptionPane.QUESTION_MESSAGE);     
        }
        
        
        do{
            for(int index = 0;index < MainIndex;index++){
            if (name.equals(student[index].getName())){
          JOptionPane.showMessageDialog(null,
                  "Name      :"+student[index].getName()+"\n"+
                  "Gender    :"+student[index].getGender()+"\n"+
                  "Course    :"+student[index].getCourse()+"\n"+
                  "Mobile     :"+student[index].getMobile(),
                  "DMIT Students",JOptionPane.INFORMATION_MESSAGE);
                  return;
        }else{    
                      
                      if(index == MainIndex-1){
                     JOptionPane.showMessageDialog(null,
                   "Cannot find info of "+ name,"DMIT Students",
                   JOptionPane.INFORMATION_MESSAGE);
                   return;
                   }
                      flag = 1;
              }
            }
        }while(flag == 1);
    }
    // A method that adds Student's infromation - name,course,contact number and gender
    public static void addStudent(){
        //Add name
        String name;
        String msg = "Please enter student's name:";
        name = checkString(msg);
        if (name.equals("*") || checkDuplicate(name) == true){
            return;
        }
        //Add course
        msg = "Please enter student's course:";
        String course = checkString(msg);
        if (course.equals("*")){
            return;
        }
        //Add number
        msg = "Please enter student's mobile:";
        int mobile = checkNum(msg);
        if(mobile == 0){
            return;
        }
        //Add gender
        msg = "Please enter student's gender:";
        char gender = checkChar(msg);
        if(gender == '*'){
            return;
        }
        student[MainIndex] = new Student(name,course,mobile,gender,"null");
        MainIndex++;
        changingArray();
        
           JOptionPane.showMessageDialog(null,"The new student has been added successfully","DMIT Student",JOptionPane.INFORMATION_MESSAGE); 
    }
    // A method that delete a specific student's information
    public static void deleteStudents() {
        String TempName;
        String TempCourse;
        String nameDelete;
        int TempMobile;
        char TempGender;
        String TempDate;
        int flag= 0;
                nameDelete = JOptionPane.showInputDialog(null,"Please enter student's name to delete.");
                if(nameDelete == null){
               return;
}
                while(!nameDelete.matches("^[ A-Za-z]+$")){
                nameDelete = JOptionPane.showInputDialog(null,"Please enter student's name to delete.");  
                if(nameDelete == null){
               return;
}
                }
                for(int i = 0; i<MainIndex; i++){
                    if(nameDelete.equals(student[i].getName())){
                        JOptionPane.showMessageDialog(null, nameDelete + " has been deleted successfully.", "DMIT Students", JOptionPane.INFORMATION_MESSAGE);
                        for(int a = i; a<MainIndex-1; a++){
                            TempName = student[a+1].getName();
                            TempCourse = student[a+1].getCourse();
                            TempMobile = student[a+1].getMobile();
                            TempGender = student[a+1].getGender();
                            TempDate = student[a+1].getTime();
                            student[a] = new Student(TempName,TempCourse,TempMobile,TempGender,TempDate);
                            }
                            flag = 1;
                        }
                }
               if(flag == 1){
                MainIndex = MainIndex-1;
                changingArray();
               }
                if(flag != 1){
                    errorSound(); 
                    JOptionPane.showMessageDialog(null, "The student name " + nameDelete + " cannot be found!", "Error", JOptionPane.ERROR_MESSAGE);
                                   }
    }
    // A method that will validate input for char data type as well as asking the user for input
    public static char checkChar(String msg){
        String variable = "";
        variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE);
        try{
        variable.matches("[a-zA-Z_]+");
        }catch(NullPointerException e){
        return '*';
        }
        while((!variable.matches("[a-zA-Z_]+"))){
        variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE);    
        try{
        variable.matches("[a-zA-Z_]+");
        }catch(NullPointerException e){
        return '*';
        }
        }
        char checkFirstLetter = variable.charAt(0);
        if (checkFirstLetter == 'B' || checkFirstLetter == 'b'){
        variable = "M";    
        }else if(checkFirstLetter == 'G' || checkFirstLetter == 'g'){
        variable = "F";        
        }else if(checkFirstLetter == 'm' || checkFirstLetter == 'M'){
        variable = "M";    
        }else if(checkFirstLetter == 'f' || checkFirstLetter == 'F'){
        variable = "F";    
        }else{
        variable = "N";    
        }
        char charnewgender = variable.charAt(0);
        char newgender = Character.toUpperCase(charnewgender);
        return newgender;
    }
    // A method that will validate input for int data type as well as asking the user for input
    public static int checkNum(String msg){
        String variable ="";
                                        variable = JOptionPane.showInputDialog(null,msg);
       try{
           while(!variable.matches("[0-9]+") || !(variable.length() == 8) ){
            variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE); 
           } }catch(NullPointerException e){
                return 0;
                }  
       while(!variable.matches("[0-9]+") || !(variable.length() == 8) ){
            variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE); 
            try{
           while(!variable.matches("[0-9]+") || !(variable.length() == 8) ){
            variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE); 
           } }catch(NullPointerException e){
                return 0;
                }  
        }
       int integer = Integer.parseInt(variable);   
      return integer;
    }
    // A method that will validate input for String data type as well as asking the user for input
    public static String checkString (String msg){
        String variable;
        variable = JOptionPane.showInputDialog(null,msg,"Input",JOptionPane.QUESTION_MESSAGE);
        try{
            if(msg.equals("Please enter course")){
        variable.matches("[a-zA-Z_]+");
            } else{
        variable.matches("^[ A-Za-z]+$");
            }
        }catch(NullPointerException e){
        return "*";
        }
        if(msg.equals("Please enter course")){
          while(!variable.matches("[a-zA-Z_]+")){
        variable = JOptionPane.showInputDialog(null,
                msg,"input",JOptionPane.QUESTION_MESSAGE); 
        try{
            if(msg.equals("Please enter course")){
        variable.matches("[a-zA-Z_]+");
            } else{
        variable.matches("^[ A-Za-z]+$");
            }
        }catch(NullPointerException e){
        return "*";
        }
        }
        }else{
              while(!variable.matches("^[ A-Za-z]+$")){
        variable = JOptionPane.showInputDialog(null,
                msg,"input",JOptionPane.QUESTION_MESSAGE);
        try{
            if(msg.equals("Please enter course")){
        variable.matches("[a-zA-Z_]+");
            } else{
        variable.matches("^[ A-Za-z]+$");
            }
        }catch(NullPointerException e){
        return "*";
        }
        }
        }
          if(msg.equals("Please enter student's course:") || msg.equals("Please enter course name:") )
            variable = variable.toUpperCase();
        return variable;
    }
}