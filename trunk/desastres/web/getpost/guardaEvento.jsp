<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored = "false" %>
<%@ page import="java.sql.Timestamp, java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ include file="database.jspf" %>
<% String modif = "'" + new Timestamp(new Date().getTime()).toString() + "'"; %>

<c:catch var="errorUpdate">
	<sql:update dataSource="${CatastrofesServer}">
		INSERT INTO catastrofes(marcador, tipo, cantidad, nombre, descripcion, info, latitud,
			longitud, direccion, estado, size, traffic, idAssigned, fecha, usuario, planta)
		VALUES(?,?,?,?,?,?,?,?,?,(SELECT id FROM tipos_estados WHERE tipo_estado = ?),?,?,?,<%=modif%>,?,?)
		<sql:param value="${param.marcador}"/>
		<sql:param value="${param.tipo}"/>
		<sql:param value="${param.cantidad}"/>
		<sql:param value="${param.nombre}"/>
		<sql:param value="${param.descripcion}"/>
		<sql:param value="${param.info}"/>
		<sql:param value="${param.latitud}"/>
		<sql:param value="${param.longitud}"/>
		<sql:param value="${param.direccion}"/>
		<sql:param value="${param.estado}"/>
		<sql:param value="${param.size}"/>
		<sql:param value="${param.traffic}"/>
		<sql:param value="${param.idAssigned}"/>
		<sql:param value="${param.usuario}"/>
		<sql:param value="${param.planta}"/>
	</sql:update>
	
	<%-- Uncomment this code in order to enable Twitter service for new disasters.
	DO NOT FORGET TO INSERT YOUR TWITTER LOGIN AND PASSWORD IN THE TWITTER CONSTRUCTOR --%>
	<%--
		Twitter twitt = new Twitter("your_username_here","your_password_here");
		StreetLocator st = new StreetLocator();
		double lat = Double.parseDouble(request.getParameter("latitud"));
		double lon = Double.parseDouble(request.getParameter("longitud"));
		String msg = "New ";
		msg += request.getParameter("tipo");
		msg += " at ";
		msg +=st.getAddress(lat, lon);
		msg += " on ";
		msg += new Date();
		msg += " Size: " + request.getParameter("size");
		if( msg.length() > 140){
		    msg = msg.substring(0, 134) +"(...)";
		}
		twitt.updateStatus(msg);
	--%>
</c:catch>
ok