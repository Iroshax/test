package iro.local.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.thetransactioncompany.cors.CORSFilter;

import iro.local.controller.HomeController;
import iro.local.model.Department;
import iro.local.model.Employee;
import iro.local.service.EmployeeService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@WebAppConfiguration
public class HomeControllerTest {
	
	private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .addFilters(new CORSFilter())
                .build();
    }
    
    @Test
    public void test_getEmployee_success() throws Exception {
        
    	Department testDepartment= new Department(1,"test_department");
    	Employee employee = new Employee(1, "John","Jones",testDepartment);

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(get("/employee/{employeeId}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.employeeId", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
        		.andExpect(jsonPath("$.lastName", is("Jones")))
        		.andExpect(jsonPath("$.department", is(testDepartment)));

        verify(employeeService, times(1)).getEmployeeById(1);
        verifyNoMoreInteractions(employeeService);
    }

}
