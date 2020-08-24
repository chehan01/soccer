import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import server.AdminService;
import view.Administrator;
import view.Subscriber;
class SignInPanel {
	//声明服务对象
	private AdminService adminService;

	//声明登陆面板及组件对象
	private JFrame frame;
	private JPanel panel;
	private JLabel userName;
	private JTextField userNameFrame;
	private JLabel userPassword;
	private JPasswordField userPasswordFrame;
	private JButton SignUp;
	private JButton SignIn;
	//登录面板组件声明结束

	//声明注册界面及组件对象
	private JDialog dialog;
	private JPanel alert;
	private JLabel son_userName;
	private JTextField son_userNameFrame;
	private JLabel son_userPassword;
	private JPasswordField son_userPasswordFrame;
	private JButton son_SignUp;
	//注册面板及组件声明结束

	//编写无参构造函数，实例化各类对象
	public SignInPanel() {
		
		//实例化服务对象
		adminService = new AdminService();

		//实例化登录面板及组件
		frame = new JFrame("宠物商店-欢迎登录");
		panel = new JPanel();
		userName = new JLabel("用户名:");
		userNameFrame = new JTextField();
		userPassword = new JLabel("密   码:");
		userPasswordFrame = new JPasswordField();
		SignUp = new JButton("注册");
		SignIn = new JButton("登录");
		//登录面板及组件声明结束
		
		//注册面板及组件声明结束
		dialog = new JDialog(frame,"注册",true);
		alert = new JPanel();
		son_userName = new JLabel("用户名:");
		son_userNameFrame = new JTextField();
		son_userPassword = new JLabel("密   码:");
		son_userPasswordFrame = new JPasswordField();
		son_SignUp = new JButton("注册");
		//注册面板及组件声明结束
	}
	//无参构造函数结束
	
	//定义各类组件的显示
	public void display() throws HeadlessException{
		
		//设置窗体图标
		Toolkit kit = Toolkit.getDefaultToolkit();
		frame.setIconImage(kit.createImage("timg.jpg"));
		//窗体图标设置结束
		
		//窗体大小及显示
		frame.setSize(350, 180);			//设置宽高
		frame.setLocationRelativeTo(null);	//相对屏幕居中
		frame.setResizable(false);			//不可调整大小
		frame.setLayout(null);				//绝对布局
		//窗体大小及显示结束
		
		//登录面板各组件显示设置
		userName.setBounds(0, 20, 100, 20);
		userName.setHorizontalAlignment(JTextField.CENTER);//文字居中对齐
		
		userNameFrame.setBounds(110,20,220,20);
		
		userPassword.setBounds(0, 50, 100, 20);
		userPassword.setHorizontalAlignment(JTextField.CENTER);
		
		userPasswordFrame.setBounds(110, 50, 220, 20);
		
		SignUp.setBounds(80, 90, 80, 35);
		
		SignIn.setBounds(180,90,80,35);
		
		panel.setLayout(null);
		frame.add(userName);
		frame.add(userNameFrame);
		frame.add(userPassword);
		frame.add(userPasswordFrame);
		frame.add(SignUp);
		frame.add(SignIn);
		frame.setVisible(true);
		//登录面板各组件显示设置结束
		
		//注册面板各组件显示设置
		dialog.setSize(350, 180);//设置宽高
		dialog.setLocationRelativeTo(null);
		dialog.add(alert);
		
		son_userName.setBounds(0, 20, 100, 20);
		son_userName.setHorizontalAlignment(JTextField.CENTER);
		
		son_userNameFrame.setBounds(110,20,220,20);
		
		son_userPassword.setBounds(0, 50, 100, 20);
		son_userPassword.setHorizontalAlignment(JTextField.CENTER);
		
		son_userPasswordFrame.setBounds(110, 50, 220, 20);
		
		son_SignUp.setBounds(130, 90, 80, 35);
		
		alert.setLayout(null);
		alert.add(son_userName);
		alert.add(son_userNameFrame);
		alert.add(son_userPassword);
		alert.add(son_userPasswordFrame);
		alert.add(son_SignUp);
		//注册面板及各组件显示设置结束
	}
	
	//为组件添加监听器
	public void listener() {
		
		//关闭窗体时退出整个程序，而不是仅仅关闭窗口
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		//登录操作
		SignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO 自动生成的方法存根
				int flag = 0;
				try {
					flag = adminService.signInRun(flag,userNameFrame.getText(),String.valueOf(userPasswordFrame.getPassword()));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				//获取用户名首字符
				char nameFlag = userNameFrame.getText().charAt(0);
				
				if(flag == 1) {
					
					//判断该账号是否为管理员账号
					if('G' == nameFlag) {
						Administrator ad = new Administrator();
						try {
							ad.display();
							ad.listener();
							frame.dispose();
						} catch (Exception e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						
					}else {
						Subscriber sub = new Subscriber(userNameFrame.getText());
						try {
							sub.display();
							sub.listener();
							frame.dispose();
						} catch (Exception e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
				}else if(flag == 2){
					JOptionPane.showMessageDialog(frame, "密码错误！");
					userPasswordFrame.setText("");
				}else {
						JOptionPane.showMessageDialog(frame, "用户不存在！");
					userPasswordFrame.setText("");
				}
				
			}
			
		});
		//登录操作结束
		
		//主面板注册按钮操作
		SignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dialog.setVisible(true);
			}
			
		});
		//主面板注册按钮操作结束
		
		//注册面板关闭窗体操作
		dialog.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				son_userNameFrame.setText("");
				son_userPasswordFrame.setText("");
			}
			
		});
		//注册面板关闭窗体操作结束
		
		//注册面板注册按钮操作
		son_SignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = son_userNameFrame.getText();
				String password = String.valueOf(son_userPasswordFrame.getPassword());
				
				try {
					//判断该账号是否为管理员账号
					if('G' == name.charAt(0)) {
						//管理员账号存入账号表，不创建用户表
						adminService.signUp(name, password);
						dialog.dispose();
					}else {
						//用户账号存入账号表，并创建用户表
						adminService.createUserTable(name,password);
						dialog.dispose();
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
					JOptionPane.showMessageDialog(dialog, "注册失败！请按文档要求注册");
				}
				//清空密码框
				son_userPasswordFrame.setText("");
				
			}
			
		});
		//注册面板注册按钮操作结束
	}
	
}

public class Main{
	public static void main(String[] args) throws SQLException {
		//创建并实例化登录面板，程序开始运行
		SignInPanel sip = new SignInPanel();
		sip.listener();
		sip.display();
		
	}
}