import './App.css';
import Login from './Login/Login';
import Join from './Join/Join';
import Main from './Main/Main';
import Stock from './Stock/Stock'; // 새로 추가된 Stock 컴포넌트를 임포트합니다.
import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path='/join' element={<Join />} />
          <Route path='/main' element={<Main />} />
          <Route path='/stock' element={<Stock />} /> {/* 새로 추가된 라우트 */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;