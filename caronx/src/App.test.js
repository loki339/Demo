import { render, screen } from '@testing-library/react';
// Change this import statement
// from:
// import App from './App';
// to:
import App from './App.js';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
