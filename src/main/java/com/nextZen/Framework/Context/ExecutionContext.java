package com.nextZen.Framework.Context;

import application.PageRepository.PageObjectManager.PageObjectManager;
import com.nextZen.Framework.Base.DriverManager;
import com.nextZen.Framework.ConfigReaderUtility.ConfigurationManager;
import com.nextZen.Framework.WebUtility.SeleniumHelper;

public class ExecutionContext {
    private DriverManager driverManager;
    private PageObjectManager pageObjectManager;
    private ConfigurationManager configurationManager;
    private SeleniumHelper seleniumHelper;

    public ExecutionContext() {
        driverManager = new DriverManager(this);
        pageObjectManager = new PageObjectManager(this);
        configurationManager = new ConfigurationManager();
        //seleniumHelper = new SeleniumHelper(this);
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public SeleniumHelper getSeleniumHelper() {
        if (seleniumHelper == null) {
            seleniumHelper = new SeleniumHelper(this);
        }
        return seleniumHelper;
    }

}
