import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Stock = () => {
    const [stockList, setStockList] = useState([]);
    const userId = localStorage.getItem("userId");

    const getStockList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/stockList");
            if (response.status === 200) {
                console.log(response.data); // 여기에서 데이터 구조를 확인합니다.
                setStockList(response.data.obj.stockList);
            } else {
                alert("주식 목록이 없습니다.");
            }
        } catch (err) {
            console.error(err);
        }
    };

    const handleSubscribe = async (userId, stockCode) => {
        try {
            const subscribeResponse = await axios.post("http://localhost:8080/stockList/subscribe", 
            {stockCode, userId},{
            headers:{
                "Content-Type": 'application/json'
            }
            }
            )

            if (subscribeResponse.status === 200) {
                alert("주식 구독 성공");
            } else {
                alert("주식 구독 실패");
            }
        }catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        getStockList();
    }, []);

    return (
        <div>
            <h2>Stock List</h2>
            {stockList.map((stock, index) => (
                <div key={index}>
                    <span>Stock ID: {stock.stockCode}</span>
                    <span> Stock Name: {stock.stockName}</span>
                    <button onClick={() => handleSubscribe(userId,stock.stockCode)}>Subscribe</button>
                </div>
            ))}
        </div>
    );
};

export default Stock;