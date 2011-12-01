<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@page import="java.sql.Timestamp, java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>

<%@include file="../jspf/database.jspf"%>
<%
	long fecha = new Date().getTime();
	String hace5min = "'" + new Timestamp(fecha - 300000).toString() + "'"; // 300000 = 5m*60s*1000ms
	String hace30min = "'" + new Timestamp(fecha - 1800000).toString() + "'"; // 1800000 = 30m*60s*1000ms
	// String hace12horas = "'" + new Timestamp(fecha - 43200000).toString() + "'"; // 43200000 = 12h*60m*60s*1000ms
%>

<c:if test="${param.action == 'firstTime'}">
	<c:choose>
		<c:when test="${basedatos == 'mysql'}">
			<sql:query var="mensajes" dataSource="${CatastrofesServer}">
				SELECT * FROM (
					(SELECT * FROM mensajes WHERE nivel <= ? AND fecha >= <%=hace5min%>)
					UNION
					(SELECT * FROM mensajes
					 WHERE nivel <= ? AND fecha < <%=hace5min%> AND fecha >= <%=hace30min%>
					 ORDER BY fecha DESC
					 LIMIT 5)) msgs
				ORDER BY id ASC
				<sql:param value="${param.nivel}"/>
				<sql:param value="${param.nivel}"/>
			</sql:query>
		</c:when>
		<c:otherwise>
			<sql:query var="mensajes" dataSource="${CatastrofesServer}">
				SELECT * FROM (
					(SELECT * FROM mensajes WHERE nivel <= ? AND fecha >= <%=hace5min%>)
					UNION
					(SELECT TOP 5 * FROM mensajes
					 WHERE nivel <= ? AND fecha < <%=hace5min%> AND fecha >= <%=hace30min%>
					 ORDER BY fecha DESC))
				ORDER BY id ASC
				<sql:param value="${param.nivel}"/>
				<sql:param value="${param.nivel}"/>
			</sql:query>
		</c:otherwise>
	</c:choose>
	
</c:if>
<c:if test="${param.action == 'notFirst'}">
	<sql:query var="mensajes" dataSource="${CatastrofesServer}">
		SELECT * FROM mensajes
		WHERE nivel <= ? AND fecha > ?
		<sql:param value="${param.nivel}"/>
		<sql:param value="${param.fecha}"/>
	</sql:query>
</c:if><c:if test="${param.action == 'idCreado'}">
	<sql:query var="mensajes" dataSource="${CatastrofesServer}">
		SELECT id FROM catastrofes
		WHERE fecha = ?
		<sql:param value="${param.fecha}"/>
	</sql:query>
</c:if>

<json:array>
	<c:forEach var="mensaje" items="${mensajes.rows}">
		<json:object>
			<json:property name="id" value="${mensaje.id}"/>
			<json:property name="creador" value="${mensaje.creador}"/>
			<json:property name="mensaje" value="${mensaje.mensaje}"/>
			<json:property name="nivel" value="${mensaje.nivel}"/>
			<json:property name="fecha" value="${mensaje.fecha}"/>
		</json:object>
	</c:forEach>
</json:array>