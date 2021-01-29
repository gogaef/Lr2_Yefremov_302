/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import classes.ManagerQuery;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class ChangeAgent implements Serializable{
    
    public String changeAgent(String idAgreement, String idNewAgent) 
    {
        try
        {
            Map<String,Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            ManagerQuery q = (ManagerQuery)session.get("ManagerQuery");
            ResultSet agreement = q.selectWhereOne("Agreement", "id", idAgreement);
            
            Integer summa = new Integer (agreement.getInt(4));
            
            ResultSet newAgent = q.selectWhereOne("Agent", "id", idNewAgent);

            Float procentNewAgent = new Float(newAgent.getFloat(5)*summa );
            
            q.updateAgent(newAgent.getInt(1), procentNewAgent, agreement.getInt(1));
        
            return "result";
        }
        catch(Exception ex)
        {
            return "ErrorNotFound";
        } 
      
    }
}
