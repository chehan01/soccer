import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import server.AdminService;
import view.Administrator;
import view.Subscriber;
class SignInPanel {
	//�����������
	private AdminService adminService;

	//������½��弰�������
	private JFrame frame;
	private JPanel panel;
	private JLabel userName;
	private JTextField userNameFrame;
	private JLabel userPassword;
	private JPasswordField userPasswordFrame;
	private JButton SignUp;
	private JButton SignIn;
	//��¼��������������

	//����ע����漰�������
	private JDialog dialog;
	private JPanel alert;
	private JLabel son_userName;
	private JTextField son_userNameFrame;
	private JLabel son_userPassword;
	private JPasswordField son_userPasswordFrame;
	private JButton son_SignUp;
	//ע����弰�����������

	//��д�޲ι��캯����ʵ�����������
	public SignInPanel() {
		
		//ʵ�����������
		adminService = new AdminService();

		//ʵ������¼��弰���
		frame = new JFrame("�����̵�-��ӭ��¼");
		panel = new JPanel();
		userName = new JLabel("�û���:");
		userNameFrame = new JTextField();
		userPassword = new JLabel("��   ��:");
		userPasswordFrame = new JPasswordField();
		SignUp = new JButton("ע��");
		SignIn = new JButton("��¼");
		//��¼��弰�����������
		
		//ע����弰�����������
		dialog = new JDialog(frame,"ע��",true);
		alert = new JPanel();
		son_userName = new JLabel("�û���:");
		son_userNameFrame = new JTextField();
		son_userPassword = new JLabel("��   ��:");
		son_userPasswordFrame = new JPasswordField();
		son_SignUp = new JButton("ע��");
		//ע����弰�����������
	}
	//�޲ι��캯������
	
	//��������������ʾ
	public void display() throws HeadlessException{
		
		//���ô���ͼ��
		Toolkit kit = Toolkit.getDefaultToolkit();
		frame.setIconImage(kit.createImage("timg.jpg"));
		//����ͼ�����ý���
		
		//�����С����ʾ
		frame.setSize(350, 180);			//���ÿ��
		frame.setLocationRelativeTo(null);	//�����Ļ����
		frame.setResizable(false);			//���ɵ�����С
		frame.setLayout(null);				//���Բ���
		//�����С����ʾ����
		
		//��¼���������ʾ����
		userName.setBounds(0, 20, 100, 20);
		userName.setHorizontalAlignment(JTextField.CENTER);//���־��ж���
		
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
		//��¼���������ʾ���ý���
		
		//ע�����������ʾ����
		dialog.setSize(350, 180);//���ÿ��
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
		//ע����弰�������ʾ���ý���
	}
	
	//Ϊ�����Ӽ�����
	public void listener() {
		
		//�رմ���ʱ�˳��������򣬶����ǽ����رմ���
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		//��¼����
		SignIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO �Զ����ɵķ������
				int flag = 0;
				try {
					flag = adminService.signInRun(flag,userNameFrame.getText(),String.valueOf(userPasswordFrame.getPassword()));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
				//��ȡ�û������ַ�
				char nameFlag = userNameFrame.getText().charAt(0);
				
				if(flag == 1) {
					
					//�жϸ��˺��Ƿ�Ϊ����Ա�˺�
					if('G' == nameFlag) {
						Administrator ad = new Administrator();
						try {
							ad.display();
							ad.listener();
							frame.dispose();
						} catch (Exception e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
						
					}else {
						Subscriber sub = new Subscriber(userNameFrame.getText());
						try {
							sub.display();
							sub.listener();
							frame.dispose();
						} catch (Exception e1) {
							// TODO �Զ����ɵ� catch ��
							e1.printStackTrace();
						}
					}
				}else if(flag == 2){
					JOptionPane.showMessageDialog(frame, "�������");
					userPasswordFrame.setText("");
				}else {
						JOptionPane.showMessageDialog(frame, "�û������ڣ�");
					userPasswordFrame.setText("");
				}
				
			}
			
		});
		//��¼��������
		
		//�����ע�ᰴť����
		SignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				dialog.setVisible(true);
			}
			
		});
		//�����ע�ᰴť��������
		
		//ע�����رմ������
		dialog.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				son_userNameFrame.setText("");
				son_userPasswordFrame.setText("");
			}
			
		});
		//ע�����رմ����������
		
		//ע�����ע�ᰴť����
		son_SignUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String name = son_userNameFrame.getText();
				String password = String.valueOf(son_userPasswordFrame.getPassword());
				
				try {
					//�жϸ��˺��Ƿ�Ϊ����Ա�˺�
					if('G' == name.charAt(0)) {
						//����Ա�˺Ŵ����˺ű��������û���
						adminService.signUp(name, password);
						dialog.dispose();
					}else {
						//�û��˺Ŵ����˺ű��������û���
						adminService.createUserTable(name,password);
						dialog.dispose();
					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
					JOptionPane.showMessageDialog(dialog, "ע��ʧ�ܣ��밴�ĵ�Ҫ��ע��");
				}
				//��������
				son_userPasswordFrame.setText("");
				
			}
			
		});
		//ע�����ע�ᰴť��������
	}
	
}

public class Main{
	public static void main(String[] args) throws SQLException {
		//������ʵ������¼��壬����ʼ����
		SignInPanel sip = new SignInPanel();
		sip.listener();
		sip.display();
		
	}
}