package view;
//import java.sql.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import server.AdminService;
import vo.UserPetItem;
import vo.petItem;
import tools.FileUtils;
public class Subscriber {

	//记录当前用户
	private String userName;
	
	//创建服务对象
	private AdminService adminService;
	
	//创建主窗体及框架组件对象
	private JFrame frame; 
	private JPanel upPanel;
	private JPanel downPanel;
	private CardLayout cardLayout;
	
	private JSplitPane splitPane;
	private JLabel store;
	private JLabel mine;
	private Color onColor;
	private Color offColor;
	private Color bGColor;
	private Color shopColor;
	private Color mineColor;
	//主窗体及框架结束
	
	//firstPanel开始
	private JPanel firstPanel;
	private String[] shoptableHead = {"编号","种类","品种","价格","数量"};
	private JScrollPane tablePanel;
	private JTable table;
	
	private JComboBox<String> choose_kind;
	private JComboBox<String> choose_varieties;
	private String[] kinds = {"全部","猫","狗"};
	private String[] varieties = null;
	
	private JLabel welcome;
	private JLabel tip;
	private JTextField number;
	private JTextField unit;
	private JButton buy;
	
	private JLabel shop_photo;
	private String shop_photoName;
	private ImageIcon shop_background;
	//firstPanel结束
	
	//lastPanel开始
	private JPanel lastPanel;
	private JLabel title;
	private String[] userTableHead = {"编号","种类","品种","数量","总价"};
	private JScrollPane user_tablePanel;
	private JTable user_table;
	
	private JButton returnOfGoods;
	private JButton throwPets;
	private JButton exit;
	
	private JLabel mine_photo;
	private String mine_photoName;
	private ImageIcon mine_background;
	//lastPanel结束
	
	//编写构造函数,实例化各类对象
	public Subscriber(String userName) {
		this.userName = userName;
		//实例化服务对象
		adminService = new AdminService();
		//实例化主窗体及框架组件对象
		frame = new JFrame("阳玉勋宠物商店");
		upPanel = new JPanel();
		downPanel = new JPanel();
		cardLayout = new CardLayout();
		onColor = new Color(255,255,255);
		offColor = new Color(204,204,204);
		
		bGColor = new Color(240,240,240);
		shopColor = new Color(255,244,77);
		mineColor = new Color(255,252,189);
		
		store = new JLabel("商店");
		mine = new JLabel("我的");
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upPanel,downPanel);
		//实例化主窗体及框架组件对象结束
		
		//实例化商店面板及组件对象
		firstPanel = new JPanel();
		table = new JTable();
		tablePanel = new JScrollPane(table);
		
		choose_kind = new JComboBox<String>(kinds);
		choose_varieties = new JComboBox<String>();
		
		welcome = new JLabel("你好，" + userName + "，欢迎光临本店。");
		tip = new JLabel("请输入宠物编号和需要的数量购买");
		number = new JTextField();
		unit = new JTextField();
		buy = new JButton("购买");
		
		shop_photoName = "3.png";
		shop_background = new ImageIcon(shop_photoName);
		shop_photo = new JLabel(shop_background);
		//商店面板及组件结束
		
		//实例化用户面板及组件
		lastPanel = new JPanel();
		title = new JLabel("购买清单");
		user_table = new JTable();
		user_tablePanel = new JScrollPane(user_table);
		
		returnOfGoods = new JButton("退货");
		throwPets = new JButton("扔掉");
		exit = new JButton("退出");
		
