<!DOCTYPE html>
<html>
	<head>
		<!-- Title -->
		<title>Teeworlds Matchmaking System</title>

		<!-- CSS -->
		<!-- Common -->
		<link rel="stylesheet" type="text/css" href="<@spring.url "/resources/css/common.css" />" >
		<!-- Custom -->
		<link rel="stylesheet" type="text/css" href="<@spring.url "/resources/css/" + pageName + "/" + pageName + ".css" />" > 

		<!-- Bootstrap -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
	</head>

	<body>
		<#include "/" + pageName + "/content.ftl" />

		<!-- Load js at end -->
		<#include "/common/js.ftl" />
		<@include template="/" + pageName + "/js.ftl" />
		<!-- Common -->
		<script src="<@spring.url "/resources/js/common.js"/>" ></script>
		<!-- Custom -->
		<script src="<@spring.url "/resources/js/" + pageName + "/" + pageName + ".js" />" ></script>
	</body>
</html> 
