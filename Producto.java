public class Producto {
    String nombre; // Llave alfabética única
    int stock;     // Cantidad disponible
    double precio; // Costo unitario
    Producto izq, der;

    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.izq = null;
        this.der = null;
    }
}
