package newpackage;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;



@WebService(serviceName = "NewWebService")
public class NewWebService {


    @WebMethod(operationName = "Buscar")
    public String Buscar(@WebParam(name = "name") String txt) {
        EntityManager em;
        EntityManagerFactory emf;

        
        emf = javax.persistence.persistence.createEntityManagerFactory("ISO815LAB5PU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        //-Buscar-------------------------
        ProductCode c = em.find(ProductCode.class,txt.toUpperCase());
        if(c != null){
        txt = (c.getDescription());
        }else{
            txt = "No se encontro el registro";
            }


        return "Resultado Busqueda " + txt + " !";
    }
}