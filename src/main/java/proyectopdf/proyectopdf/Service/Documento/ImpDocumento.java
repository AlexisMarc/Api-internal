package proyectopdf.proyectopdf.Service.Documento;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyectopdf.proyectopdf.Model.Documento;
import proyectopdf.proyectopdf.Model.DocumentoUser;
import proyectopdf.proyectopdf.Model.Role;
import proyectopdf.proyectopdf.Model.User;
import proyectopdf.proyectopdf.Model.DTO.DTODocumentoComplet;
import proyectopdf.proyectopdf.Model.DTO.DTOUser;
import proyectopdf.proyectopdf.Service.User.IUser;
import proyectopdf.proyectopdf.Model.DTO.DTOCreateDocumento;
import proyectopdf.proyectopdf.Model.DTO.DTODocumento;

@Service
public class ImpDocumento {
    
    @Autowired 
    private IDocumento IDocumento;

    @Autowired 
    private IDocumentoUser iDocumentoUser;

    @Autowired 
    private IUser Iuser;

    private Date fecha = new Date();

    //----PRIVADOS----//

    //FINDALL
    private List<Documento> findAll(){
        List<Documento> Documentos = new ArrayList<Documento>();
        Documentos = IDocumento.findAll();
        return Documentos;
    }
    //FINDONE
    private Documento findOne(Long id){
        Documento Documento = new Documento();
        Documento = IDocumento.getReferenceById(id);
        return Documento;
    }
    //FINDONE
    private User findOneUser(Long id){
        User user = new User();
        user = Iuser.getReferenceById(id);
        return user;
    }
    //SAVE
    private Documento save(Documento Documento){
        Documento use = IDocumento.save(Documento);
        return use;
    }

    //SAVE
    private void saveDocumentoUser(DocumentoUser Documento){
        iDocumentoUser.save(Documento);
    }
    
    //ENTITY A DTO ROLE
    private List<String> DTORole(List<Role> roles){
        List<String> lista = new ArrayList<>();

        Iterator<Role> i = roles.iterator();
        while (i.hasNext()) {
            lista.add(i.next().getRol().toString());
        }
        return lista;
    }

    //ENTITY A DTO DOCUMENTO
    private List<DTODocumento> DTODocumento(List<DocumentoUser> documentos){

        List<DTODocumento> lista = new ArrayList<>();

        Iterator<DocumentoUser> i = documentos.iterator();
        while (i.hasNext()) {
            DTODocumento DTO = new DTODocumento();
            DocumentoUser d = i.next();
            DTO.setId(d.getId());
            DTO.setNombre(d.getDocumento().getNombre());
            DTO.setEstado(d.getEstado());
            DTO.setFecha(d.getDate());
            lista.add(DTO);
        }
        return lista;
    }

    //ENTITY A DTO
    private DTOUser DTOUser(User User){
        DTOUser DTO = new DTOUser();
        
        DTO.setId(User.getId());
        DTO.setNombre(User.getNombre());
        DTO.setApellido(User.getApellido());
        DTO.setEmail(User.getEmail());
        DTO.setRoles(DTORole(User.getRoles()));
        DTO.setDocumentos(DTODocumento(User.getDocumentos()));
        
        return DTO;
    }
    //ENTITY A DTO COMPLET
    public DTODocumentoComplet DTOComplet(Documento doc){
        DTODocumentoComplet complet = new DTODocumentoComplet();
        List<DTOUser> users = new ArrayList<DTOUser>();
        Iterator<DocumentoUser> i = doc.getUsers().iterator();

        while (i.hasNext()) {
            users.add(DTOUser(i.next().getUser()));
        }
        
        complet.setId(doc.getId());
        complet.setNombre(doc.getNombre());
        complet.setEstado(doc.getEstado());
        complet.setUsers(users);

        return complet;
    }

    //----PUBLICOS----//

    //LISTAR TODOS
    public List<DTODocumentoComplet> FindAllDocumento(){
        List<DTODocumentoComplet> DTOs = new ArrayList<DTODocumentoComplet>();
        Iterator<Documento> i = findAll().iterator();

        while (i.hasNext()) {
                DTODocumentoComplet dto = new DTODocumentoComplet();
                dto = DTOComplet(i.next());
                DTOs.add(dto);
        }
        return DTOs;
    }

    //LISTAR UNO
    public DTODocumentoComplet FindOneDocumento(Long id){
        return DTOComplet(findOne(id));
    }

    //CAMBIO DE ESTADO
    public void Estado(Long id){
        Documento doc = findOne(id);
        if (doc.getEstado()) {
            doc.setEstado(false);
        }else {
            doc.setEstado(true);
        }
        save(doc);
    }

    //REGISTRAR

    public DTODocumentoComplet CreateDocumento(DTOCreateDocumento DTO){
        Documento doc = new Documento();
        doc.setNombre(DTO.getNombre());
        doc.setEstado(true);
        Documento documento = save(doc);

        List<User> users = new ArrayList<User>();
        Iterator<Long> i = DTO.getUsers().iterator();
        while (i.hasNext()) {
            users.add(findOneUser(i.next()));
        }

        Iterator<User> u = users.iterator();
        while (u.hasNext()) {
            DocumentoUser DU = new DocumentoUser();
            DU.setEstado(false);
            DU.setDocumento(documento);
            DU.setUser(u.next());
            saveDocumentoUser(DU);
        }
        return FindOneDocumento(documento.getId());

    }
    
    
    public Long Firmado(String nombre, Long id) {
        DocumentoUser DU = iDocumentoUser.getReferenceById(id);
        DU.setDate(fecha.toString());
        DU.setEstado(true);
        Documento doc = IDocumento.getReferenceById(DU.getDocumento().getId());
        doc.setNombre(nombre);
        IDocumento.save(doc);
        return iDocumentoUser.save(DU).getDocumento().getId();

    }

}
