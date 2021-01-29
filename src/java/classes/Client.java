package classes;


public class Client 
{
    private String idClient;
    private String name;
    private String password;
    private String age;
    
    public Client()
    {
        
    }
     public Client(String id,String name, String password, String age )
    {
        this.idClient=id;
        this.name=name;
        this.password=password;
        this.age=age;
    }
}