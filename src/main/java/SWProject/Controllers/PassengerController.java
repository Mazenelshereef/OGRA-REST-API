package SWProject.Controllers;

import SWProject.classes.IPassenger;
import SWProject.classes.Passenger;
import SWProject.classes.IDriver;
import SWProject.classes.PassengerAuthenticator;
import SWProject.classes.PassengerInfo;
import SWProject.classes.SystemData;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {
    Passenger passenger;
    PassengerInfo info = new PassengerInfo();
    
    @PostMapping("/passenger/Login")
    public boolean Register(@RequestAttribute String username, @RequestAttribute String password, @RequestAttribute String mail, @RequestAttribute String mobile) throws Exception{
        info.setUsername(username);
        if (!mail.equals("0"))
        	info.setEmail(mail);
        info.setPassword(password);
        info.setMobileNumber(mobile);
         return PassengerAuthenticator.getInstance().register(info);
    }

    @GetMapping("/passenger/Login")
        public void Login(@RequestAttribute String username, @RequestAttribute String password) throws Exception{
             passenger = (Passenger)PassengerAuthenticator.getInstance().login(username, password);
        }

        
    @PostMapping("/passenger/requestRide/{s}/{d}/{noOfPassengers}")
    public void requestRide(@PathVariable String source, @PathVariable String destination, @PathVariable int noOfPassengers){
        passenger.requestRide(source, destination, noOfPassengers);
    }

    @GetMapping("/passenger/checkOffers")
    public String checkOffers(){
        return passenger.checkOffers();
    }

    @GetMapping("/passenger/checkDriverRating/{offerNumber}")  
    public double checkDriverRating(@PathVariable int offerNumber){
    return passenger.checkDriverRating(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver());

    }

    @PutMapping("/passenger/acceptOffer/{offerNumber}")  
    public void acceptOffer(@PathVariable int offerNumber) throws Exception{
        passenger.acceptOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));

    }

    @PutMapping("/passenger/denyOffer/{offerNumber}")  
    public void denyOffer(@PathVariable int offerNumber){
        passenger.denyOffer(SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1));

    }

    @PutMapping("/passenger/rateDriver/{offerNumber}/{ratingValue}")  
    public void rateDriver(@PathVariable int offerNumber, @PathVariable int ratingValue) throws Exception{
        IDriver driverOfOffer = SystemData.getInstance().getOffersOfPassenger(passenger).get(offerNumber-1).getItsDriver();
        passenger.rateDriver(driverOfOffer, ratingValue);

    }

   



    
}
