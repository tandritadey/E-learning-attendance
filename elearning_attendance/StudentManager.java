import java.io.*;
import java.util.*;
public class StudentManager {
    private ArrayList<Student> stud = new ArrayList<Student>();
    private StudentManager(){
        init();

    }
    public static StudentManager instance; //declaration of instance of student manager class
    public static StudentManager getInstance(){ //method to return the isntance of student manager class
        if(instance==null){
            instance = new StudentManager(); 
        }
        return instance;
    }
    public boolean init(){
        try {
            FileReader fr = new FileReader("database/student.txt");
            BufferedReader br = new BufferedReader(fr);
            java.lang.String temp = "";
            temp = br.readLine();
            while(temp!=null)
            {
                java.lang.String[] arr = temp.split(",");
                this.stud.add(new Student(arr[0],arr[1],arr[2],arr[3]));
                temp=br.readLine();
            }
            return true;
            
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }
    }

    public boolean writeInStudentFile(Student st)
    {
        try {
            FileWriter fw=new FileWriter("database/student.txt",true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(st.getName()+","+st.getEmail()+","+st.getPassword()+","+st.getUsername()+"\n");
            bw.close();
            return true;
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }
    }

    public Student searchStudent(String username){
        for(int i = 0; i<stud.size(); i++){
            Student st=stud.get(i);
            if(st.getUsername().equals(username)){
                return st;
            }
        }
        return null;
    }

    public boolean createAccount(Student obj)
    {
        this.stud.add(obj);
        return(writeInStudentFile(obj));
    }


    public static void main(String[] args) {
        StudentManager sm = StudentManager.getInstance();
        sm.createAccount(new Student("Tandrita Dey","abcd@gmail.com", "abc123","td"));
    }


}
