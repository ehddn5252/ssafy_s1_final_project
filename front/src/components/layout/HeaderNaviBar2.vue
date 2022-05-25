<template>
<div class="container">
  
  <nav class="navbar navbar-icon-top navbar-expand-lg navbar-dark bg-dark">
   <a class="navbar-brand" href="#">Therichpost</a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
     <span class="navbar-toggler-icon"></span>
   </button>
 
   <div class="collapse navbar-collapse" id="navbarSupportedContent">
     <ul class="navbar-nav mr-auto">
       <li class="nav-item active">
         <a class="nav-link" href="#">
           <i class="fa fa-home"></i>
           Home
           <span class="sr-only">(current)</span>
           </a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="#">
           <i class="fa fa-envelope-o">
             <span class="badge badge-danger">11</span>
           </i>
           Link
         </a>
       </li>
       <li class="nav-item">
         <a class="nav-link disabled" href="#">
           <i class="fa fa-envelope-o">
             <span class="badge badge-warning">11</span>
           </i>
           Disabled
         </a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="#">
           <i class="fa fa-envelope-o">
             <span class="badge badge-primary">11</span>
           </i>
           Link
         </a>
         
       </li>
     </ul>
     <ul class="navbar-nav ">
       <li class="nav-item">
         <a class="nav-link" href="#">
           <i class="fa fa-bell">
             <span class="badge badge-info">11</span>
           </i>
           Test
         </a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="#">
           <i class="fa fa-globe">
             <span class="badge badge-success">11</span>
           </i>
           Test
         </a>
       </li>
     </ul>
     <form class="form-inline my-2 my-lg-0">
       <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
     </form>
   </div>
 </nav>
  
  </div> 
  <!--container end.//-->
  
</template>

<script>

</script>
<style scoped>
@import url("//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");
.navbar-icon-top .navbar-nav .nav-link > .fa {
  position: relative;
  width: 36px;
  font-size: 24px;
}
.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  font-size: 0.75rem;
  position: absolute;
  right: 0;
  font-family: sans-serif;
}
.navbar-icon-top .navbar-nav .nav-link > .fa {
  top: 3px;
  line-height: 12px;
}
.navbar-icon-top .navbar-nav .nav-link > .fa > .badge {
  top: -10px;
}
@media (min-width: 576px) {
  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }
  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }
  .navbar-icon-top.navbar-expand-sm .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}
@media (min-width: 768px) {
  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }
  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }
  .navbar-icon-top.navbar-expand-md .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}
@media (min-width: 992px) {
  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }
  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }
  .navbar-icon-top.navbar-expand-lg .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}
@media (min-width: 1200px) {
  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link {
    text-align: center;
    display: table-cell;
    height: 70px;
    vertical-align: middle;
    padding-top: 0;
    padding-bottom: 0;
  }
  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa {
    display: block;
    width: 48px;
    margin: 2px auto 4px auto;
    top: 0;
    line-height: 24px;
  }
  .navbar-icon-top.navbar-expand-xl .navbar-nav .nav-link > .fa > .badge {
    top: -7px;
  }
}
</style>

<!-- <style scoped>
h1.logo * {
  font-size: 24px;
  text-shadow: rgba(0, 0, 0, 0.267) 1px 1px 1px;
  line-height: 1;
  font-weight: 700;
  letter-spacing: 1px;
  font-family: Poppins, sans-serif;
  text-transform: uppercase;
}

.navbar {
  padding: 0;
}
.navbar ul {
  margin: 0;
  padding: 0;
  display: flex;
  list-style: none;
  align-items: center;
}
.navbar li {
  position: relative;
}
.navbar a,
.navbar a:focus {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 8px 16px;
  margin-left: 10px;
  font-family: "Raleway", sans-serif;
  font-size: 16px;
  font-weight: 700;
  color: #fff;
  white-space: nowrap;
  transition: 0.3s;
}

