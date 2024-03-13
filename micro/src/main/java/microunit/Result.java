package microunit;

public class Result {
    private int numberOfSucces;
    private int numberOfFailures;
    private int numberOfErrors;

    public void onError(){
        numberOfErrors++;
    }

    public void onFailures(){
        numberOfFailures++;
    }

    public void onSucces(){
        numberOfSucces++;
    }

    @Override
    public String toString() {
        return "Result{" +
                "numberOfSucces=" + numberOfSucces +
                ", numberOfFailures=" + numberOfFailures +
                ", numberOfErrors=" + numberOfErrors +
                '}';
    }
}
