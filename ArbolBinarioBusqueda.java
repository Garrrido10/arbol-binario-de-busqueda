public class ArbolBinarioBusqueda {
    Producto raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    // 1. Inserción
    public void insertar(String nombre, int stock, double precio) {
        raiz = insertarRec(raiz, nombre, stock, precio);
    }

    private Producto insertarRec(Producto actual, String nombre, int stock, double precio) {
        if (actual == null) {
            return new Producto(nombre, stock, precio);
        }
        
        // Uso de compareTo() para ordenamiento alfabético
        int comparacion = nombre.compareTo(actual.nombre);
        
        if (comparacion < 0) {
            actual.izq = insertarRec(actual.izq, nombre, stock, precio);
        } else if (comparacion > 0) {
            actual.der = insertarRec(actual.der, nombre, stock, precio);
        } else {
            System.out.println("Error: El producto '" + nombre + "' ya existe en el inventario.");
        }
        return actual;
    }

    // 2. Búsqueda (Divide y Vencerás)
    public Producto buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Producto buscarRec(Producto actual, String nombre) {
        if (actual == null || actual.nombre.equals(nombre)) {
            return actual;
        }
        
        if (nombre.compareTo(actual.nombre) < 0) {
            return buscarRec(actual.izq, nombre);
        }
        return buscarRec(actual.der, nombre);
    }

    // 3. Eliminación (Manejando los 3 casos)
    public void eliminar(String nombre) {
        raiz = eliminarRec(raiz, nombre);
    }

    private Producto eliminarRec(Producto actual, String nombre) {
        if (actual == null) {
            System.out.println("Producto no encontrado para eliminar.");
            return actual;
        }

        int comparacion = nombre.compareTo(actual.nombre);

        if (comparacion < 0) {
            actual.izq = eliminarRec(actual.izq, nombre);
        } else if (comparacion > 0) {
            actual.der = eliminarRec(actual.der, nombre);
        } else {
            // Caso 1 y 2: Nodo hoja o con un solo hijo
            if (actual.izq == null) return actual.der;
            else if (actual.der == null) return actual.izq;

            // Caso 3: Nodo con dos hijos (Sucesor inorden)
            Producto sucesor = obtenerMinimo(actual.der);
            
            // Reasignar atributos copiando correctamente
            actual.nombre = sucesor.nombre;
            actual.stock = sucesor.stock;
            actual.precio = sucesor.precio;
            
            // Eliminar el sucesor de la rama derecha
            actual.der = eliminarRec(actual.der, actual.nombre);
            System.out.println("Producto eliminado correctamente.");
        }
        return actual;
    }

    // Método auxiliar para obtener el menor de la rama derecha
    private Producto obtenerMinimo(Producto actual) {
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

    // 4. Recorrido InOrder
    public void inOrder() {
        if (raiz == null) {
            System.out.println("El inventario está vacío.");
            return;
        }
        inOrderRec(raiz);
    }

    private void inOrderRec(Producto actual) {
        if (actual != null) {
            inOrderRec(actual.izq);
            System.out.println(String.format("Producto: %-15s | Stock: %-4d | Precio: $%.2f", 
                               actual.nombre, actual.stock, actual.precio));
            inOrderRec(actual.der);
        }
    }
}
