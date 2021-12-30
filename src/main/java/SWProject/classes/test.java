package SWProject.classes;


import java.util.Calendar;
public class test {
    public static void main(String[] args) {
        /*IRide ride = new Ride("f","f",2,new Passenger(new PassengerInfo()));
        ride = new FirstRideDiscount(ride);
        ride = new BirthdayDiscount(ride);
        ride = new HolidayDiscount(ride);
        ride.setPrice(50);
        System.out.println(ride.getCost(ride.getPrice()));
        System.out.println(ride.getDestination());*/
        //System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH));
        Passenger p = new Passenger(new PassengerInfo());
        Ride r = new Ride("sou", "dis", 2, p);
        r.addEvent("eventName", "driver:d1" + " user:u1");
        //System.out.println(r.getEvent());
        r = new FirstRideDiscount(r);
        Driver d = new Driver(new DriverInfo());
        d.suggestPrice(r, 20);
        p.checkOffers();
    }
}
