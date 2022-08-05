package SWProject.Model.booking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import SWProject.Model.users.passengers.IPassenger;



public class RideRequest implements IRideRequest {
    private int id;
    private String source;
    private String destination;
    private int noOfPassengers;
    private IPassenger itsPassenger;
    private IOffer acceptedOffer;
    private double price;
    private ArrayList<String> events;

    private static int count = 0;

    public RideRequest(String source, String destination, int noOfPassengers, IPassenger itsPassenger) {
        this.id = count++;
        this.source = source;
        this.destination = destination;
        this.noOfPassengers = noOfPassengers;
        this.itsPassenger = itsPassenger;
        this.acceptedOffer = null;
        this.price = -1;
        events = new ArrayList<>();
    }

    //for the discount classes
    protected RideRequest(String source, String destination, int noOfPassengers, IPassenger itsPassenger, int id, ArrayList<String> events) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.noOfPassengers = noOfPassengers;
        this.itsPassenger = itsPassenger;
        this.acceptedOffer = null;
        this.price = -1;
        this.events = events;
    }

    public ArrayList<String> getEvents(){
        return events;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setSource(String source) {
        this.source = source;        
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    @Override
    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    @Override
    public void setItsPassenger(IPassenger itsPassenger) {
        this.itsPassenger = itsPassenger;        
    }

    @Override
    public IPassenger getItsPassenger() {
        return itsPassenger;
    }

    @Override
    public void setAcceptedOffer(IOffer offer) {
        acceptedOffer = offer;        
    }

    @Override
    public IOffer getAcceptedOffer() {
        return acceptedOffer;
    }
    
    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getCost(double price) {
        return price;
    }

    @Override
    public void addEvent(String eventName , String eventParticipants){
        SimpleDateFormat formatTime  = new SimpleDateFormat("hh.mm aa");
        events.add(eventName + " , " +  formatTime.format(new Date()) + " , " + eventParticipants) ;
    }

    @Override
    public String toString() {
        return "Ride: {" +
                "id='" + id + '\'' +
                "source='" + source + '\'' +
                ", distenation='" + destination + '\'' +
                ", noOfPassengers='" + noOfPassengers + '\'' +
                ", itsPassenger=" + itsPassenger.getPersonalInfo().getUsername() +
                '}';
    }

    @Override
    public String showEvents() {
        String output = "";
        for (String event : events) {
            output += event + '\n';
        }
        return output;
    }
    
}
