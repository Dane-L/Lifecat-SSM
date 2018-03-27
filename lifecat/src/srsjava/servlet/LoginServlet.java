package srsjava.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;

import srsjava.bean.User;
import srsjava.dao.UserDAO;
import srsjava.util.DBUtil;

/**
 * �û���¼servlet ��¼�ɹ�--��ת��userhome.jsp ��¼ʧ��--���ص�index.jsp
 * 
 * @author 59682
 * 
 */
public class LoginServlet extends HttpServlet {
	/**
	 * doPost����
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* super.doPost(req, resp); */

		// ��ȡ����Ϣ username password
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String message = "";
		String page = "";
		boolean success = false; // �Ƿ���Ҫ��ת��
		boolean redirect = false; // �Ƿ��ض���

		System.out.println("����˵�¼��ť");

		// �������ݿ⣬���е�¼����
		UserDAO userdao = new UserDAO();

		try {
			// �ж��Ƿ������ username
			if (userdao.isNameExisted(username)) {
				// ���д��û���,��֤����
				if (userdao.login(username, password)) {
					// ��������ȷ
					User user = userdao.getByName(username);

					// ����session�Ի����Ӳ���������
					HttpSession session = req.getSession();
					session.setAttribute("User", user);

					// ��ת��home.jsp
					System.out.println("�û��������붼��ȷ");
					success = true;
					// �ض���senRedirect
					redirect = true;
					page = "userhome.jsp";

				} else {
					// �����벻��ȷ
					message = "�������";
				}
			} else {
				// ���޴��û���
				message = "�û�������";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��successΪfalse
		if (!success) {
			// ������ִ����򷵻ص���½ҳ�棬�����û�����Ĵ�����Ϣ����
			message = "��¼���ִ���....!";
			req.setAttribute("message", message);
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			// �����ض���,getRequestDispatcher
			redirect = false;
			page = "index.jsp";
		}

		if (redirect) {
			// �ض�����ת��userhome
			System.out.println("�ض���userhome.jsp");
			resp.sendRedirect(page);
			return;
		} else {
			System.out.println("�ض���index.jsp");
			req.getRequestDispatcher(page).forward(req, resp);
			return;
		}
	}
}
