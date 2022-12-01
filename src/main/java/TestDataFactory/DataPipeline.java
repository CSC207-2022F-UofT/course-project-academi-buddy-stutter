package TestDataFactory;

import Entities.Course;
import Entities.InterestTag;
import Entities.Label;
import Entities.Student;
import External.FirebaseAPI;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPipeline {
    public static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }
    public static void main(String[] args) throws IOException {
        //essential for database classes

        initializeFirebase();
        FirebaseAPI cbc = new FirebaseAPI();
        FirebaseAPI ubc = new FirebaseAPI();
        FirebaseAPI tbc = new FirebaseAPI();
        UserDataManager ub = new UserDataManager(ubc);
        CourseDataManager cb = new CourseDataManager(cbc, ub);
        TagDataManager tb = new TagDataManager(tbc, ub);

        String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};
        String[] labelType = {"Want to Meet", "Want to Collaborate", "Want to Discuss"};
        ArrayList<InterestTag> tagLibrary = new ArrayList<>();
        ArrayList<Label> labelLibrary = new ArrayList<>();
        for(String s: tagType){
            InterestTag tag = new InterestTag(s);
            tagLibrary.add(tag);
        }
        for(String s: labelType){
            Label label = new Label(s);
            labelLibrary.add(label);
        }


        UserDataFactory userDataFactory = new UserDataFactory(cb, ub, tb);
        CourseDataFactory courseDataFactory = new CourseDataFactory(cb, ub, tb);

        List<InterestTag> t1 = new ArrayList<>();
        t1.add(tagLibrary.get(0));
        t1.add(tagLibrary.get(5));
        t1.add(tagLibrary.get(6));
        List<InterestTag> t2 = new ArrayList<>();
        t2.add(tagLibrary.get(2));
        t2.add(tagLibrary.get(3));
        t2.add(tagLibrary.get(6));
        List<InterestTag> t3 = new ArrayList<>();
        t3.add(tagLibrary.get(7));
        List<InterestTag> t4 = new ArrayList<>();
        t3.add(tagLibrary.get(4));
        List<InterestTag> t5 = new ArrayList<>();

        List<Label> l1 = new ArrayList<>();
        l1.add(labelLibrary.get(0));
        l1.add(labelLibrary.get(1));
        l1.add(labelLibrary.get(2));
        List<Label> l2 = new ArrayList<>();
        l2.add(labelLibrary.get(0));
        l2.add(labelLibrary.get(2));
        List<Label> l3 = new ArrayList<>();
        l3.add(labelLibrary.get(1));
        l3.add(labelLibrary.get(2));
        List<Label> l4 = new ArrayList<>();
        l4.add(labelLibrary.get(2));
        List<Label> l5 = new ArrayList<>();

        // Student s1 = userDataFactory.createStudent("567789", "qwerty", "John Doeeeee", "Random", "rtyui@l.com", t1, l1);
        Course c1 = courseDataFactory.createCourse("CSC800H1", "LEC", "0101", "Computer Algorithm", "Tue", "1500", "2022");
        System.out.println(c1.getEnrolledIDList());
        // courseDataFactory.addStudent(c1, s1);

        // sample student objects
        Student s1 = userDataFactory.createStudent("63267",	"qwerty",	"Ash Copper",	"Meet",	"ac63@gmail.com",	t1,	l1);
        Student s2 = userDataFactory.createStudent("72053",	"asdfgh",	"Sin Piper",	"Meet",	"sp72@mail.com",	t5,	l2);
        Student s3 = userDataFactory.createStudent("34592",	"zxcvbn",	"Ur Retared",	"Meet",	"ur48@sg.com",	t2,	l3);
        Student s4 = userDataFactory.createStudent("60383",	"poiuyt",	"Im SB",	"Meet",	"abc@mail.com",	t3,	l4);
        Student s5 = userDataFactory.createStudent("22779",	"lkjnmh",	"Iphone Charger",	"Collborate",	"70as@gmail.com",	t1,	l4);
        Student s6 = userDataFactory.createStudent("70078",	"ryfhvb",	"Water Bottle",	"Collborate",	"125ed@mail.com",	t5,	l2);
        Student s7 = userDataFactory.createStudent("13533",	"wsxchi",	"Computer case",	"Collborate",	"cc123@gmail.com",	t2,	l2);
        Student s8 = userDataFactory.createStudent("12611",	"pwmfns",	"Cell Phone",	"Discuss",	"998su@gmail.com",	t4,	l1);
        Student s9 = userDataFactory.createStudent("88927",	"xcvjsn",	"bubble tea",	"Discuss",	"asdfwwww@gmail.com",	t1,	l1);
        Student s10 = userDataFactory.createStudent("59407",	"asokgt",	"air pods",	"Discuss",	"asdf2ss2sdfd@mail.com",	t2,	l4);
        System.out.println("Read: " + ReadCounter.getCount());
        System.out.println("Write: " + WriteCounter.getCount());
    }
}
