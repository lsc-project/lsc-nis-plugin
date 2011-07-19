package org.lsc.plugins.connectors.nis;

import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrict;
import mockit.NonStrictExpectations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lsc.configuration.objects.Task;
import org.lsc.exception.LscServiceCommunicationException;
import org.lsc.exception.LscServiceConfigurationException;
import org.lsc.exception.LscServiceException;

public class NisSrcServiceTest {
	
	private NisSrcService instance;
	
	@Mocked Task task;

	@Before
	public void setUp() throws LscServiceConfigurationException {
		new NonStrictExpectations() {
			@Injectable @NonStrict NisServiceConfiguration nisSrcService;
			@Injectable @NonStrict NisConnectionConfiguration nisSrcConnection;
			{
				nisSrcService.getMap(); result = "passwd.byname";
				nisSrcService.getConnection(); result = nisSrcConnection;
				nisSrcConnection.getUrl(); result = "nis://127.0.0.1/test.org";
				task.getBean(); result = "org.lsc.beans.SimpleBean";
				task.getSourceService(); result = nisSrcService;
			}
		};
	}
	
	@Test(expected = LscServiceCommunicationException.class)
	public void testConnection() throws LscServiceException {
		instance = new NisSrcService(task);
		Assert.assertNotNull(instance.getBean("jdoe", null, true));
	}
}
