package SWProject.classes;

public interface IOffer {
    public IDriver getItsDriver() ;
    public void setItsDriver(IDriver itsDriver) ;
    public IRideRequest getItsRideRequest();
    public void setItsRideRequest(IRideRequest rideRequest);
    public double getPrice() ;
    public void setPrice(double price) ;
    public boolean isAccepted() ;
    public void setAccepted(boolean isAccepted);
}
