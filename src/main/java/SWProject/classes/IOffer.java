package SWProject.classes;

public interface IOffer {
    public IDriver getItsDriver() ;
    public void setItsDriver(IDriver itsDriver) ;
    public IRide getItsRide();
    public void setItsRide(IRide ride);
    public double getPrice() ;
    public void setPrice(double price) ;
    public boolean isAccepted() ;
    public void setAccepted(boolean isAccepted);
}
