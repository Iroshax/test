package iro.local.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iro.local.dao.EmployeeDao;
import iro.local.dto.EmployeeDto;
import iro.local.model.Department;
import iro.local.model.Employee;

/**
 * @author irosha
 * Nov 30, 2017
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao; 
	@Autowired
	private DepartmentService departmentService;
	
	static final Logger logger = Logger.getLogger(EmployeeService.class);
	
	/**
	 * Add new Employee
	 * 
	 * @param employee
	 * @return String
	 */
	public String addEmployee(EmployeeDto employeeDto){
		
		logger.info("Inside EmployeeService addEmployee method: ");
		
		Employee existingEmployee =null;
		Employee newEmployee =null;
		Department department = null;
		
		existingEmployee = getEmployeeById(employeeDto.getEmployeeId());
	    department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
	   
	    if(existingEmployee == null){
	    	
	    	newEmployee = new Employee();
	    	newEmployee.setEmployeeId(employeeDto.getEmployeeId());
	    	newEmployee.setFirstName(employeeDto.getFirstName());
	    	newEmployee.setLastName(employeeDto.getLastName());
	    	
	    	if(department == null){
	    		
	    		department = new Department();
	    		department.setDepartmentId(0);
	    		department.setDepartmentName("Default");
	    		departmentService.addDepartment(department);
	    		newEmployee.setDepartment(department);
	    	}else{
	    		newEmployee.setDepartment(department);
	    	}
	    	
	    	try {
				
				employeeDao.addEmployee(newEmployee);
				
			} catch (Exception e) {
				
				logger.error("Exception in EmployeeService addEmployee method: "+e);
				return "FAIL";
			}
	    	
	    }else{
	    	return "EXIST";
	    }
		
		return "SUCCESS";
	}
	
	/**
	 * Search Employee
	 * 
	 * @param employeeId
	 * @return Employee
	 */
	public Employee getEmployeeById(Integer employeeId){
		
		logger.info("Inside EmployeeService getEmployeeById method: ");
		
		Employee employee =null;
		
		try {
			employee = employeeDao.getEmployeeById(employeeId);	
			
		} catch (Exception e) {
			logger.error("Exception in EmployeeService getEmployeeById method: "+e);
		}
		return employee;
	}
	
	/**
	 * Update Employee
	 * 
	 * @param employee
	 * @return String
	 */
	public String updateEmployee(EmployeeDto employeeDto){
		
		logger.info("Inside EmployeeService updateEmployee method: ");
		
		Employee existingEmployee =null;
		Department department = null;
		
		existingEmployee = getEmployeeById(employeeDto.getEmployeeId());
		department = departmentService.getDepartmentById(employeeDto.getDepartmentId());
		   
		if(existingEmployee !=null){
			
			existingEmployee.setFirstName(employeeDto.getFirstName());
			existingEmployee.setLastName(employeeDto.getLastName());
			
			if(department == null){
	    		department = new Department();
	    		department.setDepartmentId(0);
	    		department.setDepartmentName("Default");
	    		departmentService.addDepartment(department);
	    		existingEmployee.setDepartment(department);
	    	}else{
	    		existingEmployee.setDepartment(department);
	    	}
			existingEmployee.setDepartment(department);
			try {
				employeeDao.updateEmployee(existingEmployee);
			} catch (Exception e) {
				logger.error("Exception in EmployeeService updateEmployee method: "+e);
				return "FAIL";
			}
			
		}else{
			
			return "NOTFOUND";
		}
		return "SUCCESS";
	}
	
	/**
	 * Delete Employee
	 * 
	 * @param employee
	 * @return String
	 */
	public String deleteEmployee(Integer employeeId){
		
		logger.info("Inside EmployeeService deleteEmployee method: ");
		Employee employee = null;
		employee = getEmployeeById(employeeId);
		
		if(employee!=null){
			try {
				
				employeeDao.deleteEmployee(employee);
				
			} catch (Exception e) {
				logger.error("Exception in EmployeeService deleteEmployee method: "+e);
				return "FAIL";
			}
		}else{
			return "NOTFOUND";
		}
		return "SUCCESS";
	}
	
}
