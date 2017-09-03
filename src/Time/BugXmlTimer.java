package Time;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.ParseException;
import util.WechatUtil;
/**
 * �ڲ�ʱ�ӿ���
 * @author hy
 *
 */
class BugXmlTimerTask extends TimerTask 
{
    	public void run() //ÿ��һ����ʱ�����Զ�������getAccessToken()
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
	static final long hourMill=3600000;    //�������ÿ���೤ʱ��ִ�У�Ĭ��1Сʱ
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
