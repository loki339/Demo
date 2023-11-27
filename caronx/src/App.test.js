import { render, screen } from '@testing-library/react';
import App.js from './App.js';

test('renders learn react link', () => {
  render(<App.js/>);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
