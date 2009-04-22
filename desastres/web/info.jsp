<%@ page contentType="text/html"  pageEncoding="UTF-8"%>
<%@ page language = "java"  %>
<%@ page import="java.sql.*" %>
<%@ page isELIgnored = "false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %> 
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   

<fmt:bundle basename="fmt.eji8n">
    
    <%@ include file="database.jspf" %>
       
    <sql:query var="eventos" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE marcador = 'event' AND estado != 'erased'  ;">
    </sql:query> 
    
    <sql:query var="controlled" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE marcador = 'event' AND estado = 'controlled'  ;">
    </sql:query>  
    
    <sql:query var="fires" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'fire' AND estado != 'erased'  ;">
    </sql:query> 
    <sql:query var="floods" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'flood' AND estado != 'erased'  ;">
    </sql:query> 
    <sql:query var="collapses" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'collapse' AND estado != 'erased'  ;">
    </sql:query> 
    <sql:query var="resources" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE marcador = 'resource' AND estado != 'erased'  ;">
    </sql:query> 
    
    <sql:query var="policemen" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'police' AND estado != 'erased'  ;">
    </sql:query> 
    
    <sql:query var="firemen" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'firemen' AND estado != 'erased'  ;">
    </sql:query> 
    
    <sql:query var="ambulance" dataSource="${CatastrofesServer}" sql="
               SELECT * FROM CATASTROFES WHERE tipo = 'ambulance' AND estado != 'erased'  ;">
    </sql:query> 
    

    <p><fmt:message key="actualmentehay"/> ${eventos.rowCount} <fmt:message key="desastres"/>. (${controlled.rowCount} <fmt:message key="controlados"/>):
        <ul><li><img src="markers/fuego.png" height="25px"> ${fires.rowCount} <fmt:message key="incendios"/> </li>
            <li><img src="markers/agua.png" height="25px"> ${floods.rowCount} <fmt:message key="inundaciones"/></li>
        <li><img src="markers/casa.png" height="30px"> ${collapses.rowCount} <fmt:message key="derrumbamientos"/></li></ul><br>
        
        <fmt:message key="hay"/> ${resources.rowCount} <fmt:message key="recursostrabajando"/>
        <ul><li><img src="markers/policia${policemen.rowCount}.png" height="25px"> ${policemen.rowCount} <fmt:message key="policias"/></li>
            <li><img src="markers/bombero${firemen.rowCount}.png" height="25px"> ${firemen.rowCount} <fmt:message key="bomberos"/></li>
        <li><img src="markers/ambulancia${ambulance.rowCount}.png" height="25px"> ${ambulance.rowCount} <fmt:message key="ambulancias"/></li></ul>
        
        <br><br>
        <a id="hideVisualize" href="#"><fmt:message key="ocultar"/></a>
        
        
    </p>
    
    
</fmt:bundle>

