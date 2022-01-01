package SWProject.Model.authentication;

import SWProject.Model.users.IUser;

public interface ILoginAuthenticator {
    public IUser login(String username, String password) throws Exception;
}
