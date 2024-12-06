
package Dao;

import Model.LoginModel;


public interface LoginDao {
    public boolean login(LoginModel login) throws Exception;
}
