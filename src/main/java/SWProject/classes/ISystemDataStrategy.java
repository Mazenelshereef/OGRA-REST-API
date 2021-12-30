package SWProject.classes;

import java.util.ArrayList;

public interface ISystemDataStrategy {
    public boolean displayAllRegistrations();
    public boolean addRegistrationRequest(IRegistrationRequest registrationRequest);
    public boolean addDriver(IDriver driver);
    public boolean addPassenger(IPassenger passenger);
    public boolean addOffer(IOffer offer);
    public boolean addRide(IRide ride);
    public boolean addRating(IRating rating);
    public boolean addDiscountArea(String area);
    public IRegistrationRequest getRegistrationRequest(int index);
    public IRegistrationRequest getRegistrationRequest(String username);
    public IDriver getDriver(String username);
    public IPassenger getPassenger(String username);
    public IAdmin getAdmin(String username);
    public ArrayList<IDriver> getDriversWithFavouriteArea(String area);
    public ArrayList<IOffer> getOffersOfPassenger(IPassenger passenger);
    public ArrayList<IOffer> getOffersOfDriver(IDriver driver);
    public ArrayList<IRating> gerRatingsOfDriver(IDriver driver);
    public ArrayList<IRide> getRidesOfDriver(IDriver driver);
    public boolean removeRegisrationRequest(IRegistrationRequest registrationRequest);
    public boolean removeDiscountArea(String area);
    public boolean containsRideOfPassenger(IPassenger passenger);
    public boolean containsDiscountArea(String destination);
}
