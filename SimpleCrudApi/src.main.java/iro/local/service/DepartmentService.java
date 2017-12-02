package iro.local.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import iro.local.dao.DepartmentDao;
import iro.local.model.Department;

/**
 * @author irosha
 * Nov 30, 2017
 */
@Service
@Transactional
public class DepartmentService {
	
	@Autowired
	private DepartmentDao DepartmentDao;
	
	static final Logger logger = Logger.getLogger(DepartmentService.class);
	
	public String addDepartment(Department department){
		
		logger.info("Inside DepartmentService addDepartment method: ");
		
		try {
			
			DepartmentDao.addDepartment(department);
			
		} catch (Exception e) {
			
			logger.error("Exception in DepartmentService addDepartment method: "+e);
			return "FAIL";
		}
		return "SUCCESS";
	}
	
	public Department getDepartmentById(Integer departmentId){
	logger.info("Inside DepartmentService getDepartmentById method: ");
	
	Department department =null;
	
	try {
		
		department = DepartmentDao.getDepartmentById(departmentId);
		
		
	} catch (Exception e) {
		logger.error("Exception in DepartmentService getDepartmentById method: "+e);
	}
	return department;
}

}
