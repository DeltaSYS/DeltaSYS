package controllers;

import deltasys.model.DsPerfilesReglamento;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DsPerfilesReglamentoController extends HttpServlet {
    
    
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
            JSONArray ja = new JSONArray();
            
            if(action.equals("select"))
            {     
                String oid = jsonObject.getString("oid");   
                
                DsUsuarios usuario = facade.getDsUsuariosFindUsuario(oid);
              
                int id_perfil = usuario.getDsPerfiles().getId_perfil();
                List<DsPerfilesReglamento> perfilesReglamento = facade.getDsPerfilesReglamentoFindReglamentoPerfil(id_perfil);
                
                for (int i=0;i<perfilesReglamento.size();i++) 
                {    
                    JSONObject obj = new JSONObject();
                    
                    DsPerfilesReglamento perfilReglamento = perfilesReglamento.get(i);
                    
                    obj.put("id_articulo", perfilReglamento.getId_articulo());                       
                    obj.put("id_fraccion", perfilReglamento.getId_fraccion());                     
                    obj.put("id_inciso", perfilReglamento.getId_inciso());                      
                    obj.put("descripcion", perfilReglamento.getDsReglamento().getDescripcion());          
                    obj.put("salarios", perfilReglamento.getDsReglamento().getNum_salarios());   
                
                    ja.add(obj);  
                }                             
              
              resultado = ja.toString();
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
