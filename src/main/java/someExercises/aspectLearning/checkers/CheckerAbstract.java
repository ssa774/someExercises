package someExercises.aspectLearning.checkers;

public abstract class CheckerAbstract {
    CheckAble nextCheck;

    public CheckerAbstract(CheckAble nextCheck) {
        this.nextCheck = nextCheck;
    }

    public CheckAble getNextCheck() {
        return nextCheck;
    }
}
