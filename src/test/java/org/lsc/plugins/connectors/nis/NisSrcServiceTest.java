package org.lsc.plugins.connectors.nis;

import java.util.Properties;

import org.lsc.exception.LscServiceConfigurationException;
import org.lsc.exception.LscServiceException;

import junit.framework.TestCase;


public class NisSrcServiceTest extends TestCase {
	
	private NisSrcService instance;
	
	public NisSrcServiceTest() {
	}
	
	public void setUp() {
		Properties serviceProps = new Properties();
		serviceProps.put("servername", "127.0.0.1");
		serviceProps.put("domain", "test.org");
		serviceProps.put("map", "passwd.byname");
		try {
			instance = new NisSrcService(serviceProps, "org.lsc.beans.SimpleBean");
		} catch (LscServiceConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testOk() throws LscServiceException {
		try {
			assertNotNull(instance.getBean("jdoe", null, true));
		} catch(LscServiceException e) {
			e.printStackTrace();
		}
		
	}

}
