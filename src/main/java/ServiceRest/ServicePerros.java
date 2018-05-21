
package ServiceRest;

import Facade.FacadePerros;
import ModeloDTO.PerrosDTO;
import exception.ConexionException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Perros;

@Path("/perros")
public class ServicePerros {
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PerrosDTO registroPerros(PerrosDTO dog) throws ConexionException {
        
        FacadePerros facade = null;
        PerrosDTO dto = null;

        facade = new FacadePerros();
        Perros peJPA = new Perros();
        
        peJPA.setNombre(dog.getNombre());
        peJPA.setGrupo(dog.getGrupo());
        peJPA.setDescripcion(dog.getDescripcion());
        peJPA.setEstado(dog.isEstado());
        peJPA.setNivelEnergia(dog.getNivelEnergia());
        dog.setPersonalidad(dog.getPersonalidad());
        facade.save(peJPA);

        return dto;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PerrosDTO> getPerros_JSON() throws ConexionException {
        List<PerrosDTO> perroDTO = new ArrayList<PerrosDTO>();
        FacadePerros facade = null;

        facade = new FacadePerros();
        List<Perros> listP = facade.findAll();
        
        for (Perros dog : listP) {
            PerrosDTO dto = new PerrosDTO();
            
            dto.setId(dog.getId());
            dto.setNombre(dog.getNombre());
            dto.setPersonalidad(dog.getPersonalidad());
            dto.setEstado(dog.getEstado());
            dto.setDescripcion(dog.getDescripcion());
            dto.setGrupo(dog.getGrupo());
            dto.setNivelEnergia(dog.getNivelEnergia());
            
            perroDTO.add(dto);
        }

        return perroDTO;
    }

    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PerrosDTO getPerro(@PathParam("id") String id) throws ConexionException {
        FacadePerros facade = null;
        PerrosDTO dto = null;

        facade = new FacadePerros();
        Perros dog = facade.get(Integer.parseInt(id));
        dto = new PerrosDTO();
        
        dto.setId(dog.getId());
        dto.setNombre(dog.getNombre());
        dto.setDescripcion(dog.getDescripcion());
        dto.setEstado(dog.getEstado());
        dto.setNivelEnergia(dog.getNivelEnergia());
        dto.setGrupo(dog.getGrupo());
        dto.setPersonalidad(dog.getPersonalidad());
        

        return dto;
    }

}
