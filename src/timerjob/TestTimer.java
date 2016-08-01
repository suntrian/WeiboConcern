package timerjob;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by suntr on 7/16/2016.
 */
public class TestTimer {
    static int count = 0;

    public static void showTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("time=" + new Date() + "executed times:" + count);

            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minite = calendar.get(Calendar.MINUTE);

        System.out.println(year + " " + month + " "+ day +" " + hour +"  " + minite);

        calendar.set(year,month,day,hour,minite,10);

        //calendar.set(Calendar.HOUR_OF_DAY,10);
       // calendar.set(Calendar.MINUTE,35);
       // calendar.set(Calendar.SECOND,0);


        Date date = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(task,date);

    }

    public static void main(String[] args){
        showTimer();
    }

}
