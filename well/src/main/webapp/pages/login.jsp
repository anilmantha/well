<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Wellness Admin</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/sb-admin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<style>
body {
	background:url(resources/images/login-img.jpg);
	background-size: cover;
}
</style>
</head>

<body>
<form action="home" method="post">
<div class="login-box">
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4 bg-white">
        <div class="group">
          <h3 style="color:#2a3ca1; text-align:center;">Staff Login</h3>
          <hr>
          <c:if test="${loginmessage!=null}">
          	<div class="text-danger"><c:out value="${loginmessage}"></c:out><div>
          </c:if>
          <div class="form">
            <div class="form-group">
              <label>User name</label>
              <input type="text" class="form-control-login" name="username">
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control-login" name="password">
            </div>
            <a href="appointment-booking.html"><input type="submit" value="Login" class="btn btn-primary btn-fit"></a>
          </div>
        </div>
      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</div>
</form>
</body>
</html>
