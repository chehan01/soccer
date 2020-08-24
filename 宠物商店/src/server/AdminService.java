package server;

import java.sql.SQLException;
import java.util.ArrayList;
import vo.*;
import dao.*;
public class AdminService {

	private PetDao petdao = new PetDao();
	
	//注册
	public void signUp(String name,String password) throws SQLException {
		petdao.signUp(name, password);
	}
	
	//登录服务
	public int signInRun(int flag,String user_name,String user_password) throws SQLException, ClassNotFoundException{
		
		int data = petdao.signInRun(flag, user_name, user_password);
		return data;
	}
	
	//注册时为普通用户建表服务
		public void createUserTable(String name,String password) throws SQLException {
			
			petdao.createUserTable(name, password);
		}
	
	//查询商店所有宠物服务
	public  ArrayList<petItem> queryAllData() throws Exception{
		
		ArrayList<petItem> data = petdao.queryAllData();
		return data;
		
	}
	
	//查找全部用户数据服务
	public  ArrayList<UserPetItem> queryAllUserData(String name) throws Exception{
		
		ArrayList<UserPetItem> data = petdao.queryAllUserData(name);
		
		return data;
	}
	
	//按种类查找并返回所有数据服务
	public  ArrayList<petItem> queryKindData(String kind) throws Exception{
		
		ArrayList<petItem> data = petdao.queryKindData(kind);
		
		return data;
	}
	
	//按种类查找并返回品种服务
	public String[] queryVarietiesDataOfKind(String kind) throws Exception{
		
		String[] data = petdao.queryVarietiesDataOfKind(kind);
		
		return data;
	}
	
	//按品种查找服务
	public  ArrayList<petItem> queryVarietiesData(String varieties) throws Exception{
		
		ArrayList<petItem> data = petdao.queryVarietiesData(varieties);
		
		return data;
	}
	
	//添加服务
	public int addPets(int number,String kind,String varieties,int price,int unit) throws Exception{
		
		int data = petdao.addPets(number, kind, varieties, price, unit);
		
		return data;
	}
	
	//修改服务
	public int updatePets(int number,String kind,String varieties,int price,int unit) throws Exception{
		int data = petdao.updatePets(number, kind, varieties, price, unit);
		return data;
	}
	
	//删除服务
	//按编号进行删除
	public int deletePets(int number) throws Exception{
		
		int data = petdao.deletePets(number);
		
		return data;
	}
	
	
	
	//购买服务
	public String buypets(int number,int unit, String name) throws SQLException{
		
		String data = petdao.buypets(number, unit, name);
		
		return data;
	}
	
	//按编号查询用户宠物服务
	public  UserPetItem queryUserDataFromNumber(String name) throws Exception{
		
		UserPetItem data = petdao.queryUserDataFromNumber(name);
		
		return data;
	}
	
	//抛弃宠物服务
	public void deleteAllUserPets(String name) throws SQLException {
		
		petdao.deleteAllUserPets(name);
	}
}
