import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Join = () => {
    const [user, setUser] = useState({
        userId: '',
        userPw: '',
        userName: ''
    });
    const navigate = useNavigate();

    const joinRequest = async (user) => {
        try {
            const response = await fetch('http://localhost:8080/join', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });
            const data = await response.json();
            if(data.code === "OK"){
                alert("회원가입 되셨습니다.");
                navigate('/');
            } else {
                alert("회원가입 실패: " + data.msg);
            }
        } catch (err) {
            console.error(err);
            alert("예상치 못한 오류가 발생했습니다.");
        }
    };

    return (
        <div>
            <h2>회원가입</h2>
            <form onSubmit={(e) => { e.preventDefault(); joinRequest(user); }}>
                <div>
                    <label>아이디: </label>
                    <input
                        type="text"
                        value={user.userId}
                        onChange={(e) => setUser({ ...user, userId: e.target.value })}
                    />
                </div>
                <div>
                    <label>비밀번호: </label>
                    <input
                        type="password"
                        value={user.userPw}
                        onChange={(e) => setUser({ ...user, userPw: e.target.value })}
                    />
                </div>
                <div>
                    <label>이름: </label>
                    <input
                        type="text"
                        value={user.userName}
                        onChange={(e) => setUser({ ...user, userName: e.target.value })}
                    />
                </div>
                <button type="submit">가입하기</button>
            </form>
        </div>
    );
};

export default Join;