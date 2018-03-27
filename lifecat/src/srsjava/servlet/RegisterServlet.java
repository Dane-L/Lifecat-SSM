package srsjava.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import srsjava.bean.User;
import srsjava.dao.UserDAO;

/**
 * ע��
 * 
 * @author 59682
 * 
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * doPost����
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ��ȡ����Ϣ username password
		String username = req.getParameter("rusername");
		String password = req.getParameter("rpassword");
		String message = "";
		String page = "";
		boolean success = false;
		boolean redirect = false;

		UserDAO userdao = new UserDAO();

		try {
			// �ж��Ƿ������ username---���ޣ���ִ��ע��
			if (!userdao.isNameExisted(username)) {

				// �½�user����
				User user = new User();
				user.setId(userdao.getIdCount() + 1);
				user.setName(username);
				user.setPassword(password);
				user.setLevel("user");

				// ִ��ע��
				userdao.add(user);

				// ����session�Ի����Ӳ���������
				HttpSession session = req.getSession();
				session.setAttribute("User", user);

				// ��ת��home.jsp
				System.out.println("�û��������붼��ȷ");
				success = true;
				redirect = true;
				page = "userhome.jsp";

			} else {
				// �û����Ѿ�����
				message = "�˺��Ѵ���";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��successΪfalse
		if (!success) {
			// ������ִ����򷵻ص���½ҳ�棬�����û�����Ĵ�����Ϣ����
			message = "ע����ִ���....!";
			req.setAttribute("message", message);
			req.setAttribute("username", username);
			req.setAttribute("password", password);
			redirect = false;
			page = "index.jsp";
		}

		if (redirect) {
			// �ض�����ת��userhome
			resp.sendRedirect(page);
			return;
		} else {
			req.getRequestDispatcher(page).forward(req, resp);
			return;
		}
	}
}
