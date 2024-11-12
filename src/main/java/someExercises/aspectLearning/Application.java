package someExercises.aspectLearning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import someExercises.aspectLearning.checkers.CheckAble;

import java.util.Arrays;
import java.util.List;

@Component
@EnableAspectJAutoProxy
public class Application {
    CheckAble firstCheck;
    @Autowired
    @Qualifier("firstCheck")
    public void setFirstCheck(CheckAble firstCheck) {
        this.firstCheck = firstCheck;
    }
    public void run(){
        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9");
        firstCheck.doCheck(list);
    }
}
