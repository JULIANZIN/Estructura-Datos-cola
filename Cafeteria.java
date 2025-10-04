import java.util.*;

// Clase que representa un pedido
class Pedido {
    private int numero;
    private String cliente;
    private String producto;

    public Pedido(int numero, String cliente, String producto) {
        this.numero = numero;
        this.cliente = cliente;
        this.producto = producto;
    }

    public int getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        return "Pedido #" + numero + " | Cliente: " + cliente + " | Producto: " + producto;
    }
}

public class Cafeteria {
    private static Queue<Pedido> pedidosEnEspera = new LinkedList<>();
    private static List<Pedido> pedidosEntregados = new ArrayList<>();
    private static int contadorPedidos = 1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    registrarPedido();
                    break;
                case 2:
                    atenderPedido();
                    break;
                case 3:
                    mostrarPedidosEnEspera();
                    break;
                case 4:
                    mostrarPedidosEntregados();
                    break;
                case 5:
                    buscarPedidoPorNumero();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú Cafetería ---");
        System.out.println("1. Registrar pedido");
        System.out.println("2. Atender pedido");
        System.out.println("3. Mostrar pedidos en espera");
        System.out.println("4. Mostrar pedidos entregados");
        System.out.println("5. Buscar pedido por número");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarPedido() {
        System.out.print("Ingrese nombre del cliente: ");
        String cliente = sc.nextLine();
        System.out.print("Ingrese producto solicitado: ");
        String producto = sc.nextLine();

        Pedido nuevo = new Pedido(contadorPedidos++, cliente, producto);
        pedidosEnEspera.add(nuevo);
        System.out.println("Pedido registrado: " + nuevo);
    }

    private static void atenderPedido() {
        if (pedidosEnEspera.isEmpty()) {
            System.out.println("No hay pedidos en espera.");
            return;
        }
        Pedido atendido = pedidosEnEspera.poll();
        pedidosEntregados.add(atendido);
        System.out.println("Pedido atendido: " + atendido);

        if (!pedidosEnEspera.isEmpty()) {
            System.out.println("Siguiente en la cola: " + pedidosEnEspera.peek());
        } else {
            System.out.println("No hay más pedidos en espera.");
        }
    }

    private static void mostrarPedidosEnEspera() {
        if (pedidosEnEspera.isEmpty()) {
            System.out.println("No hay pedidos en espera.");
            return;
        }
        System.out.println("Pedidos en espera:");
        for (Pedido p : pedidosEnEspera) {
            System.out.println(p);
        }
    }

    private static void mostrarPedidosEntregados() {
        if (pedidosEntregados.isEmpty()) {
            System.out.println("No hay pedidos entregados.");
            return;
        }
        System.out.println("Pedidos entregados:");
        for (Pedido p : pedidosEntregados) {
            System.out.println(p);
        }
    }

    private static void buscarPedidoPorNumero() {
        System.out.print("Ingrese número de pedido a buscar: ");
        int numero = sc.nextInt();
        sc.nextLine();

        for (Pedido p : pedidosEnEspera) {
            if (p.getNumero() == numero) {
                System.out.println("Pedido encontrado en espera: " + p);
                return;
            }
        }
        for (Pedido p : pedidosEntregados) {
            if (p.getNumero() == numero) {
                System.out.println("Pedido encontrado entregado: " + p);
                return;
            }
        }
        System.out.println("No se encontró ningún pedido con ese número.");
    }
}
