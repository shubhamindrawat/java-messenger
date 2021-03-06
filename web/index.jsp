<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/login.css"/>
    </head>
    <body>
        <div id="container" class="form-container-login">
            <form action="login" method="post" autocomplete="off">
                <h1>Log in</h1>
                <input class="username" type="text" name="txt_email" placeholder="Email ID"><br />
                <input class="password" type="password" name="txt_pass" input="password" placeholder="Password"><br />
                <input class="sign-in animated bounceIn" type="submit" value="Sign in">
            </form>
            <div id="login-problem" class="log-problem">
                <p>New user, register here</p>
                <p><a href="register.jsp">Click Here</a></p>
            </div>
        </div>
    </div>        
</body>
</html>