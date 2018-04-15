import junit.framework.*;


public class helloTest extends TestCase{
	public void testHello(){
		hello h = new hello();
		String res = h.re();
		Assert.assertEquals("Hello, world5", res);
	}
}