package main;

import notification.Command;
import weibostatic.Context;

/**
 * just a main
 * Created by suntr on 7/19/2016.
 */
public class Main {

    public static void main(String[] args){
    	
    	Context context = new Context();
    	context.looper.start();
    	//context.upTask.start();
    	
    	Command cmd = new Command();
    	//cmd.start();
    	
    }
}
