
package iro.local.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import iro.local.model.Employee;

/**
 * 
 * @author irosha
 * Nov 30, 2017
 */
@Repository
public class EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	static final Logger logger = Logger.getLogger(EmployeeDao.class);
	
	public void addEmployee(Employee employee)throws Exception{
		
		logger.info("--Inside EmployeeDao addEmployee method--");
		
		Session session = sessionFactory.getCurrentSession();
		session.save(employee);
		
	}
	
	public List<Employee> getEmployeeList(){
		
		
		return null;
		
	}
	public Employee getEmployeeById(Integer employeeId){
		
		logger.info("--Inside EmployeeDao getEmployeeById method--");
		return sessionFactory.getCurrentSession().get(Employee.class, employeeId);
		
		
	}
	public void updateEmployee(Employee employee){
		
		logger.info("--Inside EmployeeDao updateEmployee method--");
		Session session = this.sessionFactory.getCurrentSession();
		session.update(employee);
		
	}
	
	public void deleteEmployee(Employee employee){
		
		logger.info("--Inside EmployeeDao deleteEmployee method--");
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(employee);
		
	}
}
