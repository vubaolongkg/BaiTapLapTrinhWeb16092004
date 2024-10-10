package vbl.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vbl.models.UserModel;
@WebServlet(urlPatterns = {"/waiting"})
public class waitingController extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
		UserModel u=(UserModel) session.getAttribute("account");
		req.setAttribute("username", u.getUsername());
		if(u.getRoleid()==1) {
		resp.sendRedirect(req.getContextPath()+"/waiting");
		}else if(u.getRoleid()==2) {
		resp.sendRedirect(req.getContextPath()+"/views/DemoDangXuat.jsp");
		}else {
		resp.sendRedirect(req.getContextPath()+"/home");
		}
		}else {
		resp.sendRedirect(req.getContextPath()+"/login");
		}}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
