package modelo;

public interface Departamento {
    public int getId();
    public String getNombre();
    public int getNumEmpleados();
    public String getJefe();
    public String getServicios();
    public void setId(int id);
    public void setNombre(String nombre);
    public void setNumEmpleados(int numEmpleados);
    public void setJefe(String jefe);
    public void setServicios(String servicios);
    public boolean equals(Object o);
    public int hashCode();
    public String toString();
}
