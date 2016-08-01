package weibostatic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import timerjob.QuartzJob;
import weibo4j.util.WeiboConfig;

public class Command extends Thread{
	private String[] cmd = {"stop","setid","setkeyword","stoptimer","starttimer"};
	private Context context;
	public Command(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try {	
				System.out.println("YOUR MAJESTY:");
				String cmdString = bReader.readLine();
				String[] cmds = cmdString.toLowerCase().split(" ");
				switch (cmds[0]) {
				case "loopstop":
				case "stoploop":
				case "stoplooper":
				case "stop looper":
				case "stop loop":
					context.looper.stoplooper();
					break;
				case "setid":
					WeiboConfig.updateProperties("concern_id", cmds[1].trim());
					break;
				case "pauseupdate":
					
				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
