package someExercises.aspectLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("someExercises.aspectLearning");
        Application ap = context.getBean(Application.class);
        ap.run();
//        MyAspect myAspect = context.getBean(MyAspect.class);
//        System.out.println("myAspect " + myAspect);

    }
}
