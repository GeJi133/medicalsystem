import com.aliyuncs.exceptions.ClientException;
import utils.AliyunConfig;

public class TestSms {
    public void sendSms() throws ClientException {
        AliyunConfig aliyunConfig=new AliyunConfig ();
        AliyunConfig.sendSms ("18956778818");
    }
}
