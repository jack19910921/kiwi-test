package org.kiwi.test;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by jack on 2016/8/11.
 */
public class InitSpringProfilesExecutionListener extends AbstractTestExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitSpringProfilesExecutionListener.class);

    private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        try {
            Class<?> testClass = testContext.getTestClass();
            ActiveProfiles activeProfiles = testClass.getAnnotation(ActiveProfiles.class);
            if (activeProfiles != null) {
                String[] value = activeProfiles.value();
                System.setProperty(SPRING_PROFILES_ACTIVE, value[0]);
            }
            LOGGER.info("beforeTestClass called with [" + testContext + "].");
        } catch (RuntimeException e) {
            LOGGER.info("init [" + this.getClass().getName() + "] has some problem:[" +
                    StringUtils.left(e.getMessage(), 500) + "].");
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.getMessage(), e);
            }
        }
    }
}
