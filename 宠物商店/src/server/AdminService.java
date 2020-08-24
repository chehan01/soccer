package server;

import java.sql.SQLException;
import java.util.ArrayList;
import vo.*;
import dao.*;
public class AdminService {

	private PetDao petdao = new PetDao();
	
	//ע��
	public void signUp(String name,String password) throws SQLException {
		petdao.signUp(name, password);
	}
	
	//��¼����
	public int signInRun(int flag,String user_name,String user_password) throws SQLException, ClassNotFoundException{
		
		int data = petdao.signInRun(flag, user_name, user_password);
		return data;
	}
	
	//ע��ʱΪ��ͨ�û��������
		public void createUserTable(String name,String password) throws SQLException {
			
			petdao.createUserTable(name, password);
		}
	
	//��ѯ�̵����г������
	public  ArrayList<petItem> queryAllData() throws Exception{
		
		ArrayList<petItem> data = petdao.queryAllData();
		return data;
		
	}
	
	//����ȫ���û����ݷ���
	public  ArrayList<UserPetItem> queryAllUserData(String name) throws Exception{
		
		ArrayList<UserPetItem> data = petdao.queryAllUserData(name);
		
		return data;
	}
	
	//��������Ҳ������������ݷ���
	public  ArrayList<petItem> queryKindData(String kind) throws Exception{
		
		ArrayList<petItem> data = petdao.queryKindData(kind);
		
		return data;
	}
	
	//��������Ҳ�����Ʒ�ַ���
	public String[] queryVarietiesDataOfKind(String kind) throws Exception{
		
		String[] data = petdao.queryVarietiesDataOfKind(kind);
		
		return data;
	}
	
	//��Ʒ�ֲ��ҷ���
	public  ArrayList<petItem> queryVarietiesData(String varieties) throws Exception{
		
		ArrayList<petItem> data = petdao.queryVarietiesData(varieties);
		
		return data;
	}
	
	//��ӷ���
	public int addPets(int number,String kind,String varieties,int price,int unit) throws Exception{
		
		int data = petdao.addPets(number, kind, varieties, price, unit);
		
		return data;
	}
	
	//�޸ķ���
	public int updatePets(int number,String kind,String varieties,int price,int unit) throws Exception{
		int data = petdao.updatePets(number, kind, varieties, price, unit);
		return data;
	}
	
	//ɾ������
	//����Ž���ɾ��
	public int deletePets(int number) throws Exception{
		
		int data = petdao.deletePets(number);
		
		return data;
	}
	
	
	
	//�������
	public String buypets(int number,int unit, String name) throws SQLException{
		
		String data = petdao.buypets(number, unit, name);
		
		return data;
	}
	
	//����Ų�ѯ�û��������
	public  UserPetItem queryUserDataFromNumber(String name) throws Exception{
		
		UserPetItem data = petdao.queryUserDataFromNumber(name);
		
		return data;
	}
	
	//�����������
	public void deleteAllUserPets(String name) throws SQLException {
		
		petdao.deleteAllUserPets(name);
	}
}
