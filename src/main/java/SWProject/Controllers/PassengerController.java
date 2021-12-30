package SWProject.Controllers;

import SWProject.classes.IPassenger;
import SWProject.classes.Passenger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerController {
    Passenger passenger;
    
    @PostMapping("/passenger/requestRide/{s}/{d}/{noOfPassengers}")
    public void requestRide(@PathVariable String s,@PathVariable String d,@PathVariable int noOfPassengers){
        passenger.requestRide(s, d, noOfPassengers);
    }

    
}
