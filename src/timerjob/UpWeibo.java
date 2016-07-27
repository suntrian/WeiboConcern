package timerjob;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by suntr on 7/16/2016.
 */
public class UpWeibo {
    Timeline tm;

    public UpWeibo(){
        tm = new Timeline(WeiboConfig.getValue("access_token"));
    }

    public Status upStatus(String status){
        try{
            String enStatus = URLEncoder.encode(status,"utf-8");
            Status statu =  tm.updateStatus(status);
            return statu;

        } catch (WeiboException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status upStatus(String status, String[] imagefile){


        return null;
    }


}
