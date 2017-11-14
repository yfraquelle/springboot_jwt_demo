package cn.edu.nju.dzy;

import cn.edu.nju.dzy.config.DefaultProfileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * This is a helper Java class that provides an alternative to creating a web.xml.
 */
public class OnlinexamApplicationWebXml extends SpringBootServletInitializer {

    private final Logger log = LoggerFactory.getLogger(OnlinexamApplicationWebXml.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        /**
         * set a default to use when no profile is configured.
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(OnlinexamApplication.class);
    }
}
