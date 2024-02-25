package net.javaguides.springboot;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import net.javaguides.springboot.controller.EmployeeController;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SpringbootBackendApplicationTests {

    //Bypassing the Repository to avoid the hitting the database 
    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;
 
	
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // Ensure ObjectMapper is autowired



    @Test
    public void testGetAllEmployees() throws Exception {
        Employee employee1 = new Employee( "John", "Doe", "john.doe@example.com");
        Employee employee2 = new Employee( "Jane", "Doe", "jane.doe@example.com");

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        // Set up MockMvc for testing the standalone employeeController without the need for a full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].firstName", is("John")))
               .andExpect(jsonPath("$[1].firstName", is("Jane")));
    }


    
     @Test
     public void testCreateEmployee() throws Exception {
        Employee employee = new Employee("Praveen", "Patidar","praveenpatidar678@gmail.com");

         // Mock the behavior of your repository to return the mock employee
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        
        // Set up MockMvc for testing the standalone employeeController without the need for a full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

         mockMvc.perform(post("/api/v1/employees")
                 .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Praveen\",\"lastName\":\"Patidar\",\"emailId\":\"praveenpatidar678@gmail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName", is("Patidar")))
                .andExpect(jsonPath("$.firstName", is("Praveen")))
				.andExpect(jsonPath("$.emailId",is("praveenpatidar678@gmail.com")));
     }


	@Test
    void getEmployeeById() throws Exception {
        Employee employee = new Employee("Ranu", "Patidar", "Ranupatidar5678@gmail.com");
        employee.setId(1);

        // Mock the behavior of your repository to return the mock employee
        when(employeeRepository.findById((long) 1)).thenReturn(Optional.of(employee));

        // Set up MockMvc for testing the standalone employeeController without the need for a full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(get("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Ranu"))
                .andExpect(jsonPath("$.lastName").value("Patidar"))
                .andExpect(jsonPath("$.emailId").value("Ranupatidar5678@gmail.com"));
    }

    @Test
    void updateEmployee() throws Exception {
        Employee existingEmployee = new Employee( "Praveen", "Patidar", "praveenpatidar@gmail.com");
        Employee updatedEmployee = new Employee( "ranu", "patidar", "ranupatidar@gmail.com");
        existingEmployee.setId(1);
        updatedEmployee.setId(1);

         // Mock the behavior of your repository to return the mock employee
        when(employeeRepository.findById((long) 1)).thenReturn(java.util.Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        // Set up MockMvc for testing the standalone employeeController without the need for a full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(put("/api/v1/employees/{id}", 1)
                .content(objectMapper.writeValueAsString(updatedEmployee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("ranu"))
                .andExpect(jsonPath("$.lastName").value("patidar"))
                .andExpect(jsonPath("$.emailId").value("ranupatidar@gmail.com"));
    }

    @Test
    void deleteEmployee() throws Exception {
        Employee employee = new Employee( "Ranu", "Patidar", "ranupatidar@gmail.com");
        employee.setId(1);

         // Mock the behavior of your repository to return the mock employee
        when(employeeRepository.findById((long) 1)).thenReturn(java.util.Optional.of(employee));
     
        // Set up MockMvc for testing the standalone employeeController without the need for a full Spring context
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mockMvc.perform(delete("/api/v1/employees/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));

        verify(employeeRepository, times(1)).delete(employee);
    }



}
