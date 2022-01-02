package SWProject;

import SWProject.Model.storage.SystemData;
import SWProject.Model.users.drivers.Driver;
import SWProject.Model.users.drivers.DriverInfo;
import SWProject.Model.users.passengers.Passenger;
import SWProject.Model.users.passengers.PassengerInfo;

public class test {
    public static void main(String[] args) {
        Driver d = new Driver(new DriverInfo("d1", "123", "sss", "0", 1, 1, 2001, "00", "55"));
        Passenger p = new Passenger(new PassengerInfo("p1", "123", "s", "12", 2, 5, 3101));
        p.requestRide("source", "destination", 1);
        d.addFavoriteArea("source");
        d.suggestPrice(SystemData.getInstance().getRideRequestByID(1), 50);
        p.addBalance(100);
        try {
            p.acceptOffer(SystemData.getInstance().getOfferByID(0));
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        d.reachUserLocation();
    }
}
