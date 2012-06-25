package controllers;

import java.sql.Timestamp;

import deltasys.model.DsIntervalo;
import deltasys.model.DsUsuarios;
import deltasys.model.JavaServiceFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class DsIntervaloController extends HttpServlet
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
            
            if(action.equals("update"))
            { 
                
                List<DsIntervalo> dsintervalos = facade.getDsIntervaloFindAll();
                
                int intervalo = jsonObject.getInt("intervalo");                
                String oid = jsonObject.getString("oid");
                DsUsuarios dsusuario = facade.getDsUsuariosFindUsuario(oid);
                String fecha = jsonObject.getString("fecha");
                  
                if(dsintervalos.size()==1)
                {
                    DsIntervalo dsintervalo = dsintervalos.get(0);  
                    dsintervalo.setIntervalo(intervalo);                  
                    dsintervalo.setDsUsuarios(dsusuario);                  
                    dsintervalo.setFecha_hora_modificado(Timestamp.valueOf(fecha + " 00:00:00"));
                    //DsIntervalo dsintervalo = (DsIntervalo) JSONObject.toBean( jsonParameters, DsIntervalo.class );//Vaciamos   
                
                    facade.mergeDsIntervalo(dsintervalo);
                }
                else
                {
                    Timestamp tsfecha = Timestamp.valueOf(fecha + " 00:00:00");
                    DsIntervalo dsintervalo = new DsIntervalo(tsfecha,1,intervalo,dsusuario); 
                    //DsIntervalo dsintervalo = (DsIntervalo) JSONObject.toBean( jsonParameters, DsIntervalo.class );//Vaciamos   
                
                    facade.persistDsIntervalo(dsintervalo);
                }
                
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
            
            if(action.equals("select"))
            {     
                
                List<DsIntervalo> dsintervalos = facade.getDsIntervaloFindAll();
                
                JSONObject obj = new JSONObject();
                
                if(dsintervalos.size()==1)
                {   
                    DsIntervalo dsintervalo = dsintervalos.get(0);
                    obj.put("intervalo", dsintervalo.getIntervalo());                    
                    obj.put("oid", dsintervalo.getDsUsuarios().getOid());                    
                    obj.put("responsable", dsintervalo.getDsUsuarios().getNombre());          
                    obj.put("fecha", dsintervalo.getFecha_hora_modificado());  
                }
                
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
