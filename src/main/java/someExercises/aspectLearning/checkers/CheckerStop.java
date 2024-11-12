package someExercises.aspectLearning.checkers;

import org.springframework.stereotype.Component;



import java.util.List;

@Component
public class CheckerStop implements CheckAble {

    @Override
    public List<String> doCheck(List<String> lstString) {
        System.out.println("CheckerStop.doCheck");
        return lstString;
    }
}
