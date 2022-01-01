package SWProject.Model.authentication;

import SWProject.Model.users.UserInfo;

public interface IRegisterAuthenticator {
    public boolean register(UserInfo userInfo) throws Exception;
}
