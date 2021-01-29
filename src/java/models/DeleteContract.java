package models;

import classes.ManagerQuery;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class DeleteContract implements Serializable {
    public String deleteAgreement(String idAgreement)
    {
            Map<String,Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            ManagerQuery q = (ManagerQuery)session.get("ManagerQuery");
            int id = new Integer(idAgreement);
            q.DeleteAgreement(id);
            
            return "result";
    }
}
