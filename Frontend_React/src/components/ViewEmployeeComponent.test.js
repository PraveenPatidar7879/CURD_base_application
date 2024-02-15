import React from 'react';
import { render, screen } from '@testing-library/react';
import ViewEmployeeComponent from './ViewEmployeeComponent';
import EmployeeService from '../services/EmployeeService';

jest.mock('../services/EmployeeService');

describe('ViewEmployeeComponent', () => {
  const mockEmployeeData = {
    id: 1,
    firstName: 'John',
    lastName: 'Doe',
    emailId: 'john.doe@example.com',
  };

  it('should render View Employee Details', async () => {
    EmployeeService.getEmployeeById.mockResolvedValueOnce({ data: mockEmployeeData });

    render(<ViewEmployeeComponent match={{ params: { id: '1' } }} />);

    // Wait for the promise to resolve
    await Promise.resolve();

    expect(screen.getByText('View Employee Details')).toBeInTheDocument();
    expect(screen.getByText('Employee First Name:').nextElementSibling).toHaveTextContent('John');
    expect(screen.getByText('Employee Last Name:').nextElementSibling).toHaveTextContent('Doe');
  expect(screen.getByText('Employee Email ID:').nextElementSibling).toHaveTextContent('john.doe@example.com');
  });
});
