package controllers;

import deltasys.model.DsInfracciones;
import deltasys.model.DsReglamento;
import deltasys.model.JavaServiceFacade;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.net.URL;
import java.net.URLConnection;

import java.sql.Timestamp;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import webservices.Base64;

public class DSInfraccionesController extends HttpServlet
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
            JSONObject jsonParameters = jsonObject.getJSONObject("parameters");      
              
            if(action.equals("insert"))
            { 
                
                DsInfracciones dsinfracciones = new DsInfracciones();
                
                int id_folio = jsonObject.getInt("folio");                 
                String id_oid = jsonObject.getString("oid");                 
                String num_placa = jsonObject.getString("num_placa");                 
                String fecha = jsonObject.getString("fecha");      
                Timestamp fecha_hora = Timestamp.valueOf(fecha);
                BigDecimal latitud = new BigDecimal(jsonObject.getString("latitud"));                 
                BigDecimal longitud = new BigDecimal(jsonObject.getString("longitud")); 
                String descripcion = jsonObject.getString("descripcion");
                
                String nom_infractor= jsonObject.getString("nom_infractor");
                String domicilio = jsonObject.getString("domicilio");
                String licencia = jsonObject.getString("licencia");
                
        
                String sfoto_frontal = jsonObject.getString("foto_frontal");
                String sfoto_lateral = jsonObject.getString("foto_lateral");
                String sfoto_trasera = jsonObject.getString("foto_trasera");
                
                savePhoto(sfoto_frontal,"frontal",id_folio);                
                savePhoto(sfoto_lateral,"lateral",id_folio);                
                savePhoto(sfoto_trasera,"trasera",id_folio);
                
                int id_articulo = jsonObject.getInt("id_articulo");
                String id_fraccion = jsonObject.getString("id_fraccion");
                String id_inciso = jsonObject.getString("id_inciso");
                DsReglamento dsReglamento = facade.getDsReglamentoFindReglamento(id_articulo, id_fraccion, id_inciso);
                
                BigDecimal monto = new BigDecimal(jsonObject.getString("monto"));   
                
                dsinfracciones = new DsInfracciones(descripcion, domicilio, fecha_hora, id_folio, dsReglamento, id_oid, latitud, licencia, longitud, monto, nom_infractor, num_placa);
        
                // SETRAVI -------------------
                
                JSONObject jsonSetravi = getDatosSetravi(num_placa);
                
                if(jsonSetravi!=null)
                {
                    if(nom_infractor.length()==0)
                        dsinfracciones.setNom_infractor(jsonSetravi.getString("nombre"));
                    if(domicilio.length()==0)
                        dsinfracciones.setDomicilio(jsonSetravi.getString("direccion"));
                    if(licencia.length()==0)
                        dsinfracciones.setLicencia(jsonSetravi.getString("licencia"));
                }
                    
                notificaSetravi(dsinfracciones,fecha);  
                
                // ---------------------------
                
                facade.persistDsInfracciones(dsinfracciones);
                
                JSONObject json = new JSONObject();        
                json.put("id", "");
                resultado = json.toString();
            }
            
            if(action.equals("select"))
            {     
                Long id_folio = jsonObject.getLong("id_folio");              
                String num_placa = jsonObject.getString("num_placa");
                
                List<DsInfracciones> infracciones = facade.getDsInfraccionesFindInfracciones(id_folio,num_placa);
            
                            
              JSONArray ja = new JSONArray();
              for (int i=0;i<infracciones.size();i++) 
              {    
                  JSONObject obj = new JSONObject();
                  
                  try 
                  {
                      DsInfracciones inf = infracciones.get(i);
                      
                      int id_articulo = inf.getDsReglamento().getId_articulo();
                      String id_fraccion = inf.getDsReglamento().getId_fraccion();
                      String id_inciso = inf.getDsReglamento().getId_inciso();
            
                      obj.put("id_folio", inf.getId_folio() );
                      obj.put("fecha", inf.getFecha_hora() );
                      obj.put("num_placa", inf.getNum_placa() );
                      obj.put("fundamento_articulo", id_articulo );
                      obj.put("fundamento_fraccion", id_fraccion );
                      obj.put("fundamento_inciso", id_inciso );                      
                      obj.put("fundamento_sancion", inf.getDsReglamento().getNum_salarios() );
                      
                      DsReglamento reglamento = facade.getDsReglamentoFindReglamento(id_articulo,"","");
                      String descMotivo = reglamento.getDescripcion();              
                      reglamento = facade.getDsReglamentoFindReglamento(id_articulo,id_fraccion,"");
                      descMotivo += reglamento.getDescripcion();                    
                      reglamento = facade.getDsReglamentoFindReglamento(id_articulo,id_fraccion,id_inciso);
                      descMotivo += reglamento.getDescripcion();
                      
                      obj.put("motivo", descMotivo );
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
      
    void savePhoto(String sfoto, String cve_foto, int id_folio)
    {   
        
        try
        {

            byte[] bfoto =  Base64.decode(sfoto);
            
            String sfile = String.valueOf(id_folio)+"_"+cve_foto+".jpg"; // idfolio_cveFoto.jpg
            File file = new File(sfile);
        
            FileOutputStream fstream = new FileOutputStream(file);
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(fstream));
            
            out.write(bfoto);
            
            out.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally
        {
            System.out.println("¡Foto guardada!");      
        }
    }
    
    void notificaSetravi(DsInfracciones dsinfracciones, String fecha)
    {
        try
        {
            String datos = "";
            JSONObject jsonObject = new JSONObject();
            
            jsonObject.put("action", "insert");
            jsonObject.put("licencia", dsinfracciones.getLicencia() );
            jsonObject.put("motivo", dsinfracciones.getDescripcion() );
            jsonObject.put("fecha_hora", fecha );

            URL setraviController = new URL("http://localhost:7101/DeltaSYS/SetraviService?data="+jsonObject.toString());
            URLConnection setraviconnetion = setraviController.openConnection();
            
            BufferedReader in = new BufferedReader(
                                   new InputStreamReader(
                                   setraviconnetion.getInputStream(),"UTF-8"));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                datos += inputLine;
            in.close();
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    JSONObject getDatosSetravi(String num_placa)
    {
        JSONObject jsonDatosSetravi = new JSONObject();
        
        try
        {
            String datos = "";
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("action", "select");
            jsonObject.put("num_placa", num_placa );

            URL setraviController = new URL("http://localhost:7101/DeltaSYS/SetraviService?data="+jsonObject.toString());
            URLConnection setraviconnetion = setraviController.openConnection();
            
            BufferedReader in = new BufferedReader(
                                   new InputStreamReader(
                                   setraviconnetion.getInputStream(),"UTF-8"));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                datos += inputLine;
            in.close();
            
            jsonDatosSetravi = JSONObject.fromObject(datos);
             
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        finally
        {
            return jsonDatosSetravi;    
        }
    }
    
}
