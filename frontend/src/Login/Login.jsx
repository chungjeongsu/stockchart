import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

const Login = () => {
    const [userId, setUserId] = useState('');
    const [userPw, setUserPw] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const loginRequest = async (userId, userPw) => {
        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ userId, userPw })
            });

            if (response.ok) {
                const data = await response.json();
                navigate('/main');
            } else {
                const errorMessage = await response.text();
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
                    <div className="filed-text">아이디</div>
                    <input
                        className="input-field"
                        type="text"
                        value={userId}
                        onChange={(e) => setUserId(e.target.value)}
                        placeholder="아이디를 입력하세용."
                    />
                    <div className="filed-text">비밀번호</div>
                    <input
                        className="input-field"
                        type="password"
                        value={userPw}
                        onChange={(e) => setUserPw(e.target.value)}
                        onKeyDown={(e) => {
                            if (e.key === 'Enter') {
                                loginRequest(userId, userPw);
                            }
                        }}
                        placeholder="비밀번호를 입력하세용."
                    />
                    {error && <div className="error-message">{error}</div>}
                    <button
                        className="login-button"
                        onClick={() => { loginRequest(userId, userPw); }}
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