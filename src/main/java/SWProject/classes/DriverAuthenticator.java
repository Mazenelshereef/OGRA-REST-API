package SWProject.classes;

public class DriverAuthenticator implements ILoginAuthenticator, IRegisterAuthenticator {

    private static DriverAuthenticator instance;

    private DriverAuthenticator(){}

    public static DriverAuthenticator getInstance(){
        if (instance == null)
            instance = new DriverAuthenticator();
        return instance;
    }

    @Override
    public boolean register(UserInfo userInfo) throws Exception {
        if (SystemData.getInstance().getDriver(userInfo.getUsername()) == null && SystemData.getInstance().getRegistrationRequest(userInfo.getUsername()) == null)
            return SystemData.getInstance().addRegistrationRequest(new RegistrationRequest(userInfo));
        throw new Exception("Error: the username already exists, please try another one");
    }

    @Override
    public IUser login(String username, String password) throws Exception {
        IDriver driver = SystemData.getInstance().getDriver(username);
        if (driver != null){
            if (driver.getPersonalInfo().getPassword().equals(password)){
                if (!driver.getPersonalInfo().getSuspended())
                    return driver;
                else
                    throw new Exception("ERROR: This Driver is suspended");
            }
            else{
                throw new Exception("Error: Incorrect password, please check password and try again");
            } 
        } else if (SystemData.getInstance().getRegistrationRequest(username) != null){
            throw new Exception("ERROR: This Driver isn't varified yet. Please wait for an admin to varify it.");
        }
        throw new Exception("ERROR: This Driver was not found");
    }

    public void recieveRequestResponce(IRegistrationRequest registration) {
        if (registration.isAccepted())
            SystemData.getInstance().addDriver(new Driver((DriverInfo)registration.getUserInfo()));
        SystemData.getInstance().removeRegisrationRequest(registration);
    }

    /*
    @Override
    public IUser search(String username) throws Exception {
        for (IDriver driver : SystemData.getInstance().getDrivers()){
            if (driver.getPersonalInfo().getUsername().equals(username)){
                return driver;
            }    
        }
        throw new Exception("ERROR: This driver was not found");
    }
    */
}
