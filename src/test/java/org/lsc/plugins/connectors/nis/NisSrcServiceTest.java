package org.lsc.plugins.connectors.nis;

import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrict;
import mockit.NonStrictExpectations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsc.configuration.NisConnectionType;
import org.lsc.configuration.NisSourceServiceType;
import org.lsc.configuration.TaskType;
import org.lsc.exception.LscServiceCommunicationException;
import org.lsc.exception.LscServiceConfigurationException;
import org.lsc.exception.LscServiceException;

public class NisSrcServiceTest {
	
	private NisSrcService instance;
	
	@Mocked TaskType task;

	@Before
	public void setUp() throws LscServiceConfigurationException {
		new NonStrictExpectations() {
			@Injectable @NonStrict NisSourceServiceType nisSrcService;
			@Injectable @NonStrict NisConnectionType nisSrcConnection;
			{
				nisSrcService.getMap(); result = "passwd.byname";
				nisSrcService.getConnection(); result = nisSrcConnection;
				nisSrcConnection.getUrl(); result = "nis://127.0.0.1/test.org";
				task.getBean(); result = "org.lsc.beans.SimpleBean";
				task.getNisSourceService(); result = nisSrcService;
			}
		};
	}
	
	@Test(expected = LscServiceCommunicationException.class)
	public void testConnection() throws LscServiceException {
		instance = new NisSrcService(task);
		Assert.assertNotNull(instance.getBean("jdoe", null, true));
	}
}
