
package Dao;

import Model.LoginModel;
import java.util.Map;


public interface LoginDao {
    public Map<String, Object> login(LoginModel login) throws Exception;
}
