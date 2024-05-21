import React, { useState } from 'react';
import { json, useNavigate } from 'react-router-dom';

const Join = () => {
    const [user, setUser] = useState({
        userId:'',
        userPw:'',
        userName:''
    });
    const navigate = useNavigate();

    const joinRequest = async(userId, userPw, userName) => {
        try{
            const response = await fetch('http://localhost:8080/join',{
                method:'POST',
                header:{
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({userId, userPw, userName})
            });
            if(response.ok){
                const data = await response.json();
                alert("회원가입 되셨습니다.");
                navigate('/');
            }else{
                alert("회원가입 실패");
            }
            }catch(err){
                console.error(err);
            }
        }
      
    return (
         <div>
            <h2>회원가입</h2>
            <form onSubmit={(e) => { e.preventDefault(); joinRequest(user); }}>
                <div>
                    <label>아이디: </label>
                    <input 
                        type="text" 
                        value={user.userId} 
                        onChange={(e) => setUser({...user, userId :e.target.value})} 
                        required 
                    />
                </div>
                <div>
                    <label>비밀번호: </label>
                    <input 
                        type="password" 
                        value={user.userPw} 
                        onChange={(e) => setUser({...user, userPw: e.target.value})} 
                        required 
                    />
                </div>
                <div>
                    <label>이름: </label>
                    <input 
                        type="text" 
                        value={user.userName} 
                        onChange={(e) => setUser({...user, userName: e.target.value})} 
                        required 
                    />
                </div>
                <button type="submit" onClick={() => joinRequest(user)}>가입하기</button>
            </form>    
        </div>
    );
};

export default Join;