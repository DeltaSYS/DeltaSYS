package webservices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.net.URL;

import java.net.URLConnection;

import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.misc.IOUtils;

public class DsGeoCoding extends HttpServlet
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
            
            while ((line = reader.readLine()) != null)
            {
              jb.append(line);
            }
            
            if(jb.length()==0)
            {
                jb.append(request.getParameter("data"));
            }
            
            String input=jb.toString();
            JSONObject jsonObject = JSONObject.fromObject(input);//Transformamos la cadena de datos a un objeto JSON
            String action = jsonObject.getString("action");
            
            if(action.equals("select"))
            {
                
                String googleAPI = "";                
                String address = "";            
                JSONObject obj = new JSONObject(); 
                
                JSONArray objGoogleAPI = new JSONArray(); 
                JSONObject objDirection = new JSONObject(); 
    
                
                BigDecimal bdlat = new BigDecimal(jsonObject.getString("latitud"));            
                BigDecimal bdlng = new BigDecimal(jsonObject.getString("longitud"));
    
                DecimalFormat frmt = new DecimalFormat("00.000000");
                
                String lat = frmt.format(bdlat);            
                String lng = frmt.format(bdlng);
                
                
                URL googlemaps = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=true");
                URLConnection mapsconnetion = googlemaps.openConnection();
                BufferedReader in = new BufferedReader(
                                       new InputStreamReader(
                                       mapsconnetion.getInputStream(),"UTF-8"));
                String inputLine;
                
                while ((inputLine = in.readLine()) != null) 
                    googleAPI += inputLine;
                in.close();
                
                objGoogleAPI = JSONObject.fromObject(googleAPI).getJSONArray("results");
                
                if(objGoogleAPI.size()>0)
                {
                    objDirection = ((JSONObject)objGoogleAPI.get(0));
                    address = objDirection.getString("formatted_address");
                }
                else
                {
                    address = "dirección no disponible";
                }
                
                obj.put("address",address);
                resultado = obj.toString();
            }
            
        } catch (Exception e) {
           System.out.println("Error: "+e.toString());
           
            JSONObject obj = new JSONObject(); 
            obj.put("error",e.toString());
            resultado = obj.toString();
        } 
        finally 
        {
          out.println(resultado);//LA SALIDA DEL SERVLET SIEMPRE DEBE UBICARSE DENTRO DEl finally
          out.close();
        } 
          
      }
      
      // Redirigimos las peticiones POST a las GET para manejarlas de la misma forma
      protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          doGet(req,res);
        }   
    
}
