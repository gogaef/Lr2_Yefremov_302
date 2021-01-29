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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class NewInsurance implements Serializable{
    
    public String createIsurance(String idAgent, String price)
    {
        try {
            Map<String,Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            ManagerQuery q = (ManagerQuery)session.get("ManagerQuery");
            ResultSet agent =  q.selectWhereOne("Agent","id",idAgent );
            Integer summ = new Integer(price);
            Integer idClient = new Integer((String)session.get("id"));
            Float agentProcent = new Float(agent.getFloat(5)*summ );

            if(q.insertAgreement(idClient,new Integer(idAgent),summ,agentProcent )==1)
           {
            
                return "result"; 
           }
            
        }  
         catch (SQLException ex) {
            Logger.getLogger(NewInsurance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "ErrorNotFound";
    }
}
