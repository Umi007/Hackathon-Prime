import { useState, useEffect } from 'react'
import { Routes, Route, Navigate } from "react-router-dom";
import Header from './components/Header';
import AllListedBooks from "./components/AllListedBooks";
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  useEffect(() => {
      fetch('/api/ping')
        .then(response => console.log(response));
        }, []);

  return (
    <>
      {/* HEADER */}
      <Header />
        <Routes>
          <Route path="/" element={<AllListedBooks />} />
          {/* <Route path="/mydashboard" element={<MyBooksElement />} /> */}
        </Routes>
    </>
  )
}

export default App
