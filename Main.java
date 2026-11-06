import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinarioBusqueda inventario = new ArbolBinarioBusqueda();
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n=== Sistema de Control de Inventario (ABB) ===");
            System.out.println("a) Registrar / Insertar nuevo producto (Presione 1)");
            System.out.println("b) Buscar producto por nombre (Presione 2)");
            System.out.println("c) Dar de baja / Eliminar producto (Presione 3)");
            System.out.println("d) Mostrar inventario completo InOrder (Presione 4)");
            System.out.println("e) Salir (Presione 5)");
            System.out.print("Seleccione una opción: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese el stock: ");
                        int stock = scanner.nextInt();
                        System.out.print("Ingrese el precio: ");
                        double precio = scanner.nextDouble();
                        inventario.insertar(nombre, stock, precio);
                        System.out.println("Operación de registro finalizada.");
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre del producto a buscar: ");
                        String nombreBuscar = scanner.nextLine();
                        Producto p = inventario.buscar(nombreBuscar);
                        if (p != null) {
                            System.out.println("Encontrado -> " + p.nombre + " | Stock: " + p.stock + " | Precio: $" + p.precio);
                        } else {
                            System.out.println("El producto no existe en el inventario.");
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre del producto a dar de baja: ");
                        String nombreEliminar = scanner.nextLine();
                        inventario.eliminar(nombreEliminar);
                        break;
                    case 4:
                        System.out.println("\n--- Lista de Productos (Orden Alfabético) ---");
                        inventario.inOrder();
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada inválida. Ingrese un número.");
                scanner.nextLine(); // Limpiar entrada errónea
            }
        }
        scanner.close();
    }
}
