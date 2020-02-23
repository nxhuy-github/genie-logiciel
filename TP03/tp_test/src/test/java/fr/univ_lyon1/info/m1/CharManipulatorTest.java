package fr.univ_lyon1.info.m1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CharManipulatorTest {

	CharManipulator manip;
	
	@Before
	public void init(){
		manip = new CharManipulator();
	}
	
	@Test
	public void test() {
		
	}
	
	@Test
	public void orderNormalStringTest() {
		assertEquals("A",manip.invertOrder("A"));
		assertEquals("DCBA",manip.invertOrder("ABCD"));
		assertEquals("321DCBA",manip.invertOrder("ABCD123"));
		
	}

	@Test
	public void orderEmptyStringTest()
	{
		assertEquals("", manip.invertOrder(""));
		assertEquals("", manip.invertCase(""));
		assertEquals("", manip.removePattern("", ""));
	}
	
	@Test
	public void invertCaseStringTest(){
		assertEquals("ABcd", manip.invertCase("abCD"));
	}
	
	


}
