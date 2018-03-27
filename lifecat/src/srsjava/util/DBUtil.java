package srsjava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @name ���ݿ�����
 * @author ten
 * @description ����jdbc
 */
public class DBUtil {
	/* ���ݿ��¼��Ϣ */
	static String ip = "111.230.244.153";
	static int port = 3306;
	static String database = "student";
	static String encoding = "UTF-8";
	static String loginName = "root";
	static String password = "wangshihao";
	
	/* �������jdbc.Driver�� */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("success to classify jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//�ַ���ʽ:?useUnicode=true&&characterEncoding=%s  , encoding
		String url = String.format(
				"jdbc:mysql://%s:%d/%s",
				ip, port, database);
		System.out.println("try to connect database!");
		Connection c= DriverManager.getConnection(url, loginName, password);
		if(!c.isClosed()){
			System.out.println("Succeeded connecting to the Database!");
		}else{
			System.out.println("connecting error!");
		}
		return c;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());

	}

}