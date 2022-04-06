<%@page import="com.emergentes.modelo.Almacen"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<Almacen> lista =(ArrayList<Almacen>) session.getAttribute("listalm");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center">
            <table width="280" cellspacing="1" cellpadding="3" border ="1">
                <tr>
                <td><font  face="arial,verdana,helvetica">
                    PRIMER PARCIAL TEM-742<br>
                    Nombre: Eddy Marca Yujra<br>
                Carnet: 13789564
                </font>
                </td>
                </tr>
            </table>
        </div>
        <div>
        <h1>Productos</h1>
        <a href="MainControlador?op=nuevo">Nuevo Producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <%
            if (lista != null) {
                   for (Almacen item : lista) {     
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getPrecio()%></td>
                <td><%= item.getCategoria()%></td>
                <td><a href="MainControlador?op=editar&id=<%= item.getId()%>">Editar</a></td>
                <td><a href="MainControlador?op=eliminar&id=<%= item.getId()%>"
                       onclick='return confirm("Esta seguro de eliminar el Producto ?");'>Eliminar</a></td>
            </tr>
            <%
                }
                }
            %>
        </table>
        </div>
    </body>
</html>
