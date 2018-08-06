<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="css/login.css"/>
    </head>
    <body>
        <div id="container" class="form-container-register">
            <form action="register" method="post" autocomplete="off">
                <h1>Registration</h1>
                <input class="username" type="text" name="txt_uname" placeholder="Username"><br />
                <input class="username" type="text" name="txt_mobile" placeholder="Mobile Number"><br />
                <input class="username" type="text" name="txt_email" placeholder="Email ID"><br />
                <input class="password" type="password" name="txt_pass" input="password" placeholder="Password"><br />
                <input class="password" type="password" name="txt_cnf_pass" input="password" placeholder="Confirm Password"><br />
                <input class="sign-in animated bounceIn" type="submit" value="Sign up">
            </form>
            <div id="login-problem" class="log-problem">
                <p>Already Registered?</p>
                <p><a href="index.jsp">Click Here</a></p>
            </div>
        </div>
    </body>
</html>