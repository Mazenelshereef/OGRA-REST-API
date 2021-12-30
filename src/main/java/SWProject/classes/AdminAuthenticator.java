package SWProject.classes;

public class AdminAuthenticator implements ILoginAuthenticator {

    private static AdminAuthenticator instance;

    private AdminAuthenticator(){}

    public static AdminAuthenticator getInstance(){
        if (instance == null)
            instance = new AdminAuthenticator();
        return instance;
    }

    @Override
    public IUser login(String username, String password) throws Exception {
        IAdmin admin = SystemData.getInstance().getAdmin(username);
        if (admin == null)
            throw new Exception("ERROR: This Admin was not found");
        if (admin.getPassword().equals(password))
            return admin;
        throw new Exception("Error: Incorrect password, please check password and try again");
    }

    /*
    @Override
    public IUser search(String username) throws Exception {
        if (SystemData.getInstance().getAdmin().getUsername().equals(username))
            return SystemData.getInstance().getAdmin();
        throw new Exception("ERROR: This admin was not found");
    }
    */
}