		mine_photoName = "4.png";
		mine_background = new ImageIcon(mine_photoName);
		mine_photo = new JLabel(mine_background);
		//用户面板及组件结束
	}
	//构造方法结束
	
	//各组件显示设置
	public void display() throws Exception {
		//设置窗体图标
		Toolkit kit = Toolkit.getDefaultToolkit();
		frame.setIconImage(kit.createImage("timg.jpg"));
		//图标设置结束
		
		//窗体及框架显示设置
		frame.setSize(1000, 590);					//设置宽高
		frame.setLocationRelativeTo(null);			//居中显示
		frame.setResizable(false);					//大小不可调整
		frame.setLayout(null);
		frame.add(splitPane);
		
		splitPane.setBorder(null);
		splitPane.setBounds(10, 10, 968, 535);
		splitPane.setDividerLocation(35);
		splitPane.setDividerSize(0);
		upPanel.setLayout(null);
		upPanel.add(store);
		upPanel.add(mine);
		upPanel.setBackground(bGColor);
		
		downPanel.setLayout(cardLayout);
		downPanel.add(firstPanel);
		downPanel.add(lastPanel);
		//窗体及框架显示结束
		
		//upPanel中的组件设置
		store.setOpaque(true);
		store.setBackground(offColor);
		store.setHorizontalAlignment(JTextField.CENTER);
		store.setBounds(0, 0, 80, 32);
//		store.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor));
		
		mine.setOpaque(true);
		mine.setBackground(onColor);
		mine.setHorizontalAlignment(JTextField.CENTER);
		mine.setBounds(83, 0, 80, 32);
//		mine.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, borderColor));
		//upPanel中的组件结束
		
		//firstPanel中的组件开始
		table.getTableHeader().setReorderingAllowed(false);//列不能移动
		table.getTableHeader().setResizingAllowed(false);//不可拉动表格
		table.setEnabled(false);  //不可更改数据
		table.setBorder(null);
		
//		table.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		showTable();
		
		tablePanel.setBounds(10, 10, 710, 280);
		tablePanel.setBorder(null);
		tablePanel.setBackground(bGColor);
		
		choose_kind.setBounds(735, 10, 100, 30);
		choose_varieties.setBounds(850, 10, 100, 30);
		varieties = adminService.queryVarietiesDataOfKind("全部");
//		choose_varieties.removeAllItems();
		choose_varieties.addItem("喵&汪");
		for(int i = 0;i < varieties.length;i++) {
			choose_varieties.addItem(varieties[i]);
		}
		
		welcome.setBounds(735, 60, 215, 30);
		welcome.setFont(new Font("Serief",Font.PLAIN,13));
		welcome.setHorizontalAlignment(JTextField.CENTER);
		
		tip.setBounds(735, 90, 215, 30);
		tip.setFont(new Font("Serief",Font.PLAIN,13));
		tip.setHorizontalAlignment(JTextField.CENTER);
		number.setBounds(735, 130, 100, 30);
		unit.setBounds(850, 130, 100, 30);
		buy.setBounds(735, 170, 215, 30);
		
		shop_background.setImage(shop_background.getImage().getScaledInstance(950, 200, Image.SCALE_DEFAULT));
		shop_photo.setBounds(10, 290, 950, 200);
		//firstPanel中的组件结束
		
		//firstPanel添加组件
		firstPanel.setBackground(shopColor);
		firstPanel.setLayout(null);
		firstPanel.add(tablePanel);
		firstPanel.add(choose_kind);
		firstPanel.add(choose_varieties);
		firstPanel.add(welcome);
		firstPanel.add(tip);
		firstPanel.add(number);
		firstPanel.add(unit);
		firstPanel.add(buy);
		firstPanel.add(shop_photo);
		//firstPanel添加组件结束
		
		//lastPanel中的组件设置
		title.setBounds(215, 5, 300, 20);
		title.setFont(new Font("Serief",Font.PLAIN,13));
		title.setHorizontalAlignment(JTextField.CENTER);
		
		user_table.getTableHeader().setReorderingAllowed(false);//列不能移动
		user_table.getTableHeader().setResizingAllowed(false);//不可拉动表格
		user_table.setEnabled(false);  //不可更改数据
		user_table.setBorder(null);
		
		showUserTable();
		user_tablePanel.setBounds(10, 30, 710, 280);
		user_tablePanel.setBorder(null);
		user_tablePanel.setBackground(bGColor);
		
		returnOfGoods.setBounds(735, 130, 100, 30);
		throwPets.setBounds(850, 130, 100, 30);
		exit.setBounds(735, 170, 215, 30);
		
		mine_background.setImage(mine_background.getImage().getScaledInstance(950, 200, Image.SCALE_DEFAULT));
		mine_photo.setBounds(10, 290, 950, 200);
		//lastPanel组件设置结束
		
		//lastPanel添加组件
		lastPanel.setLayout(null);
		lastPanel.setBackground(mineColor);
		lastPanel.add(title);
		lastPanel.add(user_tablePanel);
		lastPanel.add(returnOfGoods);
		lastPanel.add(throwPets);
		lastPanel.add(exit);
		lastPanel.add(mine_photo);
		//lastPanel添加组件结束
		
		//默认显示lastPanel
		cardLayout.last(downPanel);
		
		frame.setVisible(true);
	}
	//显示设置结束
	
	//为各组件添加监听器
	public void listener() {
		//设置窗体关闭操作
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
		//商店按钮操作
		store.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				store.setBackground(onColor);
				mine.setBackground(offColor);
				cardLayout.first(downPanel);
			}
		});
		//商店按钮操作结束
		
		//我的按钮操作
		mine.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				mine.setBackground(onColor);
				store.setBackground(offColor);
				cardLayout.last(downPanel);
			}
		});
		//我的按钮操作结束
		
		//种类下拉菜单操作
		choose_kind.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				ArrayList<petItem> dataList;
				String kind = (String)e.getItem();
				try {
					//判断查询的种类
					if("全部".equals(kind)) {
						dataList = adminService.queryAllData();
					}else {
						dataList = adminService.queryKindData(kind);
					}
					
					//为品种下拉菜单赋值
					varieties = adminService.queryVarietiesDataOfKind(kind);
					choose_varieties.removeAllItems();
					if("猫".equals(kind)) {
						choose_varieties.addItem("喵~");
					}else if("狗".equals(kind)) {
						choose_varieties.addItem("汪！");
					}else if("全部".equals(kind)) {
						choose_varieties.addItem("喵&汪");
					}
					
					for(int i = 0;i < varieties.length;i++) {
						choose_varieties.addItem(varieties[i]);
					}
					//品种下拉菜单赋值结束
					
					String[][] tbody = listToArray(dataList);
					TableModel dataModel = new DefaultTableModel(tbody,shoptableHead);
					table.setModel(dataModel);
					
					
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		//种类下拉菜单操作结束
		
		//品种下拉菜单操作
		choose_varieties.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				ArrayList<petItem> dataList;
				String varieties = (String)e.getItem();
				try {
					
					if("喵喵喵".equals(varieties)) {
						dataList = adminService.queryKindData("猫");
					}else if("汪汪汪".equals(varieties)) {
						dataList = adminService.queryKindData("狗");
					}else if("喵&汪".equals(varieties)){
						dataList = adminService.queryAllData();
					}else{
						dataList = adminService.queryVarietiesData(varieties);
					}
					
					String[][] tbody = listToArray(dataList);
					TableModel dataModel = new DefaultTableModel(tbody,shoptableHead);
					table.setModel(dataModel);
					
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		//品种下拉菜单操作结束
		
		//购买按钮操作
		buy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				try {
					String alert = adminService.buypets(Integer.valueOf(number.getText()).intValue(), Integer.valueOf(unit.getText()).intValue(),userName);
					
					ArrayList<petItem> dataList;
					String varieties = (String)choose_varieties.getSelectedItem();
					try {
						//购买后按品种下拉菜单中的显示刷新表格
						if("喵喵喵".equals(varieties)) {
							dataList = adminService.queryKindData("猫");
						}else if("汪汪汪".equals(varieties)) {
							dataList = adminService.queryKindData("狗");
						}else if("喵&汪".equals(varieties)){
							dataList = adminService.queryAllData();
						}else{
							dataList = adminService.queryVarietiesData(varieties);
						}
						
						String[][] tbody = listToArray(dataList);
						TableModel dataModel = new DefaultTableModel(tbody,shoptableHead);
						table.setModel(dataModel);
						
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
					UserPetItem pet = adminService.queryUserDataFromNumber(userName);
					number.setText("");
					unit.setText("");
					showUserTable();
					JOptionPane.showMessageDialog(firstPanel, alert);
					
					FileUtils.savePets(pet,userName);
				} catch (NumberFormatException | SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		//购买按钮操作结束
		
		//退货按钮操作
		returnOfGoods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JOptionPane.showMessageDialog(lastPanel, "想退货？门都没有！");
			}
			
		});
		//退货按钮操作结束
		
		//扔掉按钮操作
		throwPets.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JOptionPane.showMessageDialog(lastPanel, "我走了，你喜欢别人去吧");
				JOptionPane.showMessageDialog(lastPanel, "它走了，我们也走了，你喜欢别人去吧！");
				try {
					adminService.deleteAllUserPets(userName);
					showUserTable();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		//扔掉按钮结束
		
		//退出按钮操作
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
			
		});
		//退出按钮结束
	}
	//添加监听器结束
	
	//为表格赋值
	public void showTable() throws Exception {
		
		ArrayList<petItem> dataList = adminService.queryAllData();
		String[][] tbody = listToArray(dataList);
		//将查询到的结果为table赋值
		TableModel dataModel = new DefaultTableModel(tbody,shoptableHead);
		table.setModel(dataModel);
	}
	//为表格赋值结束
	
	
	private String[][] listToArray(ArrayList<petItem> dataList) {
		// TODO 自动生成的方法存根
		String[][] tbody = new String[dataList.size()][5];
		for(int i = 0; i < dataList.size();i++){
			petItem pet = dataList.get(i);
			tbody[i][0] = pet.getNumber() + "";
			tbody[i][1] = pet.getKind();
			tbody[i][2] = pet.getVarieties();
			tbody[i][3] = pet.getPrice() + "";
			tbody[i][4] = pet.getUnit() + "";
		}
		return tbody;
	}
	
	//为用户的表格赋值
	public void showUserTable() throws Exception {
		
		ArrayList<UserPetItem> dataList = adminService.queryAllUserData(userName);
		String[][] tbody = user_listToArray(dataList);
		//将查询到的结果为table赋值
		TableModel dataModel = new DefaultTableModel(tbody,userTableHead);
		user_table.setModel(dataModel);
	}
	//赋值结束

	//将列表转换为二维数组
	private String[][] user_listToArray(ArrayList<UserPetItem> dataList) {
		// TODO 自动生成的方法存根
		String[][] tbody = new String[dataList.size()][5];
		for(int i = 0; i < dataList.size();i++){
			UserPetItem pet = dataList.get(i);
			tbody[i][0] = pet.getNumber() + "";
			tbody[i][1] = pet.getKind();
			tbody[i][2] = pet.getVarieties();
			tbody[i][3] = pet.getUnit() + "";
			tbody[i][4] = pet.getTotal() + "";
		}
		return tbody;
	}
	//转换结束
	
}
