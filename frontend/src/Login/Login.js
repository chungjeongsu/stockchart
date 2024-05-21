const { useState, useRef } = require("react")

const Login = () => {
    const [userId,setUserId] = useState('');
    const [userPw,setUserPw] = useState('');

    async function login(userId, userPw){
        if(userId ==='' || userPw ===''){
            alert("로그인, 비밀번호를 입력해주세요.");
            return;
        }else{
            const loginRequest = new FormData();
            loginRequest.append('userId', userId);
            loginRequest.append('userPw', userPw);

            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                body: loginRequest
            });

            if(response.ok){
               
            }else{
                alert('아이디, 비밀번호 틀림');
            }

        }
    }
    

    return(
        <section className="login-background-container">
            <section className="login-container">
                <section className="login-field-container">
                    <h1 className="login-title">로그인</h1>
                    <div className="filed-text">Id</div>
                    <input className="input-field" type="text" value={userId} onChange={(e)=>setUserId(e.target.value)} placeholder="아이디를 입력하세용."/>
                    <div className="filed-text">비밀번호</div>
                    <input className="input-field" type="text" value={userPw} onChange={(e)=>setUserPw(e.target.value)} onKeyDown={(e)=>{
                        if(e.key === 'Enter'){
                            login(userId,userPw);
                        }
                    }} placeholder="비밀번호를 입력하세용."/>
                    <button className="login-button" onClick={()=>{login(userId, userPw);}}>로그인</button>
                </section>
            </section>
        </section>
    )



}

