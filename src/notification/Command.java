package notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.util.WeiboConfig;

public class Command extends Thread{
	private String[] cmd = {"stop","setid","setkeyword"};
	
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
				case "stop":

					break;
				case "setid":

					break;
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
