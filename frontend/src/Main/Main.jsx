import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Main = () => {
    const [userInfo, setUserInfo] = useState({
        userName: ''
    });

    const [myStockList, setMyStockList] = useState([]);

    const getUserInfo = async () => {
        try {
            const response = await axios.get("http://localhost:8080/main", {});
            console.log(response.data.obj);
            setUserInfo({ userName: response.data.obj.userName });
        } catch (err) {
            console.error(err);
        }
    }

    const getMyStockList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/myStockList", {});
            console.log(response.data.myStockListDTO.myStockList);
            setMyStockList(response.data.myStockListDTO.myStockList);
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
                {myStockList.map((stockName, index) => (
                    <div key={index}>
                        <h3>Stock ID: {stockName}</h3>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Main;