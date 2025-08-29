
package Dao;

import Model.AssesmentModel;
import Model.ImagesModel;
import java.util.List;

public interface AssesmentDao {
    public void insert(AssesmentModel assesment) throws Exception;
    public List<Object[]> getPasien() throws Exception;
    
}
