package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.orm.model.Department;
import edu.upc.eetac.dsa.orm.model.Employee;
import edu.upc.eetac.dsa.orm.util.QueryHelper;
import junit.framework.Assert;
import org.junit.Test;

public class QueryHelperTest {

    @Test
    public void testQueryINSERT() {
        Assert.assertEquals("INSERT INTO Employee (ID, name, surname, salary) VALUES (?, ?, ?, ?)",
                QueryHelper.createQueryINSERT(new Employee("Juan", "lopez", 333333)));
    }

    @Test
    public void testQueryINSERT2() {
        Assert.assertEquals("INSERT INTO Department (ID, name, description) VALUES (?, ?, ?)",
                QueryHelper.createQueryINSERT(new Department("ENTEL", "ENGINYERIA TELEMÀTICA")));
    }

    @Test
    public void testQuerySELECT() {
        Assert.assertEquals("SELECT * FROM Employee WHERE ID = ?",
                QueryHelper.createQuerySELECT(new Employee("Juan", "lopez", 333333)));
    }

    @Test
    public void testQuerySELECT2() {
        Assert.assertEquals("SELECT * FROM Department WHERE ID = ?",
                QueryHelper.createQuerySELECT(new Department("ENTEL", "ENGINYERIA TELEMÀTICA")));
    }
}
