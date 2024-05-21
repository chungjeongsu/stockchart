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
            if (response.ok) {
                const data = await response.json();
                alert("회원가입 되셨습니다.");
                navigate('/');
            } else {
                alert("회원가입 실패");
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
                        required
                    />
                </div>
                <div>
                    <label>비밀번호: </label>
                    <input
                        type="password"
                        value={user.userPw}
                        onChange={(e) => setUser({ ...user, userPw: e.target.value })}
                        required
                    />
                </div>
                <div>
                    <label>이름: </label>
                    <input
                        type="text"
                        value={user.userName}
                        onChange={(e) => setUser({ ...user, userName: e.target.value })}
                        required
                    />
                </div>
                <button type="submit">가입하기</button>
            </form>
        </div>
    );
};

export default Join;