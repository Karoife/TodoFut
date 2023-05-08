/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joser
 */
package todofut;
public class Postal {
    // Variables de la clase
    private String seccion; // La clase postal debe saber a que seccion pertenece
    private String identificador; // El identificador es que postal es, por ejmplo un objeto puede tener FWC 3
    private int precio; // Cuanto vale en colones según la sección
    private String imagen; //Esto puede ayudar a la GUI si logro tener una imagen por sticker
    private int cantidad;
    
    // Constructor de la clase
    public Postal(String seccionE, String identificadorE, int precioE, String imagenE){
        this.seccion = seccionE;
        this.identificador = identificadorE;
        this.imagen = imagenE;
        this.precio = precioE;
    }
    // metodos para solicitar y dar enntrads
    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int CostoTotal(){
        return this.cantidad*this.precio;
    }
}
