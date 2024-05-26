import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

axios.defaults.withCredentials = true;

const Main = () => {
    const [userInfo, setUserInfo] = useState({
        userName: ''
    });

    const [myStockList, setMyStockList] = useState([]);
    const navigate = useNavigate();
    const userId = localStorage.getItem("userId");

    const getUserInfo = async (userId) => {
        try {
            const response = await axios.get("http://localhost:8080/main/user-info",{
                params: { userId }
            });
            console.log(response.data.obj);
            setUserInfo({ userName: response.data.obj.userName });
        } catch (err) {
            console.error(err);
        }
    }

    const getMyStockList = async (userId) => {
        try {
            const response = await axios.get("http://localhost:8080/main/my-stock-list",{
                params: { userId }
            });
            console.log(response.data.obj.myStockList);
            setMyStockList(response.data.obj.myStockList);
        } catch (err) {
            console.error(err);
        }
    }


    const handleRemoveSubscribe = async (userId, stockCode) => {
        const response = await axios.post("http://localhost:8080/main/remove-subscribe",
            {userId, stockCode},{
            headers:{
                "Content-Type": 'application/json'
            }}
        );

        alert(response.data.obj.stockName + "이 삭제되었습니다.");
        setMyStockList(prevList => prevList.filter(stock => stock.stockCode !== stockCode));
        
    }

    useEffect(() => {
        console.log(userId);
        getUserInfo(userId);
        getMyStockList(userId);
    }, [userId, navigate]);
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
                        <button onClick={() => handleRemoveSubscribe(userId,stock.stockCode)}>remove</button>
                    </div>
                ))}
            </div>
            <button onClick={() => navigate('/stock')}>Go to Stock List</button>
        </div>
    );
};

export default Main;