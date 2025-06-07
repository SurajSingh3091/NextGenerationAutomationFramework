package StepDefinition.TestHooks;

import com.nextZen.Framework.Context.ExecutionContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {

  private final ExecutionContext executionContext;
  
    public CucumberHooks(ExecutionContext context){
        this.executionContext= context;
    }
    @Before
    public void setUp(){
        executionContext.getDriverManager().invokeWebApplication();
        //Add Reporter for successfully launching the Browser and URL
    }

    @After
    public void tearDown(){
        executionContext.getDriverManager().killDriverInstance();
        //Add Reporter
    }
}
