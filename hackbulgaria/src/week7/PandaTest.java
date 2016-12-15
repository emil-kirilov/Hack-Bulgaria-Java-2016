package week7;

import static org.junit.Assert.*;

import org.junit.Test;

public class PandaTest {

	@Test(expected=Exception.class)
	public void creatingAPandaWithInvalidGenderShouldResultInAException() throws Exception {
		Panda p = new Panda("Genderless", "afds@dfs.com", "fmale");
	}
	
	@Test
	public void creatingAPandaShoulSetItsFieldsRight() throws Exception {
		Panda p = new Panda("Milush", "manqk@sam.org", "male");
		assertEquals("Name is wrong", "Milush", p.name());
		assertEquals("Email is wrong" ,"manqk@sam.org", p.email());
		assertEquals("Sex is wrong" , "male" , p.gender());
	}
	
	@Test
	public void samePandasShouldBeEqual() throws Exception {
		Panda p1 = new Panda("Milush", "manqk@sam.org", "male");
		Panda p2 = new Panda("Milush", "manqk@sam.org", "male");
		assertEquals(true, p1.equals(p2));
	}
	
	

}
