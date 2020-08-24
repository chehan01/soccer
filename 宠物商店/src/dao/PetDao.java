package dao;

import java.sql.*;
import java.util.*;
import dbc.JDBCUtils;
import vo.UserPetItem;
import vo.petItem;

public class PetDao {

	/**
	 * 
	 * @param 彻涵
	 * 本项目开发完成于2020/05/03，上传到CSDN仅供学习交流，严禁用于学术抄袭
	 */
	
	//注册
	public void signUp(String name,String password) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "insert into user values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setString(1, name);
		ps.setString(2, password);
		
		ps.executeUpdate();
		ps.close();
		JDBCUtils.close(conn);
	}
	
	//登录
	public int signInRun(int flag,String user_name,String user_password) throws SQLException, ClassNotFoundException{
		Connection conn = JDBCUtils.getConnection();

		String sqlStr = "select userName,userPassword from user where username = ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		
		ps.setString(1, user_name);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String name = rs.getString(1);
			String password = rs.getString("userPassword");
			if(name.equals(user_name)) {
				flag = 1;
				if(!password.equals(user_password)) {
					flag = 2;
				}
			}
		}
		
		rs.close();
		ps.close();
		JDBCUtils.close(conn);
		return flag;
	}
	
	//查找全部
	public  ArrayList<petItem> queryAllData() throws Exception{
		ArrayList<petItem> list = new ArrayList<petItem>();
		Connection conn = JDBCUtils.getConnection();
		
		Statement stmt = conn.createStatement();
		String sqlStr = "select * from pets";
		ResultSet rs = stmt.executeQuery(sqlStr);
		while (rs.next()) {
			petItem thisPetItem = new petItem();
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setPrice(rs.getDouble("price"));
			thisPetItem.setUnit(rs.getInt("unit"));
			list.add(thisPetItem);
		}
		rs.close();
		stmt.close();
		JDBCUtils.close(conn);
		
		return list;
	}
	
	//查找全部用户数据
	public  ArrayList<UserPetItem> queryAllUserData(String name) throws Exception{
		ArrayList<UserPetItem> list = new ArrayList<UserPetItem>();
		Connection conn = JDBCUtils.getConnection();
		
		Statement stmt = conn.createStatement();
		String sqlStr = "select * from " + name;
		ResultSet rs = stmt.executeQuery(sqlStr);
		while (rs.next()) {
			UserPetItem thisPetItem = new UserPetItem();
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setUnit(rs.getInt("unit"));
			thisPetItem.setTotal(rs.getDouble("total"));
			list.add(thisPetItem);
		}
		rs.close();
		stmt.close();
		JDBCUtils.close(conn);
		
		return list;
	}
	
	//按种类查找并返回所有数据
	public  ArrayList<petItem> queryKindData(String kind) throws Exception{
		ArrayList<petItem> list = new ArrayList<petItem>();
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "select * from pets where kind= ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setString(1, kind);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			petItem thisPetItem = new petItem();
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setPrice(rs.getDouble("price"));
			thisPetItem.setUnit(rs.getInt("unit"));
			list.add(thisPetItem);
		}
		rs.close();
		ps.close();
		JDBCUtils.close(conn);
		return list;
	}
	
	//按种类查找并返回品种
	public String[] queryVarietiesDataOfKind(String kind) throws Exception{
		
		String[] varieties = null;
		int n = 0;
		
		if("全部".equals(kind)) {
			Connection conn = JDBCUtils.getConnection();
			String sqlStr = "select count(distinct varieties) from pets";
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				n = rs.getInt("count(distinct varieties)");
				varieties = new String[n];
			}
			
			rs.close();
			ps.close();
			
			String sqlStr2 = "select distinct varieties from pets";
			PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
			
			ResultSet rs2 = ps2.executeQuery();
			
			int i = 0;
			while(rs2.next()) {
				varieties[i] = rs2.getString("varieties");
				i++;
			}
			
			rs2.close();
			ps2.close();
			JDBCUtils.close(conn);
			
		}else {
			Connection conn = JDBCUtils.getConnection();
			String sqlStr = "select count(distinct varieties) from pets where kind = ?";
			PreparedStatement ps = conn.prepareStatement(sqlStr);
			ps.setString(1, kind);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				n = rs.getInt("count(distinct varieties)");
				varieties = new String[n];
			}
			
			rs.close();
			ps.close();
			
			String sqlStr2 = "select distinct varieties from pets where kind = ?";
			PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
			ps2.setString(1, kind);
			ResultSet rs2 = ps2.executeQuery();
			
			int i = 0;
			while(rs2.next()) {
				varieties[i] = rs2.getString("varieties");
				i++;
			}
			
			rs2.close();
			ps2.close();
			
			JDBCUtils.close(conn);
		}
		
		return varieties;
	}
	
	//按品种查找
	public  ArrayList<petItem> queryVarietiesData(String varieties) throws Exception{
		ArrayList<petItem> list = new ArrayList<petItem>();
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "select * from pets where varieties = ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setString(1, varieties);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			petItem thisPetItem = new petItem();
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setPrice(rs.getDouble("price"));
			thisPetItem.setUnit(rs.getInt("unit"));
			list.add(thisPetItem);
		}
		rs.close();
		ps.close();
		JDBCUtils.close(conn);
		
		return list;
	}
	
	
	//添加宠物
	public int addPets(int number,String kind,String varieties,int price,int unit) throws Exception{
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "INSERT INTO pets VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setInt(1, number);
		ps.setString(2, kind);
		ps.setString(3, varieties);
		ps.setInt(4, price);
		ps.setInt(5, unit);
		int len = ps.executeUpdate();
		ps.close();
		JDBCUtils.close(conn);
		return len;
	}
	//修改宠物
	public int updatePets(int number,String kind,String varieties,int price,int unit) throws Exception{
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "UPDATE pets SET number = ?,kind = ?,varieties = ?,price = ?,unit = ? WHERE number = ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setInt(1, number);
		ps.setString(2, kind);
		ps.setString(3, varieties);
		ps.setInt(4, price);
		ps.setInt(5, unit);
		ps.setInt(6, number);
		int len = ps.executeUpdate();
		ps.close();
		JDBCUtils.close(conn);
		return len;
	}
	//删除宠物
	//按编号进行删除
	public int deletePets(int number) throws Exception{
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "DELETE FROM pets WHERE number = ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setInt(1, number);
//		ps.setString(2, kind);
//		ps.setString(3, varieties);
//		ps.setInt(4, price);
//		ps.setInt(5, unit); 
//		ps.setInt(6, number);
		int len = ps.executeUpdate();
		ps.close();
		JDBCUtils.close(conn);
		return len;
	}
	
	//注册时为普通用户创建表
	public void createUserTable(String name,String password) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "insert into user values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setString(1, name);
		ps.setString(2, password);
		ps.executeUpdate();
		ps.close();
		
		String sqlStr2 = "CREATE TABLE " + name + "(number INT NOT NULL PRIMARY KEY AUTO_INCREMENT,kind ENUM('猫','狗') NOT NULL,varieties VARCHAR(7) NOT NULL,unit INT DEFAULT 0 NOT NULL,total DECIMAL(7,2) NOT NULL)ENGINE=INNODB DEFAULT CHARSET=gbk AUTO_INCREMENT=1";
		PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
		ps2.executeUpdate();
		ps2.close();
		JDBCUtils.close(conn);
	}
	
	//购买操作
	public String buypets(int number,int unit, String name) throws SQLException{
		Connection conn = JDBCUtils.getConnection();
		String alert = null;
		ArrayList<petItem> list = new ArrayList<petItem>();
		petItem thisPetItem = new petItem();
		String sqlStr = "select * from pets where number = ?";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.setInt(1, number);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setPrice(rs.getDouble("price"));
			thisPetItem.setUnit(rs.getInt("unit"));
			list.add(thisPetItem);
		}
		rs.close();
		ps.close();
		if(thisPetItem.getNumber() == 0) {
			alert = "没有这个编号的宠物，请重新查询";
		}else {
			if(thisPetItem.getUnit() < unit) {
				alert = "现有宠物没有那么多了，请重新输入购买数量";
			}else {
				String sqlStr2 = "UPDATE pets SET unit = ? WHERE number = ?";
				PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
				ps2.setInt(1, thisPetItem.getUnit() - unit);
				ps2.setInt(2, number);
				ps2.executeUpdate();
				
				ps2.close();
				alert = "购买成功";
				
				String sqlStr3 = "insert into " + name + "(kind,varieties,unit,total) values(?,?,?,?)";
				PreparedStatement ps3 = conn.prepareStatement(sqlStr3);
				ps3.setString(1, thisPetItem.getKind());
				ps3.setString(2, thisPetItem.getVarieties());
				ps3.setInt(3, unit);
				ps3.setFloat(4, (float) (thisPetItem.getPrice() * unit));
				
				ps3.executeUpdate();
				
				ps3.close();
				
			}
		}
		
		if(thisPetItem.getUnit() - unit == 0) {
			String sqlStr2 = "DELETE FROM pets WHERE number = ?";
			PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
			ps2.setInt(1, number);
			ps2.executeUpdate();
			ps2.close();
		}
		JDBCUtils.close(conn);
		return alert;
	}
	
	//按编号查询用户宠物
	public  UserPetItem queryUserDataFromNumber(String name) throws Exception{
		UserPetItem thisPetItem = new UserPetItem();
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "SELECT * FROM " + name +" ORDER BY number DESC LIMIT 1;";
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			thisPetItem.setNumber(rs.getInt("number"));
			thisPetItem.setKind(rs.getString("kind"));
			thisPetItem.setVarieties(rs.getString("varieties"));
			thisPetItem.setUnit(rs.getInt("unit"));
			thisPetItem.setTotal(rs.getDouble("total"));
		}
		rs.close();
		ps.close();
		JDBCUtils.close(conn);
		
		return thisPetItem;
	}

	//删除所有用户宠物
	public void deleteAllUserPets(String name) throws SQLException {
		Connection conn = JDBCUtils.getConnection();
		String sqlStr = "truncate " + name;
		PreparedStatement ps = conn.prepareStatement(sqlStr);
		ps.executeUpdate();
		ps.close();
		
		JDBCUtils.close(conn);
	}
}
