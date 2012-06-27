package webservices;

import setravi.model.JavaServiceFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import setravi.model.SetraviDatos;
import setravi.model.SetraviPuntos;

public class SetraviService extends HttpServlet 
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
                
                String licencia = jsonObject.getString("licencia");
                String motivo = jsonObject.getString("motivo");
                String sfecha_hora = jsonObject.getString("fecha_hora");
                Timestamp fecha_hora = Timestamp.valueOf(sfecha_hora);
                
                SetraviPuntos setravipuntos = new SetraviPuntos(fecha_hora,licencia,motivo);
                  
                //SetraviPuntos setravipuntos = (SetraviPuntos) JSONObject.toBean( jsonParameters, SetraviPuntos.class );//Vaciamos   
                
                facade.persistSetraviPuntos(setravipuntos);
                  
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
              
            if(action.equals("select"))
            { 
              String num_placa = jsonObject.getString("num_placa");
              JSONObject obj = new JSONObject();
              
              try 
              {
                  SetraviDatos setravidatos= facade.getSetraviDatosFindDatosPlaca(num_placa);
                  
                  obj.put("num_placa", setravidatos.getNum_placa() );
                  obj.put("licencia", setravidatos.getLicencia() );
                  obj.put("nombre", setravidatos.getNombre() );
                  obj.put("direccion", setravidatos.getDireccion() );
                  
              } 
              catch (Exception e) 
              {
                  System.out.println("Error: "+e.toString());
              }
              //espaux.add(obj);
                                      
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
