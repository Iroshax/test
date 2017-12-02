package iro.local.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import iro.local.model.Department;


/**
 * 
 * @author irosha
 * Nov 30, 2017
 */
@Repository
public class DepartmentDao {
	
	@Autowired
	private SessionFactory sessionFactory ;
	
	static final Logger logger = Logger.getLogger(DepartmentDao.class);
	
	public void addDepartment(Department department)throws Exception{
		
		logger.info("--Inside DepartmentDao addDepartment method--");
		
		Session session = sessionFactory.getCurrentSession();
		session.save(department);
		//session.close();
		
	}
	
	public List<Department> getDepartmentList(){
		
		return null;
		
	}
	public Department getDepartmentById(Integer departmentId){
		
		Department department = null;
		department = sessionFactory.getCurrentSession().get(Department.class, departmentId);
		return department;
		
	}
	public Department updateDepartment(Department department){
		return department;
		
	}
	
	public void deleteDepartment(Department department){
			
	}

}
