package vbl.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vbl.models.CategoryModel;
import vbl.services.ICategoryService;
import vbl.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("categories")) {
			List<CategoryModel> list = categoryService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = categoryService.findById(id);
			req.setAttribute("cate", category);
			
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			categoryService.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			String images = "https://anphat.com.vn/media/product/47469_acer_gaming_aspire_5_a515_58gm_53pz_anphatpc55_1.jpg";
			
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			
			categoryService.insert(category);
			System.out.println(category.toString());
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
		    try {
		        // Lấy và chuyển đổi categoryid
		        int categoryid = Integer.parseInt(req.getParameter("categoryid"));

		        // Lấy các thông tin khác từ form
		        String categoryname = req.getParameter("categoryname");

		        // Kiểm tra và chuyển đổi status thành int, nếu không hợp lệ trả về 0
		        String status = req.getParameter("status");
		        int statuss;
		        try {
		            statuss = Integer.parseInt(status);
		        } catch (NumberFormatException e) {
		            statuss = 0; // Giá trị mặc định hoặc có thể đưa ra thông báo lỗi
		        }

		        // Lấy hình ảnh, nếu từ form hoặc giữ nguyên hình mặc định
		        String images = req.getParameter("images");
		        if (images == null || images.isEmpty()) {
		            images = "https://anphat.com.vn/media/product/47469_acer_gaming_aspire_5_a515_58gm_53pz_anphatpc55_1.jpg"; // Hình mặc định
		        }

		        // Tạo đối tượng category
		        CategoryModel category = new CategoryModel();
		        category.setCategoryid(categoryid);
		        category.setCategoryname(categoryname);
		        category.setImages(images);
		        category.setStatus(statuss);

		        // Cập nhật category vào cơ sở dữ liệu
		        categoryService.update(category);

		        // Điều hướng lại trang sau khi cập nhật thành công
		        resp.sendRedirect(req.getContextPath() + "/admin/categories");
		    } catch (Exception e) {
		        e.printStackTrace();
		        // Xử lý lỗi, có thể chuyển hướng tới trang lỗi hoặc hiển thị thông báo
		        resp.sendRedirect(req.getContextPath() + "/admin/error");
		    }
		} else if (url.contains("delete")) {
			
		}

	}
}
