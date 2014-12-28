<!DOCTYPE html>
<html>
	<head>
		<!-- Content type -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<!-- Title -->
		<title>Teeworlds Matchmaking System</title>

		<!-- CSS -->
		<!-- Common -->
		<link rel="stylesheet" type="text/css" href="<@spring.url "/resources/css/common.css" />" >
		<!-- Custom -->
		<link rel="stylesheet" type="text/css" href="<@spring.url "/resources/css/" + pageName + "/page.css" />" > 

		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	</head>

	<body>
		<!-- Header -->
		<div class="website-header">
			<h1>TeeFun</h1>
		</div>

		<!-- Page content -->
		<#include "/" + pageName + "/content.ftl" />

		<!-- Footer -->
		<p class="website-footer">
			TeeFun - Copyright (C) Rajh &amp; Choupom<br/>
			Contact us on IRC (#teefun @ irc.quakenet.org)
		</p>

		<!-- Load js at end -->
		<#include "/common/js.ftl" />
		<@include template="/" + pageName + "/js.ftl" />
		<!-- Common -->
		<script src="<@spring.url "/resources/js/common.js"/>" ></script>
		<!-- Custom -->
		<script src="<@spring.url "/resources/js/" + pageName + "/page.js" />" ></script>
	</body>
</html> 
