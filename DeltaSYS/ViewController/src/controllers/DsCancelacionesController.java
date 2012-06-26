package controllers;

import deltasys.model.DsCancelaciones;
import deltasys.model.DsInfracciones;
import deltasys.model.DsIntervalo;
import deltasys.model.DsUsuarios;
import deltasys.model.JavaServiceFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class DsCancelacionesController extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
          
        // Configuración del servlet
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");    
        //-------------------
        
        PrintWriter out = response.getWriter();
        
        //Objetos para leer la cadena que proviene del JS/JSP (peticion de ajax usando Jquery)
        StringBuffer jb = new StringBuffer();
        BufferedReader reader = request.getReader();    
        String resultado="";
        String line = null;
        //------------------- 
        
        try 
        {
            JavaServiceFacade facade= new JavaServiceFacade();
            
            //Lee la cadena de datos        
            while ((line = reader.readLine()) != null)
            {
              jb.append(line);
            }
            
            if(jb.length()==0)
            {
                jb.append(request.getParameter("data"));
            }
               
          //Lee y recupera el objeto JSON       
            
            String input=jb.toString();
            JSONObject jsonObject = JSONObject.fromObject(input);//Transformamos la cadena de datos a un objeto JSON
            
            String action = jsonObject.getString("action");
            
            if(action.equals("insert"))
            { 
                
                Long id_folio = jsonObject.getLong("id_folio");                
                String folio_oficio = jsonObject.getString("folio_oficio");
                String fecha = jsonObject.getString("fecha");
                DsInfracciones dsInfracciones = facade.getDsInfraccionesFindInfraccion(id_folio);
                  
                Date dfecha = Date.valueOf(fecha);
                
                DsCancelaciones dscancelaciones = new DsCancelaciones(dfecha,folio_oficio, dsInfracciones);
                //DsCancelaciones dscancelaciones = (DsCancelaciones) JSONObject.toBean( jsonParameters, DsCancelaciones.class );//Vaciamos   
            
                facade.persistDsCancelaciones(dscancelaciones);
                
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
            
            if(action.equals("select"))
            {     
                
                Long id_folio = jsonObject.getLong("id_folio");
                List<DsCancelaciones> dscancelaciones = facade.getDsCancelacionesFindFolioCancelado(id_folio);
                
                JSONObject obj = new JSONObject();
                
                if(dscancelaciones.size()==0)
                    obj.put("cancelado", "no");    
                else
                    obj.put("cancelado", "si");    
                
                resultado = obj.toString();
                System.out.println(resultado);
            }
            
            
            
        } catch (Exception e) {
           System.out.println("Error: "+e.toString());
           
            JSONObject obj = new JSONObject(); 
            obj.put("error",e.toString());
            resultado = obj.toString();
        } finally {
          out.println(resultado);//LA SALIDA DEL SERVLET SIEMPRE DEBE UBICARSE DENTRO DEl finally
          out.close();
        } 
          
      }
      
      // Redirigimos las peticiones POST a las GET para manejarlas de la misma forma
      protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          doGet(req,res);
        }   
}
