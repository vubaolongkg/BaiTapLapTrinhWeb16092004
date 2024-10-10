package vbl.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vbl.configs.JDBCConnectMySQL;
import vbl.configs.JDBCConnectSQLServer;
import vbl.dao.ICategoryDao;
import vbl.models.CategoryModel;
import vbl.services.ICategoryService;
import vbl.services.impl.CategoryServiceImpl;

public class CategoryDaoImpl implements ICategoryDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new JDBCConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql = "select * from categories where categoryid=?";
		CategoryModel category = new CategoryModel();
		try {
			conn = new JDBCConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel findName(String name) {
	    String sql = "SELECT * FROM categories WHERE categoryname = ?";
	    CategoryModel category = null;

	    try (Connection conn = new JDBCConnectSQLServer().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, name);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // Khởi tạo đối tượng category nếu có kết quả
	                category = new CategoryModel();
	                category.setCategoryid(rs.getInt("categoryid"));
	                category.setCategoryname(rs.getString("categoryname"));
	                category.setImages(rs.getString("images"));
	                category.setStatus(rs.getInt("status"));
	            }
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return category; // Trả về null nếu không tìm thấy
	}


	@Override
	public List<CategoryModel> searchByName(String keyword) {
		String sql = "select * from categories where category like ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new JDBCConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(CategoryModel category) {
	    // Chỉ chèn các cột categoryname, images, status
	    String sql = "INSERT INTO categories (categoryname, images, status) VALUES (?, ?, ?)";

	    try (Connection conn = new JDBCConnectSQLServer().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	         
	        ps.setString(1, category.getCategoryname());
	        ps.setString(2, category.getImages());
	        ps.setInt(3, category.getStatus());

	        // Sử dụng executeUpdate() cho lệnh INSERT
	        ps.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý ngoại lệ
	    }
	}


	@Override
	public void update(CategoryModel category) {
	    String sql = "UPDATE categories SET categoryname=?, images=?, status=? WHERE categoryid=?";

	    try (Connection conn = new JDBCConnectSQLServer().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, category.getCategoryname());
	        ps.setString(2, category.getImages());
	        ps.setInt(3, category.getStatus());
	        ps.setInt(4, category.getCategoryid());
	        System.out.println("Tui tới đây ròi");
	        // Sử dụng executeUpdate() cho lệnh UPDATE
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace(); // Xử lý ngoại lệ
	    }
	}


	@Override
	public void delete(int id) {
		String sql = "delete from categories where categoryid=?";

		try {
			conn = new JDBCConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);	
			ps.executeQuery();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatestatus(int id, int status) {
		String sql = "update categories set status=? where categoryid=?";

		try {
			conn = new JDBCConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, status);
			ps.executeQuery();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	


}
