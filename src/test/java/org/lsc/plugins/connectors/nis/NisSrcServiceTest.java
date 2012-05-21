package org.lsc.plugins.connectors.nis;

import java.util.ArrayList;
import java.util.List;

import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrict;
import mockit.NonStrictExpectations;

import org.junit.Assert;
import org.junit.Test;
import org.lsc.beans.IBean;
import org.lsc.configuration.ConnectionType;
import org.lsc.configuration.PluginSourceServiceType;
import org.lsc.configuration.ServiceType.Connection;
import org.lsc.configuration.TaskType;
import org.lsc.exception.LscServiceCommunicationException;
import org.lsc.exception.LscServiceException;
import org.lsc.plugins.connectors.nis.generated.NisSourceServiceSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NisSrcServiceTest {
    
    private static transient Logger LOGGER = LoggerFactory.getLogger(NisSrcServiceTest.class);
	
	private NisSrcService instance;
	
	@Mocked TaskType task;

	@Test
	public void testConnection() throws LscServiceException {

	       new NonStrictExpectations() {
	            @Injectable @NonStrict NisSourceServiceSettings nisSrcService;
	            @Injectable @NonStrict PluginSourceServiceType pluginSourceService;
	            @Injectable @NonStrict ConnectionType nisSrcConnection;
	            @Injectable @NonStrict Connection connection;
	            {
	                nisSrcService.getMap(); result = "passwd.byname";
	                nisSrcConnection.getUrl(); result = "nis://127.0.0.1/lsc-project.org";
	                connection.getReference(); result = nisSrcConnection;
	                nisSrcService.getConnection(); result = connection;
	                task.getBean(); result = "org.lsc.beans.SimpleBean";
	                task.getPluginSourceService(); result = pluginSourceService;
	                List<Object> any = new ArrayList<Object>();
	                any.add(nisSrcService);
	                pluginSourceService.getAny(); result = any;
	            }
	        };

	    try {
	        instance = new NisSrcService(task);
	        IBean jdoeBean = instance.getBean("jdoe", null, true);
	        Assert.assertNotNull(jdoeBean);
            LOGGER.info("Test successful !");
	    } catch(LscServiceCommunicationException e) {
	        LOGGER.info("NIS server unavailable. Test exited successfully !");
	    } catch (LscServiceException e) {
	        throw e;
        }
	}

}
