package SWProject.Model.booking;

import SWProject.Model.users.drivers.IDriver;
import SWProject.Model.users.passengers.IPassenger;

public interface IRating {
    public int getId();
    public int getValue() ;
    public IPassenger getItsPassenger();
    public IDriver getItsDriver();
    public void setItsPassenger(IPassenger itsPassenger);
    public void setValue(int value);
    public void setItsDriver(IDriver driver);
}
