package SWProject.Model.users.passengers;

import SWProject.Model.users.UserInfo;
import SWProject.Model.users.drivers.IDriver;
import SWProject.Model.storage.SystemData;
import SWProject.Model.booking.*;
import java.util.ArrayList;

public class Passenger implements IPassenger {
    private UserInfo personalInfo;
    private ArrayList<String> notifications;
    private double balance;

    public Passenger(UserInfo personalInfo) {
        this.personalInfo = personalInfo;
        notifications = new ArrayList<>();
        balance = 0;
    }

    @Override
    public void setPersonalInfo(UserInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    @Override
    public UserInfo getPersonalInfo() {
        return personalInfo;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void addBalance(double amount) throws Exception {
        if (amount < 0)
            throw new Exception("ERROR: balance must be a non-negative value!");
        balance += amount;        
    }

    @Override
    public boolean takeBalance(double amount) {
        if (amount <= balance)
        {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void requestRide(String source, String destination, int noOfPassengers) {
        BookingFacade.getInstance().requestRide(new RideRequest(source, destination, noOfPassengers, this));
    }

    @Override
    public double checkDriverRating(IDriver driver) {
        return driver.getAverageRating();
    }

    @Override
    public void acceptOffer(IOffer offer) throws Exception {
        BookingFacade.getInstance().acceptOffer(offer);
    }

    @Override
    public void denyOffer(IOffer offer) {
        BookingFacade.getInstance().denyOffer(offer);
    }

    @Override
    public String checkOffers() {
        String output="";
        ArrayList<IOffer> offers = SystemData.getInstance().getOffersOfPassenger(this);
        for (int i = 0; i < offers.size(); i++) {
            output += offers.get(i).toString() + "\n";
        }
        return output;
    }

    @Override
    public void rateDriver(IDriver driver, int ratingValue) throws Exception {
        if (ratingValue >= 1 && ratingValue <= 5) {
            BookingFacade.getInstance().makeRating(new Rating(ratingValue, this, driver));
        } else
            throw new Exception("rating must be between 1 and 5");
    }

    @Override
    public void setSuspended(boolean isSuspended) {
        personalInfo.setSuspended(isSuspended);
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
    public String viewNotifications() {
        String output="";     
        for(int i = 0 ; i < notifications.size() ; i++){
            output += (i+1) + ": " + notifications.get(i) + "\n";
        }
        return output;
    }
}
