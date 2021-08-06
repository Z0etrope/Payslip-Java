import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayslipTest {
	
	Payslip payslip;
	@BeforeEach
	void setUp() throws Exception {
		payslip = new Payslip();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	final void testGross() {
		assertEquals(5004, payslip.gross("60050"), "method to find the gross income should work");
		assertEquals(100000, payslip.gross("120000"), "method to find the gross income should work");
	}

	@Test
	final void testTax() {
		assertEquals(922, payslip.tax("60050"), "method to find the tax income should work");
		assertEquals(0, payslip.tax("18201"), "method to find the tax income should work");
		assertEquals(3572, payslip.tax("37001"), "method to find the tax income should work");
		assertEquals(54232, payslip.tax("180001"), "method to find the tax income should work");
		assertEquals(19822, payslip.tax("87001"), "method to find the tax income should work");
	}

	@Test
	final void testSuperTax() {
		assertEquals(450, payslip.superTax("9%",5004), "method to find the super tax income should work");
		assertEquals(1000, payslip.superTax("10%",10000), "method to find the super tax income should work");
	}

}
