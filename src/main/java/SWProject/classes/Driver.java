package SWProject.classes;

import java.util.ArrayList;


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

    public ArrayList<IOffer> getMyOffers() {
        return SystemData.getInstance().getOffersOfDriver(this);
    }

    public ArrayList<IRating> getMyRatings() {
        return SystemData.getInstance().gerRatingsOfDriver(this);
    }

    public double getAverageRating() {
        return averageRating;
    }

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
        ArrayList<IRide> favoriteAreaRides = getFavouriteAreaRides();  
        for(int i = 0 ; i < favoriteAreaRides.size() ; ++i){
            output += (i+1) + ": " + favoriteAreaRides.get(i).toString();
        }
        return output;
    }

    @Override
    public void suggestPrice(IRide ride, double price) {
        SystemData.getInstance().addOffer(new Offer(price, this, ride));
    }

    @Override
    public String listPassengersRatings() {
        String output = "";
        ArrayList<IRating> myRatings = getMyRatings(); 
        for(int i = 0 ; i < myRatings.size() ; ++i){
            output += (i+1) + ": " + myRatings.get(i).toString();
        }
        return output;
    }

    @Override
    public String viewMyOffers() {
        String output = "";
        ArrayList<IOffer> myOffers = getMyOffers();  
        for(int i = 0 ; i < myOffers.size() ; ++i){
            output += (i+1) + ": " + myOffers.get(i).toString();
        }
        return output;
    }

    @Override
    public ArrayList<IRide> getFavouriteAreaRides() {
        return SystemData.getInstance().getRidesOfDriver(this);
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
    public void removeNotification(int index) {
        notifications.remove(index);
    }

    @Override
    public ArrayList<String> getFavouriteAreas() {
        return new ArrayList<String>(favoriteAreas);
    }

    @Override
    public String viewNotifications() {
        String output = "";     
        for(int i = 0 ; i < notifications.size() ; i++){
            output += (i+1) + ": " + notifications.get(i);
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

    public void reachUserLocation(IRide ride){
        ride.addEvent("Captain arrived to user location", "Driver: " + this.getPersonalInfo().getUsername() + ", Passenger: " + ride.getItsPassenger().getPersonalInfo().getUsername());
    }

    public void reachUserDistination(IRide ride){
        ride.addEvent("Captian arrived to user destination", "Driver: " + this.getPersonalInfo().getUsername() + ", Passenger: " + ride.getItsPassenger().getPersonalInfo().getUsername());
        currentRide = null ;
    }

}
