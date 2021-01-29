
package classes;

public class Agent {
    
    private String idAgent;
    private String name;
    private String age;
    private String procent;
    private String raiting;
    
    public String getId()
    {
        return idAgent;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAge()
    {
        return age;
    }
    
    public String getProcent()
    {
        return procent;
    }
    
    public String getExperience()
    {
        return raiting;
    }
    
    public Agent()
    {
        
    }
    public Agent(String id, String name, String age, String procent, String raiting)
    {
        this.idAgent=id;
        this.name=name;
        this.age=age;
        this.procent=procent;
        this.raiting=raiting;
    }
    
    
}
