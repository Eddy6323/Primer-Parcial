
package com.emergentes.controlador;

import com.emergentes.modelo.Almacen;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainControlador", urlPatterns = {"/MainControlador"})
public class MainControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession ses = request.getSession();
        
        if (ses.getAttribute("listalm") == null) {
            ArrayList<Almacen> listaux = new ArrayList<Almacen>();
            ses.setAttribute("listalm", listaux);
        }
        ArrayList<Almacen> lista = (ArrayList<Almacen>) ses.getAttribute("listalm");
        String op = request.getParameter("op");
        String opcion = (op != null)? request.getParameter("op") : "view";
        
        Almacen alm = new Almacen();
        int id, pos;
        
        switch(opcion){
            case "nuevo": // insertar nuevo registro
                request.setAttribute("miAlmacen", alm);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "editar": //modificar registro
                id = Integer.parseInt(request.getParameter("id"));
                pos = BuscarIndice(request, id);
                alm = lista.get(pos);
                request.setAttribute("miAlmacen", alm);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar": //Eliminar registro
                pos = BuscarIndice(request, Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                ses.setAttribute("listalm", lista);
                response.sendRedirect("index.jsp");
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>) ses.getAttribute("listalm");
        
        Almacen alm = new Almacen();
        
        alm.setId(Integer.parseInt(request.getParameter("id")));
        alm.setDescripcion(request.getParameter("descripcion"));
        alm.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        alm.setPrecio((float) Double.parseDouble(request.getParameter("precio")));
        alm.setCategoria(request.getParameter("categoria"));
       
        int idt = alm.getId();
        
        if (idt == 0) {
            int ultimID;
            ultimID = UltimoId(request);
            alm.setId(ultimID);
            lista.add(alm);
        }else{
            lista.set(BuscarIndice(request, idt), alm);
        }
        ses.setAttribute("listalm", lista);
        response.sendRedirect("index.jsp");
    }

   private int UltimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>) ses.getAttribute("listalm");
        
        int idaux =0;
        for (Almacen item : lista) {
            idaux = item.getId();
        }
        return idaux +1;
    }
    
    private int BuscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>) ses.getAttribute("listalm");
        
        int i =0;
        if (lista.size() > 0) {
            while ( i < lista.size()){
                if (lista.get(i).getId() == id) {
                    break;
                }else{
                    i++;
                }
            }
        }
        return i;
    }
}
