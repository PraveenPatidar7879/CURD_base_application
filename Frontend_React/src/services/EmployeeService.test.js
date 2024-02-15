import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import EmployeeService from './EmployeeService';

// Create a new instance of axios-mock-adapter
const mock = new MockAdapter(axios);

describe('EmployeeService', () => {
  afterEach(() => {
    // Reset axios-mock-adapter after each test
    mock.reset();
  });

  it('should fetch employees', async () => {
    const mockData = [{ id: 1, name: 'John Doe' }];
    mock.onGet('http://localhost:8989/api/v1/employees').reply(200, mockData);

    const response = await EmployeeService.getEmployees();

    expect(response.data).toEqual(mockData);
  });

  it('should create an employee', async () => {
    const employeeData = { name: 'Alice' };
    const createdEmployee = { id: 2, ...employeeData };

    mock.onPost('http://localhost:8989/api/v1/employees', employeeData).reply(201, createdEmployee);

    const response = await EmployeeService.createEmployee(employeeData);

    expect(response.data).toEqual(createdEmployee);
  });

  it('should fetch an employee by ID', async () => {
    const employeeId = 3;
    const mockEmployee = { id: employeeId, name: 'Bob' };

    mock.onGet(`http://localhost:8989/api/v1/employees/${employeeId}`).reply(200, mockEmployee);

    const response = await EmployeeService.getEmployeeById(employeeId);

    expect(response.data).toEqual(mockEmployee);
  });

  it('should update an employee', async () => {
    const employeeId = 4;
    const updatedEmployeeData = { name: 'Charlie' };

    mock.onPut(`http://localhost:8989/api/v1/employees/${employeeId}`, updatedEmployeeData).reply(200, { id: employeeId, ...updatedEmployeeData });

    const response = await EmployeeService.updateEmployee(updatedEmployeeData, employeeId);

    expect(response.data).toEqual({ id: employeeId, ...updatedEmployeeData });
  });

  it('should delete an employee', async () => {
    const employeeId = 5;

    mock.onDelete(`http://localhost:8989/api/v1/employees/${employeeId}`).reply(204);

    const response = await EmployeeService.deleteEmployee(employeeId);

    expect(response.status).toEqual(204);
  });
});
