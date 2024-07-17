import { useState, useEffect } from 'react'
import { Routes, Route, Navigate } from "react-router-dom";
import Header from './components/Header';
import AllListedBooks from "./components/AllListedBooks";
import './App.css'
import SignUp from './components/SignUp';

function App() {
  const [userId, setUserId] = useState(null);

  console.log("Logging user id App:" + userId)

  useEffect(() => {
      fetch('/api/ping')
        .then(response => console.log(response));
        }, []);

  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<AllListedBooks userId={userId} />} />
        <Route path="/signup" element={<SignUp setUserId={setUserId}/>} />
        {/* <Route path="/mydashboard" element={<MyBooksElement />} /> */}
      </Routes>
    </>
  )
}

export default App
