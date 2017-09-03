package Time;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.ParseException;
import util.WechatUtil;
/**
 * 内部时钟控制
 * @author hy
 *
 */
class BugXmlTimerTask extends TimerTask 
{
    	public void run() //每隔一定的时间便会自动的申请getAccessToken()
    	{
    			try {
    				System.out.println(WechatUtil.getAccessToken().getToken());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
}

public class BugXmlTimer  
{
	public	Timer timer;
	static final long hourMill=3600000;    //最后面是每隔多长时间执行，默认1小时
	public void timerStart()
   {
       timer = new Timer();        
       timer.schedule(new BugXmlTimerTask(), 0,hourMill);
   }
   public void timerStop()
   {
       if(timer!=null) 
          timer.cancel();
   }
}
