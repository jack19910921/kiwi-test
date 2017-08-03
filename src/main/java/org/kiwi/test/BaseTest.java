package org.kiwi.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/6/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@TestExecutionListeners({ServletTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, InitProfileEnvExecutionListener.class})
public abstract class BaseTest {

    public static final String CONFIG = "config";

    protected String configPath;

    @Before
    public void setUp() throws Exception {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        this.configPath = basePath + CONFIG;

        setUpInternal();
    }

    /**
     * hook method
     *
     * @throws Exception
     */
    protected void setUpInternal() throws Exception {
        //implementation in sub class
    }

    /**
     * 业务成功的case
     *
     * @throws Exception
     */
    @Test
    public abstract void onSuccess() throws Exception;

    /**
     * 业务失败的case
     *
     * @throws Exception
     */
    @Test
    public abstract void onFailure() throws Exception;

    /**
     * 业务超时的case
     *
     * @throws Exception
     */
    @Test
    public abstract void onTimeOut() throws Exception;

}
