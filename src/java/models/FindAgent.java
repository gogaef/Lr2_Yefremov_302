
package models;

import classes.Agent;
import classes.ManagerQuery;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class FindAgent implements Serializable {
    
    private List<Agent> agents;
    
    public List<Agent> getAgents()
    {
        return this.agents;
    }
    
    public void setAgents(ResultSet result)
    {
        agents= new ArrayList<Agent>();
        try {
            while(result.next())
            {
                agents.add(new Agent(result.getString(1),result.getString(2),result.getString(4),result.getString(5),result.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FindAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String searchAgent(String fromAge, String toAge, String fromExp, String toExp, String fromProc, String toProc)
    {
         String column1 = "age";
        String column2 = "expirience";
        String column3 = "procent";
        
        String value1 = "BETWEEN " + fromAge + " AND " + toAge;
        String value2 = "BETWEEN " + fromExp + " AND " + toExp;
        String value3 = "BETWEEN " + fromProc + " AND " + toProc;
        
        ManagerQuery q = (ManagerQuery)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ManagerQuery");
        ResultSet result = q.selectWhereThree("Agent", column1, value1, column2, value2, column3, value3);
        
        setAgents(result);
        
        return "ResultAgent";
    }
}
