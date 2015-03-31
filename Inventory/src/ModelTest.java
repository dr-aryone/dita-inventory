import static org.junit.Assert.*;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class ModelTest {
	private static Model model = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new Model();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		model = null;
	}

	@Test
	public void testCreateTables() throws Exception {
		Method m = Model.class.getMethod("createTables", null);
		assertNull("Return value should be null",model.transactions(model,m));
	}

}
