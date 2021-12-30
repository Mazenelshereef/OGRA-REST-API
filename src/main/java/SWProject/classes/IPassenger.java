package SWProject.classes;

public interface IPassenger extends ISuspendableUser {
    public void setPersonalInfo(UserInfo personalInfo);
    public UserInfo getPersonalInfo();
    public double getBalance();
    public void addBalance(double amount);
    public boolean takeBalance(double amount);
    public double checkDriverRating(IDriver driver);
    public void acceptOffer(IOffer offer) throws Exception;
    public void denyOffer(IOffer offer);
    public String checkOffers();
    public void rateDriver(IDriver driver, int ratingValue) throws Exception;
    public void requestRide(String s, String d, int noOfPassengers);
    public void recieveNotification(String notification);
    public String getNotification(int index);
    public void removeNotification(int index);
    public String viewNotifications();
}
