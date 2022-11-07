package Users;
import java.sql.Array;
import java.util.*;

public class Student {
    String student_id;
    private String student_password;
    String student_info;
    ArrayList<Tabs> tabs_of_interests;
    //ArrayList<Course> enrolled_courses;

    //init
    public Student(String SID, String sPass, String info){
        this.student_id = SID;
        this.student_password = sPass;
        this.student_info = info;
        this.tabs_of_interests = new ArrayList<>();
    }

    /*
    GetStudentID: returns student id
    param: none
    return: String
     */
    public String GetStudentID(){
        return this.student_id;
    }

    /*
    GetStudentInfo: returns student info
    param: none
    return: String
     */
    public String GetStudentInfo(){
        return this.student_info;
    }

    /*
   GetStudentPassword: Get student password
   param: none
   return: String
    */
    private String GetStudentPassword(){
       return this.student_password;
    }

    /*
    SetStudentInfo: sets new student info
    param: String
    return: none
     */
    public void SetStudentInfo(String info){
        this.student_info = info;
    }

    /*
    SetStudentID: sets new student ID
    param: String
    return: none
     */
    public void SetStudentID(String ID){
        this.student_id = ID;
    }

    /*
    SetStudentPassword: sets new student password
    param: String
    return: none
     */
    private void SetStudentPassword(String pass){
        this.student_password = pass;
    }


    /*
    UpdateStudentTOI: updates the student's tabs of interest given the index of the tab required for change
    param: index
    return: none
     */
    private void UpdateStudentTOI(int index, Boolean value){
        this.tabs_of_interests.get(index).value = value;
    }

}
