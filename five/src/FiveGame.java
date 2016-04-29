import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by Administrator on 2015/5/19.
 */
public class FiveGame extends JFrame {
	public static final ChessPanel view = new ChessPanel();
	public static final ChessModel model = new ChessModel();
	public static final ChessControllor control = new ChessControllor();
	public static final NetHelper helper = new NetHelper();
	public static JTextArea chatTextArea = new JTextArea();

	private JPanel northPanel = null;

	private JPanel southPanel = null;

	private JPanel westPanel = null;

	private JPanel eastPanel = null;

	private JPanel centerPanel = null;

	private JButton listenBtn = null;

	private JButton connectBtn = null;

	private JButton resetBtn = null;

	private JButton regressButton = null;

	private JButton rsButton = null;

	private JScrollPane jScrollPane = null;

	private JPanel jPanel = null;

	private JTextField messageTextField = null;

	private JButton sendButton = null;
	
	private JButton aboutButton=null;
	
	private JPanel getNorthPanel() {
		if (northPanel == null) {
			northPanel = new JPanel();
			northPanel.setOpaque(false);
			northPanel.setLayout(new FlowLayout());
			listenBtn = new JButton("监听");
			listenBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					listenBtn.setEnabled(false);
					System.out.println("开始卖萌连接");
					FiveGame.helper.listen();

				}
			});
			northPanel.add(listenBtn);

			connectBtn = new JButton("连接");
			connectBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String ip = JOptionPane.showInputDialog("输入对方IP地址");
					if (ip != null) {
						FiveGame.helper.connect(ip);
					} else {
						System.out.println("IP为空");
					}
				}
			});
			northPanel.add(connectBtn);

			resetBtn = new JButton("重开");
			resetBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					FiveGame.helper.send("重开");
					FiveGame.control.resetGame();

				}
			});
			northPanel.add(resetBtn);
		}
		return northPanel;
	}

	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setOpaque(false);
			southPanel.setLayout(new FlowLayout());
			regressButton = new JButton("悔棋");
			regressButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					FiveGame.helper.send("悔棋");
//					FiveGame.model.regretChess();
				}
			});
			southPanel.add(regressButton);

			rsButton = new JButton("认输");
			rsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					FiveGame.helper.send("认输");
					FiveGame.model.rschess();
				}
			});
			southPanel.add(rsButton);
			
			aboutButton=new JButton("关于");
			aboutButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					JOptionPane.showMessageDialog(null, "该程序由13届仿游13990260孙森独立完成，侵权必究。");
				}
			});
			southPanel.add(aboutButton);

			southPanel.setOpaque(false);
		}
		return southPanel;
	}

	/**
	 * This method initializes westPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWestPanel() {
		if (westPanel == null) {
			westPanel = new JPanel();
			westPanel.setLayout(new GridBagLayout());
			westPanel.setOpaque(false);
		}
		return westPanel;
	}

	/**
	 * This method initializes eastPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new BorderLayout());
			eastPanel.add(getJScrollPane(), BorderLayout.CENTER);
			eastPanel.add(getJPanel(), BorderLayout.SOUTH);
			eastPanel.setOpaque(false);
		}
		return eastPanel;
	}


	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = FiveGame.view;
			centerPanel.setLayout(new GridBagLayout());
			centerPanel.setSize(570, 570);
			centerPanel.setOpaque(false);
		}
		return centerPanel;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setOpaque(false);
			jScrollPane.getViewport().setOpaque(false);
			jScrollPane.setViewportView(getChatTextArea());

		}
		return jScrollPane;
	}


	private JTextArea getChatTextArea() {
		if (chatTextArea == null) {
			chatTextArea.setOpaque(false);
			chatTextArea.setEnabled(false);
		}
		return chatTextArea;
	}


	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BorderLayout());
			jPanel.setOpaque(false);
			jPanel.add(getMessageTextField(), BorderLayout.CENTER);
			jPanel.add(getSendButton(), BorderLayout.EAST);
		}
		return jPanel;
	}


	private JTextField getMessageTextField() {
		if (messageTextField == null) {
			messageTextField = new JTextField();
			messageTextField.setOpaque(false);
			messageTextField.setPreferredSize(new Dimension(120, 22));
		}
		return messageTextField;
	}


	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setText("发送");
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FiveGame.control.sendChatMessage(messageTextField.getText());
					messageTextField.setText("");
				}
			});
		}
		return sendButton;
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		new FiveGame();

	}

	/**
	 * This is the default constructor
	 */
	public FiveGame() {
		// TODO 自动生成的构造函数存根
		// super();
		JFrame frame = new JFrame();
		Container contentPanel = frame.getContentPane();
		contentPanel.add(getNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(getSouthPanel(), BorderLayout.SOUTH);
		contentPanel.add(getWestPanel(), BorderLayout.WEST);
		contentPanel.add(getEastPanel(), BorderLayout.EAST);
		contentPanel.add(getCenterPanel(), BorderLayout.CENTER);
		frame.setSize(1000, 700);
		// frame.setBackground(Color.blue);直接设置背景会覆盖棋盘
		frame.getContentPane().setBackground(new Color(176,224, 230));
		frame.getContentPane().setVisible(true);// 如果改为true那么就变成了红色。
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("13990260孙森JAVA五子棋");
		frame.setVisible(true);
	}
}
