package Time;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimeControl implements ServletContextListener
{
    private BugXmlTimer  mytimer = new BugXmlTimer();
    public void contextInitialized(ServletContextEvent event) 
    {
        mytimer.timerStart();
    }
    public void contextDestroyed(ServletContextEvent event)
    {
        mytimer.timerStop();
    }
}
