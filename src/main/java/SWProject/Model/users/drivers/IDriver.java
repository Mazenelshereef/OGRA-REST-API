package SWProject.Model.users.drivers;

import java.util.ArrayList;
import SWProject.Model.users.ISuspendableUser;
import SWProject.Model.users.UserInfo;
import SWProject.Model.booking.*;

public interface IDriver extends ISuspendableUser {
    public UserInfo getPersonalInfo();
    public boolean hasFavouriteArea(String area);
    public void setPersonalInfo(UserInfo info);
    public void setCurrentRide(IRide currentRide);
    public IRide getCurrentRide();
    public void addFavoriteArea(String name);
    public String listRidesInFavouriteAreas();
    public void suggestPrice(IRideRequest rideRequest, double price);
    public String listPassengersRatings();
    public String viewMyOffers();
    public double getAverageRating();
    public void setAverageRating(double averageRating);
    public ArrayList<IRating> getMyRatings();
    public ArrayList<IOffer> getMyOffers();
    public ArrayList<IRideRequest> getFavouriteAreaRides();
    public ArrayList<String> getFavouriteAreas();
    public void recieveNotification(String notification);
    public String getNotification(int index);
    public boolean removeNotification(int index);
    public String viewNotifications();
    public void updateAverageRating();
    public double getBalance();
    public void addBalance(double amount);
    public boolean isAvailable();
    public void reachUserLocation() throws Exception;
    public void reachUserDestination() throws Exception;
}
