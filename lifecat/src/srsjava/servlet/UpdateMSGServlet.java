package srsjava.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import srsjava.bean.User;
import srsjava.bean.UserMsg;
import srsjava.dao.UserDAO;
import srsjava.dao.UserMsgDAO;

/**
 * �����û���Ϣ
 * 
 * @author 59682
 * 
 */
public class UpdateMSGServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String message = "";

		try {
			UserMsgDAO usermsgdao = new UserMsgDAO();

			System.out.println("��ͼͨ��session��ȥuser����...");

			// ͨ��session��ȡ��ǰ���û�User״̬
			User user = (User) req.getSession().getAttribute("User");

			System.out.println("��ȡ����req.getSession().getAttribute(user)");

			// ��ȡ����Ϣ username password
			String nickname = req.getParameter("nickname");
			String sex = req.getParameter("sex");
			String age = req.getParameter("age");
			String birthday = req.getParameter("birthday");
			String email = req.getParameter("email");
			int id = user.getId();

			System.out.println("��ȡ����form�ύ������");

			UserMsg usermsg = new UserMsg();

			usermsg.setId(id);
			usermsg.setNickname(nickname);
			usermsg.setSex(sex);
			usermsg.setAge(age);
			usermsg.setBirthday(birthday);
			usermsg.setEmail(email);

			System.out.println("��ͼ�����û���Ϣ...");

			usermsgdao.update(usermsg);

			message = "�ѳɹ������û���Ϣ!";
			System.out.println(message);

			// ����session����
			req.getSession().setAttribute("UserMsg", usermsg);
		} catch (Exception e) {
			System.out.println("�����û���Ϣ--ERROR");
			message = "����ʧ��....";
			e.printStackTrace();
		}

		// ˢ��ҳ��
		req.setAttribute("message", message);
		req.getRequestDispatcher("userhome.jsp").forward(req, resp);
		return;
	}
}
