package someExercises.aspectLearning.checkers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.List;

@Component

public class CheckerName extends CheckerAbstract implements CheckAble {

    public CheckerName(@Qualifier("checkerApp") CheckAble nextCheck) {
        super(nextCheck);
    }

    @Override

    public List<String> doCheck(List<String> lstString) {
        System.out.println("CheckerName.doCheck");
        if (this.getNextCheck()!=null)
            return this.getNextCheck().doCheck(lstString);
        return lstString;
    }
}
