import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const Login = () => {
    const [userInfo,setUserInfo] = useState({
        userId:"",
        userPw:""
        });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    axios.defaults.withCredentials = true;

    const loginRequest = async (userId, userPw) => {
        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: 'include',
                body: JSON.stringify({ userId, userPw })
            });

            const data = await response.json();
            if(data.code === 'OK'){
                localStorage.setItem('userId', userId);
                console.log(userId);
                alert("환영합니다. "+  userId + "님");
                navigate('/main');
            } else {
                const errorMessage = data.msg;
                alert(errorMessage);
                setError(errorMessage);
            }
        } catch (err) {
            setError('An unexpected error occurred');
        }
    };

    return (
        <section className="login-background-container">
            <section className="login-container">
                <section className="login-field-container">
                    <h1 className="login-title">로그인</h1>
                    <div className="field-text">아이디</div>
                    <input
                        className="input-field"
                        type="text"
                        value={userInfo.userId}
                        onChange={(e) => setUserInfo(prevState => ({...prevState, userId:e.target.value}))}
                        placeholder="아이디를 입력하세요."
                    />
                    <div className="field-text">비밀번호</div>
                    <input
                        className="input-field"
                        type="password"
                        value={userInfo.userPw}
                        onChange={(e) => setUserInfo(prevState => ({...prevState, userPw:e.target.value}))}
                        onKeyDown={(e) => {
                            if (e.key === 'Enter') {
                                loginRequest(userInfo.userId, userInfo.userPw);
                            }
                        }}
                        placeholder="비밀번호를 입력하세요."
                    />
                    <button
                        className="login-button"
                        onClick={() => { loginRequest(userInfo.userId, userInfo.userPw); }}
                    >
                        로그인
                    </button>
                    <button
                        className="join"
                        onClick={() => navigate('/join')}
                    >
                        회원가입
                    </button>
                </section>
            </section>
        </section>
    );
};

export default Login;