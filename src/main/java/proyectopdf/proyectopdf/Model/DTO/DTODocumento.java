package proyectopdf.proyectopdf.Model.DTO;

public class DTODocumento {
        //----------ID----------//
        private Long id;
    
        //----------NOMBRE----------//
        private String nombre;

        //----------NOMBRE----------//
        private String fecha;
    
        //----------ESTADO----------//
        private Boolean estado;
        
        public DTODocumento(Long id, String nombre, String fecha, Boolean estado) {
            this.id = id;
            this.nombre = nombre;
            this.fecha = fecha;
            this.estado = estado;
        }

        public DTODocumento() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Boolean getEstado() {
            return estado;
        }

        public void setEstado(Boolean estado) {
            this.estado = estado;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }
    
        
}
