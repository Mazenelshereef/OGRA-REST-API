package SWProject.Model.authentication;

import SWProject.Model.storage.SystemData;
import SWProject.Model.users.IUser;
import SWProject.Model.users.admin.*;

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
}
