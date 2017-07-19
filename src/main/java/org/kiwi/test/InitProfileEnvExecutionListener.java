package org.kiwi.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @email jack.liu.19910921@gmail.com
 * Created by jack on 17/6/30.
 */
public class InitProfileEnvExecutionListener extends AbstractTestExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitProfileEnvExecutionListener.class);

    private static final String PROFILE_ENV = "profile.env";

    private static final String PROFILE_ENV_DEVELOPMENT = "development";

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        try {
            System.setProperty(PROFILE_ENV, PROFILE_ENV_DEVELOPMENT);
            LOGGER.info("beforeTestClass profile.env=[{}] called with [{}].", System.getProperty(PROFILE_ENV), testContext);
        } catch (Exception e) {
            LOGGER.info("init [{}] has some problem:[{}].", getClass().getName(), e.getMessage());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage(), e);
            }
        }
    }

}
