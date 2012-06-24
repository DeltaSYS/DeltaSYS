package controllers;

import deltasys.model.DsPerfiles;
import deltasys.model.DsUsuarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import deltasys.model.JavaServiceFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DsUsuariosController extends HttpServlet{
    
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
            JSONObject jsonParameters = jsonObject.getJSONObject("parameters");      
              
            if(action.equals("insert")||action.equals("update"))
            { 
                String oid = jsonParameters.getJSONObject("Form").getString("oid");
                String password = jsonParameters.getJSONObject("Form").getString("password");
                Long id_perfil = jsonParameters.getJSONObject("Form").getLong("id_perfil");
                String nombre = jsonParameters.getJSONObject("Form").getString("nombre");
                  
                DsPerfiles perfiles = facade.getDsPerfilesFindPerfil(id_perfil);                
                DsPerfiles perfil = perfiles;
                
                DsUsuarios usuario = new DsUsuarios(perfil,oid,password,nombre);
                  
                //DsUsuarios usuario = (DsUsuarios) JSONObject.toBean( jsonParameters, DsUsuarios.class );//Vaciamos   
                
                if(action.equals("insert")) // insert
                    facade.persistDsUsuarios(usuario);
                else                        // update
                    facade.mergeDsUsuarios(usuario);
                  
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
              
            if(action.equals("delete"))
            { 
                String oid = jsonParameters.getJSONObject("Form").getString("oid");
                
                DsUsuarios usuario = facade.getDsUsuariosFindUsuario(oid);
                
                facade.removeDsUsuarios(usuario);
                
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
          
              if(action.equals("select"))
              { 
                  String nameQuery = jsonObject.getString("name");
                  List<DsUsuarios> usuarios=null;
                  
                  if(nameQuery.equals("findAll")){
                      usuarios=facade.getDsUsuariosFindAll();
                  }
                  if(nameQuery.equals("findCatalogo"))
                  {
                    usuarios=facade.getDsUsuariosCatalogo("DsUsuarios.findCatalogo",jsonParameters);
                  } 
                                
                  JSONArray ja = new JSONArray();
                  for (int i=0;i<usuarios.size();i++) 
                  {    
                      JSONObject obj = new JSONObject();
                      
                      try 
                      {
                          DsUsuarios usu= usuarios.get(i);

                          obj.put("oid", usu.getOid() );
                          obj.put("password", usu.getPassword() );
                          obj.put("id_perfil", usu.getDsPerfiles().getId_perfil() );
                          obj.put("nombre", usu.getNombre() );
                          
                      } 
                      catch (Exception e) 
                      {
                          System.out.println("Error: "+e.toString());
                      }
                      //espaux.add(obj);
                      
                      ja.add(obj);  
                  }                             
                  
                  resultado = ja.toString();
                  System.out.println(resultado);
              }
          
            
            if(action.equals("autentify"))
            { 
                List<DsUsuarios> usuarios =null;
                
                String oid = jsonObject.getString("oid");
                String password = jsonObject.getString("password");
                    
                usuarios = facade.getDsUsuariosLogin(oid,password);
                
                JSONArray ja = new JSONArray();
                JSONObject obj = new JSONObject();
                
                if(usuarios.size()>0) 
                {
                    try 
                    {
                        DsUsuarios usu= usuarios.get(0);
                        obj.put("oid", usu.getOid());
                        obj.put("user",usu.getNombre() );
                        obj.put("id_perfil", usu.getDsPerfiles().getId_perfil());
                        obj.put("perfil", usu.getDsPerfiles().getPerfil());
                        obj.put("loggedon", "yes");
                      
                        HttpSession session =request.getSession();
                        session.setAttribute("oid",usu.getOid());
                        session.setAttribute("user",usu.getNombre());
                        session.setAttribute("id_perfil",usu.getDsPerfiles().getId_perfil());
                      
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error: "+e.toString());
                    }
                    
                }    
                else
                {

                    obj.put("loggedon", "no");
                }
                
                ja.add(obj);  
                
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
