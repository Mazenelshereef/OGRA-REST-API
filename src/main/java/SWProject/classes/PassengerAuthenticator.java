package SWProject.classes;

public class PassengerAuthenticator implements ILoginAuthenticator, IRegisterAuthenticator {

    private static PassengerAuthenticator instance;

    private PassengerAuthenticator(){}

    public static PassengerAuthenticator getInstance(){
        if (instance == null)
            instance = new PassengerAuthenticator();
        return instance;
    }

    @Override
    public boolean register(UserInfo userInfo) throws Exception {
        if (SystemData.getInstance().getPassenger(userInfo.getUsername()) == null)
            return SystemData.getInstance().addPassenger(new Passenger(userInfo));
        throw new Exception("Error: the username already exists, please try another one");
    }

    @Override
    public IUser login(String username, String password) throws Exception {
        IPassenger passenger = SystemData.getInstance().getPassenger(username);
        if (passenger != null){
            if (passenger.getPersonalInfo().getPassword().equals(password)){
                if (!passenger.getPersonalInfo().getSuspended())
                    return passenger;
                else
                    throw new Exception("ERROR: This Passenger is suspended");
            }
            else{
                throw new Exception("Error: Incorrect password, please check password and try again");
            } 
        }
        throw new Exception("ERROR: This Passenger was not found");
    }
}
