import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;

public class NetHelper {
	public static final int PORT = 8888;
	BufferedReader in;
	PrintWriter out;

	public void listen() {
		new Thread() {
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(PORT);
					Socket s = ss.accept();
					System.out.println("server:connected");
					FiveGame.control.setStart(true);
					FiveGame.control.setChessColor(ChessModel.BLACK);
					FiveGame.control.setAllow(true);
					in = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					out = new PrintWriter(s.getOutputStream(), true);
					startReadThread();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void sendChess(int row, int col) {
		out.println("下棋:" + row + "," + col);
	}

	private void startReadThread() {
		// TODO Auto-generated method stub
		new Thread() {
			public void run() {
				try {
					while (true) {
						String line = in.readLine();
						System.out.println(line);
						parseMessage(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void send(String line) {
		try {
			out.println(line);
		} catch (RuntimeException e) {
			// TODO 自动生成 catch 块
			// e.printStackTrace();
		}
	}

	protected void parseMessage(String line) {
		// TODO Auto-generated method stub
		if (line.startsWith("下棋")) {
			parseRowCol(line);
		} else if (line.startsWith("聊天")) {
			parseChat(line);
		} else if (line.startsWith("重开")) {
			FiveGame.control.resetGame();
		} else if (line.startsWith("认输")) {
			rs(line);
		} else if (line.startsWith("悔棋")) {
			doRegret(line);
			System.out.println("哈哈，你悔棋了");
		}
	}
	private void parseChat(String line) {
		String[] array = line.split("://");
		FiveGame.control.netChat(array[1]);
	}

	private void doRegret(String line) {
		// TODO 自动生成的方法存根
		if("悔棋".equals(line) == true){
			System.out.println("菜鸟悔棋了");
		    Object[] options ={ "同意", "不同意" };
		    int m = JOptionPane.showOptionDialog(null, "对方悔棋，是否同意？", "请求",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		    if (m == JOptionPane.YES_OPTION) {
		    	FiveGame.model.regretChess();
		    	send("悔棋同意");
		    	FiveGame.control.setAllow(false);
		    } else if(m == JOptionPane.NO_OPTION){
		    	send("悔棋不同意");
	    	}
		}else if("悔棋不同意".equals(line)){
			JOptionPane.showMessageDialog(null, "对方不同意悔棋");
		}else if("悔棋同意".equals(line)){
			FiveGame.model.regretChess();
			FiveGame.control.setAllow(true);
			JOptionPane.showMessageDialog(null, "对方同意了你的悔棋请求");

		}

	}

	private void rs(String line) {
		// TODO 自动生成的方法存根
		JOptionPane.showConfirmDialog(null, "对方认输");
		FiveGame.model.regretChess();
	}

	private void parseRowCol(String line) {
		// TODO Auto-generated method stub
		// 下棋:12,13
		String temp;
		temp = line.substring(3);
		// temp 12,13
		String[] array = temp.split(",");
		// array[0] = "12"
		// array[1] = "13"
		int row = Integer.parseInt(array[0]);
		int col = Integer.parseInt(array[1]);
		FiveGame.control.putChessonline(row, col);
		// FiveGame.control.setStart(true);
	}

	public void connect(String ip) {
		try {
			Socket s = new Socket(ip, PORT);
			System.out.println("client:connected");
			FiveGame.control.setChessColor(ChessModel.WHITE);
			FiveGame.control.setStart(true);
			FiveGame.control.setAllow(false);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			startReadThread();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String msg) {
		out.println(msg);
	}

}
