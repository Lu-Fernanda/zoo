
package ServiceRest;

import Facade.FacadeComentarios;
import ModeloDTO.ComentariosDTO;
import exception.ConexionException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Comentarios;

@Path("/comentarios")
public class ServiceComentarios {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ComentariosDTO addComentario(ComentariosDTO coment) throws ConexionException {
        
        FacadeComentarios facade = null;
        ComentariosDTO dto = null;

        facade = new FacadeComentarios();
        Comentarios dJPA = new Comentarios();
        
        dJPA.setNombre(coment.getNombre());
        dJPA.setComentario(coment.getComentario());
        
        facade.save(dJPA);

        return dto;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ComentariosDTO> getComentarios_JSON() throws ConexionException {
        List<ComentariosDTO> comentDTO = new ArrayList<ComentariosDTO>();
        FacadeComentarios facade = null;
         
        facade = new FacadeComentarios();
        List<Comentarios> listC = facade.findAll();

        for (Comentarios coment : listC) {
            ComentariosDTO dto = new ComentariosDTO();
            
            dto.setNombre(coment.getNombre());
            dto.setFecha(coment.getFecha());
            dto.setId(coment.getId());
            dto.setComentario(coment.getComentario());
            comentDTO.add(dto);
        }
        return comentDTO;
    }
}
