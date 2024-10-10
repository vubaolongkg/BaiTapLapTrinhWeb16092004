package vbl.services.impl;

import java.util.List;

import vbl.dao.ICategoryDao;
import vbl.dao.impl.CategoryDaoImpl;
import vbl.models.CategoryModel;
import vbl.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	public ICategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		// TODO Auto-generated method stub
		return cateDao.findById(id);
	}

	@Override
	public CategoryModel findName(String name) {
		// TODO Auto-generated method stub
		return cateDao.findName(name);
	}

	@Override
	public List<CategoryModel> searchByName(String keyword) {
		// TODO Auto-generated method stub
		return cateDao.searchByName(keyword);
	}

	@Override
	public void insert(CategoryModel category) {
		// TODO Auto-generated method stub
		CategoryModel cate = this.findName(category.getCategoryname());
		if (cate == null) {
			cateDao.insert(category);
		} 
	}

	@Override
	public void update(CategoryModel category) {
		// TODO Auto-generated method stub
		CategoryModel cate = this.findById(category.getCategoryid());
		if (cate != null) {
			cateDao.update(category);
		} 
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		cateDao.delete(id);
	}

	@Override
	public void updatestatus(int id, int status) {
		// TODO Auto-generated method stub
		cateDao.updatestatus(id, status);
	}

}
