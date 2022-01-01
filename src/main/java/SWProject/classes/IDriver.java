package SWProject.classes;

import java.util.ArrayList;

public interface IDriver extends ISuspendableUser {
    public UserInfo getPersonalInfo();
    public boolean hasFavouriteArea(String area);
    public void setPersonalInfo(UserInfo info);
    public void setCurrentRide(IRide currentRide);
    public IRide getCurrentRide();
    public void addFavoriteArea(String name) ;
    public String listRidesInFavouriteAreas();
    public void suggestPrice(IRide ride , double price);
    public String listPassengersRatings(); 
    public String viewMyOffers() ;
    public double getAverageRating() ;
    public void setAverageRating(double averageRating) ;
    public ArrayList<IRating> getMyRatings() ;
    public ArrayList<IOffer> getMyOffers() ;
    public ArrayList<IRide> getFavouriteAreaRides();
    public ArrayList<String> getFavouriteAreas();
    public void recieveNotification(String notification);
    public String getNotification(int index);
    public void removeNotification(int index);
    public String viewNotifications();
    public void updateAverageRating();
    public double getBalance();
    public void addBalance(double amount);
    public void reachUserLocation(IRide ride);
    public void reachUserDistination(IRide ride);
}
