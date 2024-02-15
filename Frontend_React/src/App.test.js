import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App'; 
describe('App component', () => {
  test('renders app component with hello text', () => {
    render(<App />);
  });
});
