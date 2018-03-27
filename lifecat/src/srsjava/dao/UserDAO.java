package srsjava.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.crypto.provider.RSACipher;

import srsjava.bean.User;
import srsjava.util.DBUtil;

/**
 * user ���ݿ����
 * 
 * @author 59682
 * 
 *         ��¼--��ѯ--�Ƿ������û���username���û�����
 * 
 *         ע��--���--��username��password��ӵ����ݿ�user ע��--��ȡId--��ȡusername��ΨһId��
 * 
 *         ��������--�����û�username��password,id��Ψһ������
 * 
 *         ɾ��--ɾ���û�username
 */
public class UserDAO {

	/*******************************************************************
	 * �û�ע���߼�
	 * 
	 * @param bean
	 ***************************************************************** */

	/**
	 * �ж�username�û����Ƿ��Ѿ�����
	 * 
	 * @return �����ڣ�����true
	 * 
	 * @return �������ڣ�����false
	 */
	public boolean isNameExisted(String username) throws SQLException {
		Connection connection = DBUtil.getConnection();
		System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		int count = -1;

		try {
			// ��ȡ�û���username��count��ֵ
			String sql = "select count(*) as count from user where name=? limit 1";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			count = resultSet.getInt("count");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		// true���� false������
		if (count == 0) {
			return false;
		} else if (count > 0) {
			return true;
		} else {
			System.out.println("�ж�username�Ƿ����ʱ����");
			return true;
		}
	}

	/**
	 * ��username����ʱ--��֤����
	 * 
	 * ������ȷ--��¼--����user���� �������--���ش���
	 * 
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public boolean login(String username, String password) throws SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		String psw = null;

		try {
			String sql = "select password as psw from user where name=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			psw = resultSet.getString("psw");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}

		// �ж������Ƿ�ƥ��
		if (password == psw) {
			// ƥ��
			System.out.println("������ȷ");
			return true;
		} else {
			System.out.println("����������");
			return false;
		}
	}

	/**
	 * ��¼�ɹ���--ͨ��id��ȡuser����
	 * 
	 * @param id
	 * @return
	 */
	public User getByID(int id) {
		User bean = null;

		String sql = "select * from user where id = " + id;

		try (Connection c = DBUtil.getConnection();
				Statement s = c.createStatement();) {

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				bean = new User();
				int oid = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String level = rs.getString("level");
				bean.setName(name);
				bean.setLevel(level);
				bean.setPassword(password);
				bean.setId(oid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * ��¼�ɹ���--ͨ��username��ȡuser����
	 * 
	 * @param user
	 * @return
	 */
	public User getByName(String username) {
		User bean = null;

		try (Connection c = DBUtil.getConnection();
				Statement s = c.createStatement();) {

			String sql = "select * from user where name = " + username;

			ResultSet rs = s.executeQuery(sql);

			if (rs.next()) {
				bean = new User();
				int oid = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String level = rs.getString("level");
				bean.setId(oid);
				bean.setName(name);
				bean.setPassword(password);
				bean.setLevel(level);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/*******************************************************************
	 * �û�ע���߼�
	 * 
	 * @param bean
	 ***************************************************************** */

	/**
	 * ע��user��user���ݱ�
	 * 
	 * @param bean
	 */
	public void add(User bean) {
		// values(id,name,password,level)

		String sql = "insert into user values(?,?,?,?)";

		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, bean.getId());// id
			ps.setString(3, bean.getName());// name
			ps.setString(2, bean.getPassword());// password
			ps.setString(4, bean.getLevel());// level
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ��ע���û���id�� �������е�id��,��idΪ����+1
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public int getIdCount() throws SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;

		try {
			String sql = "select count(id) as count from user";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();
			count = resultSet.getInt("count");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
			connection.close();
		}
		return count;
	}

	/**
	 * �����û�����--ͨ�����޸����롱����
	 * 
	 * @param bean
	 */
	public void updatePassword(User bean) {

		String sql = "update user set password = ? where id = ? ";
		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setString(1, bean.getPassword());
			ps.setInt(2, bean.getId());

			ps.execute();
			System.out.println("�����޸ĳɹ�");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*******************************************************************
	 * ����Ա�����߼�
	 * 
	 * @param bean
	 * @throws SQLException
	 ***************************************************************** */

	/**
	 * ��ȡ���е�user����--����List�б�
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<User> list() throws SQLException {
		return list(0, getIdCount());
	}

	// ��ȡUser������б�
	public List<User> list(int start, int count) {
		List<User> beans = new ArrayList<User>();

		String sql = "select * from User order by id desc limit ?,? ";

		try (Connection c = DBUtil.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User bean = new User();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String level = rs.getString("level");
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(password);
				bean.setLevel(level);
				beans.add(bean);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return beans;
	}
}