package SWProject.Model.booking;

import SWProject.Model.users.drivers.IDriver;

public interface IOffer {
    public int getId();
    public IDriver getItsDriver() ;
    public void setItsDriver(IDriver itsDriver) ;
    public IRideRequest getItsRideRequest();
    public void setItsRideRequest(IRideRequest rideRequest);
    public double getPrice() ;
    public void setPrice(double price) ;
    public boolean isAccepted() ;
    public void accept();
    public boolean isDenied();
    public void deny();
}
