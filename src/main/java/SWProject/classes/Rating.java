package SWProject.classes;

public class Rating implements IRating {
    private IPassenger itsPassenger;
    private IDriver itsDriver;
    private int value;


    public int getValue() {
        return value;
    }
    public IPassenger getItsPassenger() {
        return itsPassenger;
    }
    public void setItsPassenger(IPassenger itsPassenger) {
        this.itsPassenger = itsPassenger;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Rating [itsPassenger=" + itsPassenger.getPersonalInfo().getUsername() + ", value=" + value + "]";
    }

    public Rating(int rate, IPassenger itsPassenger, IDriver itsDriver) {
        value = rate;
        this.itsPassenger = itsPassenger;
        this.itsDriver = itsDriver;
    }
    @Override
    public IDriver getItsDriver() {
        return itsDriver;
    }
    @Override
    public void setItsDriver(IDriver driver) {
        itsDriver = driver;
    }
}
