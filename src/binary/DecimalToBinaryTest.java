package binary;

import org.junit.Assert;
import org.junit.Test;

public class DecimalToBinaryTest {

	
	@Test
	public void testBinary() {
		DecimalToBinary binary=new DecimalToBinary();
		Assert.assertEquals(Integer.toBinaryString(300), binary.toBinary(300));
		Assert.assertEquals(Integer.toBinaryString(1), binary.toBinary(1));
		Assert.assertEquals(Integer.toBinaryString(43345), binary.toBinary(43345));
	}

}
