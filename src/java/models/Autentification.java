package models;

import classes.ManagerQuery;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.servlet.ServletException;
import org.jboss.weld.SimpleCDI;

@Named
@SessionScoped
public class Autentification implements Serializable{
    
    private String permission;
    
    public String getPermission()
    {
        return this.permission;
    }
    
    public String doPost(String userPermission, String password)
            throws ServletException, IOException
    {
        
        try {
            ManagerQuery q = new ManagerQuery("jdbc:sqlite://C:/sqlite/mySqLite.db");
            ResultSet result = q.select(userPermission);
            Map<String,Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            session.put("permission",null);
            while(result.next())
            {
                        
                if(result.getString(3).equals(password))
                {
                    session.put("ManagerQuery", q);
                    session.put("permission",userPermission);
                    session.put("userName",result.getString(2));
                    session.put("id", result.getString(1));
                    this.permission=userPermission;
                    break;
                }
            }
            
            if(session.get("permission")==null)
            {
                return "ErrorIdentification";
            }
            else
            {
                return "WelcomeUser";
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Autentification.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<SelectItem> getAgents() throws SQLException
    {
        List<SelectItem> listAgent = new ArrayList <SelectItem>();
        ManagerQuery q = (ManagerQuery) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ManagerQuery");
        ResultSet agents = q.select("Agent");
        while(agents.next())
        {
            SelectItem s=new SelectItem();
            s.setValue(agents.getString(1));
            s.setLabel(agents.getString(1));
            listAgent.add(s);
        }
        return listAgent;
    }
    
}
