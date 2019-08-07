<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Print Page</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	<style>
		#content {
			width: 50%;
		}
		#piecanvas {
			width: 80%;
		}
	</style>
</head>
<body>
<div id="header">
	<h1>
		Frequency Table
	</h1>
</div>
<div id="content">
	<div id="textdisplay">
		<table border="1">
			<thead>
				<th>Bins</th>
				<th>Number of objects in bin</th>
			</thead>
			<tbody>
				<%	
					/*
						Learned a very simple way to display data in JSP using an iterator.
						https://medium.com/@kasunpdh/sample-java-web-application-using-servlets-and-jsp-5621cad2f582
					*/
					
					/*
						Adapted iterator implementation for display of the frequency table
						from https://www.geeksforgeeks.org/iterate-map-java/
					*/
					Map<Double, Integer> frequencyTable = (Map<Double, Integer>) request.getAttribute("frequencytable");
	
					for (Map.Entry<Double,Integer> entry : frequencyTable.entrySet()) {
						out.println("<tr><td>" + entry.getKey() + "</td><td>" + entry.getValue() + "</td></tr>");
					}
			
				%>
			</tbody>
		</table>
	</div>
	<div id="piecanvas">
		<canvas id="donutchart" width="400" height="400">
		</canvas>
	</div>
</div>
<script>
	var ctx = document.getElementById("donutchart");
	var data = {
			datasets: [{
				data: ${values},
				backgroundColor: ${colors}
			}],
			labels: ${keys}
			
	};
	var donutchart = new Chart(ctx, {
		type: 'doughnut',
		data: data,
		options: Chart.defaults.doughnut
	});
</script>
</body>
</html>