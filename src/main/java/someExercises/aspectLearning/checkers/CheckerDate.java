package someExercises.aspectLearning.checkers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("firstCheck")
public class CheckerDate extends CheckerAbstract implements CheckAble {

    public CheckerDate(@Qualifier("checkerName") CheckAble nextCheck) {
        super(nextCheck);
    }


    @Override

    public List<String> doCheck(List<String> lstString) {
        System.out.println("CheckerDate.doCheck");

        if (this.getNextCheck() != null)
            return this.getNextCheck().doCheck(lstString);
        return lstString;
    }
}