.navbar a i,
.navbar a:focus i {
  font-size: 12px;
  line-height: 0;
  margin-left: 5px;
}
.navbar a:hover,
.navbar .active,
.navbar .active:focus,
.navbar li:not(.inactive-nav):hover > a {
  background: rgba(255, 255, 255, 0.2);
}
.navbar .dropdown ul {
  display: block;
  position: absolute;
  left: 10px;
  top: calc(100% + 30px);
  margin: 0;
  padding: 10px 0;
  z-index: 99;
  opacity: 0;
  visibility: hidden;
  background: #fff;
  box-shadow: 0px 0px 30px rgba(127, 137, 161, 0.25);
  transition: 0.3s;
}
.navbar .dropdown ul li {
  min-width: 200px;
}
.navbar .dropdown ul a {
  padding: 10px 20px;
  font-size: 15px;
  text-transform: none;
  font-weight: 600;
  color: #2f4d5a;
}
.navbar .dropdown ul a i {
  font-size: 12px;
}
.navbar .dropdown ul a:hover,
.navbar .dropdown ul .active:hover,
.navbar .dropdown ul li:not(.inactive-nav):hover > a {
  color: #67b0d1;
}
.navbar .dropdown:hover > ul {
  opacity: 1;
  top: 100%;
  visibility: visible;
}
.navbar .dropdown .dropdown ul {
  top: 0;
  left: calc(100% - 30px);
  visibility: hidden;
}
.navbar .dropdown .dropdown:hover > ul {
  opacity: 1;
  top: 0;
  left: 100%;
  visibility: visible;
}
@media (max-width: 1366px) {
  .navbar .dropdown .dropdown ul {
    left: -90%;
  }
  .navbar .dropdown .dropdown:hover > ul {
    left: -100%;
  }
}

/**
* Mobile Navigation 
*/
.mobile-nav-toggle {
  color: #fff;
  font-size: 28px;
  cursor: pointer;
  display: none;
  line-height: 0;
  transition: 0.5s;
}

@media (max-width: 991px) {
  .mobile-nav-toggle {
    display: block;
  }

  .navbar ul {
    display: none;
  }
}
.navbar-mobile {
  position: fixed;
  overflow: hidden;
  top: 0;
  right: 0;
  left: 0;
  bottom: 0;
  background: rgba(43, 111, 142, 0.9);
  transition: 0.3s;
  z-index: 2;
}
.navbar-mobile .mobile-nav-toggle {
  position: absolute;
  top: 15px;
  right: 15px;
}
.navbar-mobile ul {
  display: block;
  position: absolute;
  top: 55px;
  right: 15px;
  bottom: 15px;
  left: 15px;
  padding: 10px 0;
  background-color: #fff;
  overflow-y: auto;
  transition: 0.3s;
}
.navbar-mobile a,
.navbar-mobile a:focus {
  padding: 10px 20px;
  font-size: 15px;
  color: #2f4d5a;
}
.navbar-mobile a:hover,
.navbar-mobile .active,
.navbar-mobile li:hover > a {
  color: #67b0d1;
}
.navbar-mobile .getstarted,
.navbar-mobile .getstarted:focus {
  margin: 15px;
}
.navbar-mobile .dropdown ul {
  position: static;
  display: none;
  margin: 10px 20px;
  padding: 10px 0;
  z-index: 99;
  opacity: 1;
  visibility: visible;
  background: #fff;
  box-shadow: 0px 0px 30px rgba(127, 137, 161, 0.25);
}
.navbar-mobile .dropdown ul li {
  min-width: 200px;
}
.navbar-mobile .dropdown ul a {
  padding: 10px 20px;
}
.navbar-mobile .dropdown ul a i {
  font-size: 12px;
}
.navbar-mobile .dropdown ul a:hover,
.navbar-mobile .dropdown ul .active:hover,
.navbar-mobile .dropdown ul li:hover > a {
  color: #67b0d1;
}
.navbar-mobile .dropdown > .dropdown-active {
  display: block;
}
#header {
  transition: all 0.5s;
  z-index: 997;
  transition: all 0.5s;
  padding: 24px 0;
  background: rgba(103, 176, 209, 0.8);
}
#header.header-transparent {
  background: transparent;
}
#header.header-scrolled {
  background: rgba(55, 142, 181, 0.9);
  padding: 12px;
}
#header .logo h1 {
  font-size: 24px;
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.267);
  margin: 0;
  line-height: 1;
  font-weight: 700;
  letter-spacing: 1px;
  font-family: "Poppins", sans-serif;
  text-transform: uppercase;
}
#header .logo h1 a,
#header .logo h1 a:hover {
  color: #fff;
  text-decoration: none;
}
#header .logo img {
  padding: 0;
  margin: 0;
  max-height: 40px;
}
@media (max-width: 768px) {
  #header.header-scrolled {
    padding: 15px 0;
  }
} -->
</style>
