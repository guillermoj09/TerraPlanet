<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="cl.samtech.sgomt.object.UsuarioLogin"%>
<%UsuarioLogin usuario=(UsuarioLogin)request.getSession().getAttribute("usuario"); %>

<a href="dashboard" class="navbar-brand">

<c:if test="${usuario.getUsername() ne 'simersamtech'}">
	SGOMT
</c:if>
<c:if test="${usuario.getUsername() == 'simersamtech'}">
	INICIO
</c:if>
</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-reorder"></i>
                </button>

 
  