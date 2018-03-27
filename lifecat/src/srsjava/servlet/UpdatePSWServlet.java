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
 * �޸�����
 * 
 * @author 59682
 * 
 */
public class UpdatePSWServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ��ȡ����Ϣ username password
		String fpassword = req.getParameter("fpassword");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");

		String message = "";

		if (fpassword != req.getSession().getAttribute("username")) {
			message = "ԭ�����������";
			System.out.println(message);
		} else if (password1 != password2) {
			message = "�����������벻һ��";
			System.out.println(message);
		} else {
			// ��֤ͨ��
			try {
				UserDAO userdao = new UserDAO();

				// ͨ��session��ȡ��ǰ���û�User״̬
				User user = (User) req.getSession().getAttribute("User");
				
				System.out.println("��ͼ��������...");

				// ��������
				user.setPassword(password1);
				userdao.updatePassword(user);
				message = "�ѳɹ���������!";
				System.out.println(message);

				// ����session����
				req.getSession().setAttribute("User", user);

			} catch (Exception e) {
				message="����ʧ��...";
				e.printStackTrace();
			}
		}

		// ˢ��ҳ��
		req.setAttribute("message", message);
		req.getRequestDispatcher("userhome.jsp").forward(req, resp);
		return;
	}
}
