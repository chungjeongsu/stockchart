import './App.css';
import Login from './Login/Login';
import Join from './Join/Join';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './Main/Main';

function App() {
  return (
    <div>
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<Login />}/>
      <Route path='/join' element={<Join />}/>
      <Route path='/main' element={<Main />}/>
    </Routes>   
    </BrowserRouter>
    </div>
      
  );
}

export default App;
