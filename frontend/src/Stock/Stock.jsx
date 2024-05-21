import React, { useState } from 'react';
import axios from 'axios';

const Stock = () => {
    const [stockList, setStockList] = useState([]);

    const getStockList = async () =>{
        try{
        const response = await axios.get("http://localhost:8080/stockList",{});
        if(response.status == 200){
            console.log(response.data.obj);
            setStockList(response.data.stockListDTO.stockList);
        }else{
            alert("주식 목록이 없습니다.");
        }
        }catch(err){
            console.error(err);
        }
    }

    
    const handleSubscribe = async(stockId) => {
        try{
            const subscribeResponse = await axios.get("http://localhost:8080/subscribe",{
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ stockId })
            });
        }catch(err){
            console.error(err);
        }
    };

    return (
        <div>
            <h2>Stock List</h2>
            {stockList.map((stockId, index) => (
                <div key={index}>
                    <span>Stock ID: {stockId}</span>
                    <button onClick={() => handleSubscribe(stockId)}>Subscribe</button>
                </div>
            ))}
        </div>
    );
};

export default Stock;