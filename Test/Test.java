import org.junit.After;
import org.junit.Before;
import sample.ToDoDatabase;
import sample.ToDoItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by RdDvls on 9/28/16.
 */
public class Test {
    ToDoDatabase todoDatabase;

    @Before
    public void setUp() throws Exception {
        todoDatabase = new ToDoDatabase();
        todoDatabase.init();
    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testInit() throws Exception {
        // test to make sure we can access the new database
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        PreparedStatement todoQuery = conn.prepareStatement("SELECT * FROM todos");
        ResultSet results = todoQuery.executeQuery();
        assertNotNull(results);

    }

}
