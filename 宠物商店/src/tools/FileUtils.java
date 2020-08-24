package tools;

import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import vo.UserPetItem;

public class FileUtils {
	public static final String SEPARATE_FIELD = ",";  //字段分隔，英文逗号
	public static final String SEPARATE_LINE = "\r\n"; //行分隔
	/**
	 * 保存水果信息
	 */
	public static void savePets(UserPetItem pet,String userName) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String name = "交易记录_" + userName + "_" + format.format(date)+ ".csv";
		InputStream in = null;
		try {
			in = new FileInputStream(name);  //判断本地是否存在文件
			if(in != null){
				in.close();
			    createFile(name,true,pet);  // 存在文件，则修改文件
			}
		} catch (FileNotFoundException e) {
			createFile(name,false,pet);  // 不存在文件，则新建文件
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void createFile(String name,boolean label,UserPetItem pet){
		BufferedOutputStream out = null;  
		StringBuffer sBuffer = new StringBuffer();
		try {
			if(label){
				out = new BufferedOutputStream(new FileOutputStream(name,true));
			}else{
				out = new BufferedOutputStream(new FileOutputStream(name));
				String[] fieldSort = {"宠物编号","种类","品种","数量","总价"};//创建表头
				for (String fieldKey : fieldSort){
					sBuffer.append(fieldKey).append(SEPARATE_FIELD);
			    }
			}
			sBuffer.append(SEPARATE_LINE);
			sBuffer.append(pet.getNumber()).append(SEPARATE_FIELD);
			sBuffer.append(pet.getKind()).append(SEPARATE_FIELD);
			sBuffer.append(pet.getVarieties()).append(SEPARATE_FIELD);
			sBuffer.append(pet.getUnit()).append(SEPARATE_FIELD);
			sBuffer.append(pet.getTotal()).append(SEPARATE_FIELD);
			String str = sBuffer.toString();
			byte[] b = str.getBytes();
			for(int i = 0; i < b.length;i++){
				out.write(b[i]);
			}
		}catch (Exception e) {
				e.printStackTrace();
		}finally{
			if(out != null)
			{
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
