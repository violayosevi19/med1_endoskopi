
package Dao;

import Model.AssesmentModel;
import java.util.List;

public interface AssesmentDao {
    public void insert(AssesmentModel assesment) throws Exception;
    public List<AssesmentModel> getAllAssesment() throws Exception;
    
}
