import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

axios.defaults.withCredentials = true;

const Main = () => {
    const [userInfo, setUserInfo] = useState({
        userName: ''
    });

    const [myStockList, setMyStockList] = useState([]);
    const navigate = useNavigate();  // useNavigate 훅을 사용하여 네비게이트 함수 정의

    const getUserInfo = async () => {
        try {
            const response = await axios.get("http://localhost:8080/main/user-info");
            console.log(response.data.obj);
            setUserInfo({ userName: response.data.obj.userName });
        } catch (err) {
            console.error(err);
        }
    }

    const getMyStockList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/main/my-stock-list");
            console.log(response.data.obj.myStockList);
            setMyStockList(response.data.obj.myStockList);
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        getUserInfo();
        getMyStockList();
    }, []);

    return (
        <div className='main'>
            <div className='main_userinfo'>
                <h1>Welcome, {userInfo.userName}</h1>
            </div>
            <div className='main_stocklist'>
                <h2>Subscribed Stock List</h2>
                {myStockList.map((stock, index) => (
                    <div key={index}>
                        <h3>Stock ID: {stock.stockCode}</h3>
                        <p>Stock Name: {stock.stockName}</p>
                        <p>Stock Price: {stock.stockPrice}</p>
                        <p>Stock Amount: {stock.stockAmount}</p>
                    </div>
                ))}
            </div>
            <button onClick={() => navigate('/stock')}>Go to Stock List</button> {/* 버튼 추가 */}
        </div>
    );
};

export default Main;