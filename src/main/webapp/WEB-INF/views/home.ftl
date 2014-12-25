<#assign pageName="home"/>
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- Bootstrap -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

		<!-- Common -->
		<script src="<@spring.url "/resources/js/common.js"/>" ></script>
		<!-- Static Page -->
		<script>
			<#include "/" + pageName + "/js.ftl" />
		</script>
		<!-- Custom -->
		<script src="<@spring.url "/resources/js/" + pageName + "/" + pageName + ".js" />" ></script>
	</body>
</html> 
