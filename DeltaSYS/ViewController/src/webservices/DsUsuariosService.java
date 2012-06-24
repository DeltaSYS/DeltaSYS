package webservices;

import deltasys.model.DsUsuarios;
import deltasys.model.JavaServiceFacade;
import java.util.List;
import net.sf.json.JSONObject;


public class DsUsuariosService 
{
    
    public DsUsuariosService() 
    {
        super();
    }
            
    public String jsonDsUsuario(String oid,String password)
    {   
        JSONObject obj = new JSONObject();
        
        try
        {
            JavaServiceFacade facade= new JavaServiceFacade();
            List<DsUsuarios> usuarios =null;       
            usuarios = facade.getDsUsuariosLogin(oid,password);
    
    
            obj.put("status","correcto");
            if(usuarios.size()>0) 
            {
                try 
                {
                    DsUsuarios usu= usuarios.get(0);
                    obj.put("oid", usu.getOid());
                    obj.put("nombre",usu.getNombre() );
                    obj.put("id_perfil", usu.getDsPerfiles().getId_perfil());
                }
                catch (Exception e)
                {
                    obj.put("status","error");  
                }
                
                 
            }   
            else
            {
                obj.put("status","fallido");    
            }
        }
        catch (Exception e)
        {
            obj.put("status","error");  
        }
        
        return obj.toString();
    }
}
