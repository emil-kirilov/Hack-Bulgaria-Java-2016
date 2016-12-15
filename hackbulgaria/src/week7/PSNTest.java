package week7;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PSNTest {

	PandaSocialNetwork psn;
	
	@Before
	public void initiate() {
		psn = new PandaSocialNetwork();
	}
	
	@Test
	public void addPandaMethodShouldAddPandaToThePSN() throws Exception {
		psn.add(new Panda("Kamen","kamen@seisei.hack","male"));
		
	}

}
