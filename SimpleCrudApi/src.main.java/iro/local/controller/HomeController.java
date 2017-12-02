package iro.local.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import iro.local.dto.EmployeeDto;
import iro.local.model.Department;
import iro.local.model.Employee;
import iro.local.service.DepartmentService;
import iro.local.service.EmployeeService;

/**
 * @author irosha
 * Nov 30, 2017
 */

@RestController
public class HomeController {
	
	static final Logger logger = Logger.getLogger(HomeController.class);
	
	private EmployeeService employeeService;
	private DepartmentService departmentService;

	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequest(){
	    
		logger.info("--Initializing HomeController--");
	    
	    ModelAndView model = new ModelAndView();
	   
	    model.setViewName("index");
	    return model;
	  }
  
	 @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") Integer employeeId) {
        
		logger.info("Retriving Employee with employeeId " + employeeId);
		Employee employee = null;
		employee = employeeService.getEmployeeById(employeeId);
	    if(employee == null){
	    	return new ResponseEntity<Employee>(employee,HttpStatus.NOT_FOUND);
	    }else{
	    	return new ResponseEntity<Employee>(employee,HttpStatus.OK);
	    }
		   
	    }
	 
	 @RequestMapping(value = "/employee", method = RequestMethod.POST)
	 public ResponseEntity<Void> addEmployee(@RequestBody EmployeeDto employeeDto) {
        
		logger.info("Adding new Employee");

        String status = employeeService.addEmployee(employeeDto);
        
    	if("SUCCESS".equals(status)){
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	}else if("EXIST".equals(status)){
    		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    	} else{
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
       
	    }
	 
	 @RequestMapping(value = "/employee", method = RequestMethod.PUT)
	 public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDto employeeDto) {
		 
		String status= employeeService.updateEmployee(employeeDto);
		
		if("SUCCESS".equals(status)){
	    	return new ResponseEntity<Void>(HttpStatus.OK);
    	}else if("NOTFOUND".equals(status)){
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	} else{
    		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	 }
	 
	 @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
	 public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		
		String status = employeeService.deleteEmployee(employeeId);
		
		if("SUCCESS".equals(status)){
		    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	 	}else if("NOTFOUND".equals(status)){
	 		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	 	} else{
	 		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	 	}
	 }
	 
	 @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	 @ResponseBody
	 public String addDepartment(@RequestBody Department department) {
        
		logger.info("Adding new Department");
		
        String status = departmentService.addDepartment(department);
        
        	if("SUCCESS".equals(status)){
        		return "SUCCESS" ;
        	}else{
        		return "FAILED";
        	}

	    }
}
