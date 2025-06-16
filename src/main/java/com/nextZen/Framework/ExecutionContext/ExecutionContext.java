package com.nextZen.Framework.ExecutionContext;

import application.PageRepository.PageObjectManager.PageObjectManager;
import com.nextZen.Framework.Base.DriverManager;
import com.nextZen.Framework.ConfigReaderUtility.ConfigurationManager;
import com.nextZen.Framework.WebUtility.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecutionContext {
    Logger log = LogManager.getLogger(ExecutionContext.class);
    private DriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private  ConfigurationManager configurationManager;
    private SeleniumHelper seleniumHelper;

    public ExecutionContext() {
        driverManager = new DriverManager();
        driverManager.setExecutionContext(this);
    }


    public DriverManager getDriverManager() {
        return driverManager;
    }

    public ConfigurationManager getConfigurationManager() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }

        return configurationManager;
    }

    //Lazy Loading of the classes which is not required at boot time.

    public SeleniumHelper getSeleniumHelper() {
        if (seleniumHelper == null) {
            seleniumHelper = new SeleniumHelper(this);
        }
        return seleniumHelper;
    }

    //get page object manager
    public PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager(this);
        }
        return pageObjectManager;
    }


}
