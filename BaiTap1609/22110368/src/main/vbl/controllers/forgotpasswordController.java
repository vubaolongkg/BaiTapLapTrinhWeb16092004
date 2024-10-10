package vbl.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vbl.models.UserModel;
import vbl.services.IUserService;
import vbl.services.impl.UserService;
import vbl.utils.Constant;
@WebServlet(urlPatterns = "/forgot")
public class forgotpasswordController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService service = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// Kiểm tra email trong cơ sở dữ liệu
		boolean emailExists = service.checkExistEmail(email);
		if (emailExists) {
			service.update(new UserModel(username, password, email));
			req.setAttribute("message", "Your password reset instructions have been sent!");
		} else {
			// Nếu email không tồn tại
			req.setAttribute("message", "Email not found in our records!");
		}

		// Chuyển hướng lại trang kết quả hoặc form forgot password
		req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/forgot_password.jsp").forward(req, resp);
	}
}
