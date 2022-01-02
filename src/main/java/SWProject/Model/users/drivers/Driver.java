package SWProject.Model.users.drivers;

import java.util.ArrayList;
import SWProject.Model.booking.*;
import SWProject.Model.users.UserInfo;
import SWProject.Model.storage.SystemData;

public class Driver implements IDriver {

    private UserInfo personalInfo ;
    private ArrayList<String> favoriteAreas;
    private double averageRating;
    private ArrayList<String> notifications;
    private double balance;
    private IRide currentRide;

    public Driver(DriverInfo personalInfo) {
        this.personalInfo = personalInfo;
        favoriteAreas = new ArrayList<>();
        averageRating = 0;
        notifications = new ArrayList<>();
        balance = 0;
        currentRide = null ;
    }

    @Override
    public void setSuspended(boolean isSuspended) {
        personalInfo.setSuspended(isSuspended);
    }

    @Override
    public void setCurrentRide(IRide currentRide) {
        this.currentRide = currentRide;
    }

    @Override
    public IRide getCurrentRide() {
        return currentRide;
    }

    @Override
    public ArrayList<IOffer> getMyOffers() {
        return SystemData.getInstance().getOffersOfDriver(this);
    }

    @Override
    public ArrayList<IRating> getMyRatings() {
        return SystemData.getInstance().gerRatingsOfDriver(this);
    }

    @Override
    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public UserInfo getPersonalInfo() {
        return personalInfo;
    }

    @Override
    public void setPersonalInfo(UserInfo info) {
        this.personalInfo = (DriverInfo) info ;
    }

    @Override
    public boolean hasFavouriteArea(String area) {
        for(int i = 0 ; i < favoriteAreas.size() ; ++i){
            if( area.equals(favoriteAreas.get(i))){
                return true ;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Driver [personalInfo=" + personalInfo + ", averageRating=" + averageRating
                + ", favoriteAreas=" + favoriteAreas + "]";
    }

    @Override
    public void addFavoriteArea(String name) {
        favoriteAreas.add(name) ;        
    }

    @Override
    public String listRidesInFavouriteAreas() {
        String output = "";
        ArrayList<IRideRequest> favoriteAreaRides = getFavouriteAreaRides();  
        for(int i = 0 ; i < favoriteAreaRides.size() ; ++i){
            output += favoriteAreaRides.get(i).toString() + '\n';
        }
        return output;
    }

    @Override
    public void suggestPrice(IRideRequest rideRequest, double price) {
        BookingFacade.getInstance().makeOffer(new Offer(price, this, rideRequest));
    }

    @Override
    public String listPassengersRatings() {
        String output = "";
        ArrayList<IRating> myRatings = getMyRatings(); 
        for(int i = 0 ; i < myRatings.size() ; ++i){
            output += myRatings.get(i).toString() + '\n';
        }
        return output;
    }

    @Override
    public String viewMyOffers() {
        String output = "";
        ArrayList<IOffer> myOffers = getMyOffers();  
        for(int i = 0 ; i < myOffers.size() ; ++i){
            output += myOffers.get(i).toString() + '\n';
        }
        return output;
    }

    @Override
    public ArrayList<IRideRequest> getFavouriteAreaRides() {
        return SystemData.getInstance().getRidesOfDriverFavouriteAreas(this);
    }

    @Override
    public void recieveNotification(String notification) {
        notifications.add(notification);
    }

    @Override
    public String getNotification(int index) {
        return notifications.get(index);
    }

    @Override
    public boolean removeNotification(int index) {
        try{
            notifications.remove(index);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public ArrayList<String> getFavouriteAreas() {
        return new ArrayList<String>(favoriteAreas);
    }

    @Override
    public String viewNotifications() {
        String output = "";     
        for(int i = 0 ; i < notifications.size() ; i++){
            output += (i+1) + ": " + notifications.get(i) + '\n';
        }
        return output;
    }

    @Override
    public void updateAverageRating() {
        this.averageRating = 0;
        ArrayList<IRating> myRatings = getMyRatings();
        for(int i = 0 ; i < myRatings.size() ; ++i){
            this.averageRating += myRatings.get(i).getValue() ;
        }       
        this.averageRating /= myRatings.size();        
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void addBalance(double amount) {
        balance += amount;        
    }

    @Override
    public boolean isAvailable() {
        if (currentRide == null)
            return true;
        return !currentRide.isFull() && !currentRide.hasStarted();
    }

    @Override
    public void reachUserLocation() throws Exception{
        if (currentRide == null)
           throw new Exception("Error: You don't have a ride to start.");
        //set the ride as started
        currentRide.start();
    }

    @Override
    public void reachUserDestination() throws Exception{
        if (currentRide == null)
            throw new Exception("Error: You don't have a ride to start.");
        if (!currentRide.hasStarted())
            throw new Exception("Error: this ride hasn't started yet!");
        //set the ride as finished
        currentRide.finish();
        currentRide = null ;
    }

}
