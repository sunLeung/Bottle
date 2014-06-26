<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>bottle</title>

    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/bootstrap/bootstrap-responsive.min.css" rel="stylesheet"/>
    <link href="/css/bootstrap/datetimepicker.css" rel="stylesheet"/>
    <link href="/css/component.css" rel="stylesheet"/>
    <link href="/css/main.css" rel="stylesheet"/>
    <!--[if lte IE 6]>
	  <link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-ie6.min.css">
	  <link rel="stylesheet" type="text/css" href="/css/bootstrap/ie.css">
    <![endif]-->