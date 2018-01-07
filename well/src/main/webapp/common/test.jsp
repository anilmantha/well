<!DOCTYPE html>
<html lang="en">
<head>

</head>

<body>
<div id="wrapper"> 
  
  <!-- Navigation -->
  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"> 
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html"><img src="images/logo-white.png" alt=""></a> </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
      <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
        <ul class="dropdown-menu message-dropdown">
          <li class="message-preview"> <a href="#">
            <div class="media"> <span class="pull-left"> <img class="media-object" src="http://placehold.it/50x50" alt=""> </span>
              <div class="media-body">
                <h5 class="media-heading"><strong>Raghava</strong> </h5>
                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                <p>Lorem ipsum dolor sit amet, consectetur...</p>
              </div>
            </div>
            </a> </li>
          <li class="message-preview"> <a href="#">
            <div class="media"> <span class="pull-left"> <img class="media-object" src="http://placehold.it/50x50" alt=""> </span>
              <div class="media-body">
                <h5 class="media-heading"><strong>Raju</strong> </h5>
                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                <p>Lorem ipsum dolor sit amet, consectetur...</p>
              </div>
            </div>
            </a> </li>
          <li class="message-preview"> <a href="#">
            <div class="media"> <span class="pull-left"> <img class="media-object" src="http://placehold.it/50x50" alt=""> </span>
              <div class="media-body">
                <h5 class="media-heading"><strong>Manas</strong> </h5>
                <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                <p>Lorem ipsum dolor sit amet, consectetur...</p>
              </div>
            </div>
            </a> </li>
          <li class="message-footer"> <a href="#">Read All New Messages</a> </li>
        </ul>
      </li>
      
      <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Mr. Pavan <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li> <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a> </li>
          <!--<li> <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a> </li>-->
          <li> <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a> </li>
          <li class="divider"></li>
          <li> <a href="login.html"><i class="fa fa-fw fa-power-off"></i> Log Out</a> </li>
        </ul>
      </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
      <ul class="nav navbar-nav side-nav">
        <li class="active"> <a href="index.html"><i class="fa fa-fw fa-book"></i> Appointment Booking</a> </li>
        <li> <a href="#"><i class="fa fa-fw fa-check-square"></i> Check Availability</a> </li>
        <li> <a href="#"><i class="fa fa-fw fa-users"></i> Existing Clients</a> </li>
        <li> <a href="#"><i class="fa fa-fw fa-user"></i> New Client Register</a> </li>
      </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </nav>
  <div id="page-wrapper">
    <div class="container-fluid"> 
      
      <!-- Page Heading -->
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header"><small>Appointment Booking</small> </h1>
          <!--<ol class="breadcrumb">
            <li class="active"> <i class="fa fa-dashboard"></i> Dashboard </li>
          </ol>-->
        </div>
      </div>
      
      <!-- Main Content Area -->
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Booking Selection</h3>
            </div>
            <div class="panel-body">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-no-bordered">
                    <tr>
                        <td class="col-md-3"><label>Name</label></td>
                        <td class="col-md-3"><input type="search" class="form-control" id="email"></td>
                        <td class="col-md-6"><span>No. of Visites 4</span></td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Select Appointment Time</label></td>
                        <td class="col-md-3"><select class="form-control">
                	<option>Select Time</option>
                    <option>1:00</option>
                    <option>2:35</option>
                    <option>3:21</option>
                  </select>
                  </td>
                        <td class="col-md-6"></td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Select</label></td>
                        <td class="col-md-3"><select class="form-control">
                	<option>Select</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                  </select></td>
                        <td class="col-md-6"></td>
                    </tr>
                    <tr>
                        <td class="col-md-3"><label>Available Services Packages</label></td>
                        <td class="col-md-3">
                        	<select multiple="" class="form-control">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                        </td>
                        <td class="col-md-6"></td>
                    </tr>
                    <tr>
                    	<td class="col-md-3"></td>
                        <td class="col-md-3"><button type="submit" class="btn btn-primary btn-sm">Add</button></td>
                        <td class="col-md-6"></td>
                    </tr>
                </table>

            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Main Content Area -->
    <!-- /.container-fluid --> 
    
  </div>
  <!-- /#page-wrapper --> 
  
</div>
<!-- /#wrapper --> 

<!-- jQuery --> 
<script src="js/jquery.js"></script> 

<!-- Bootstrap Core JavaScript --> 
<script src="js/bootstrap.min.js"></script> 

<!-- Morris Charts JavaScript --> 
<script src="js/plugins/morris/raphael.min.js"></script> 
<script src="js/plugins/morris/morris.min.js"></script> 
<script src="js/plugins/morris/morris-data.js"></script>
</body>
</html>
