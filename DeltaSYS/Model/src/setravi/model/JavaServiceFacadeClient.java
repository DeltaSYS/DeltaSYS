package setravi.model;

import java.util.List;

public class JavaServiceFacadeClient {
    public static void main(String[] args) {
        try {
            final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
            for (SetraviPuntos setravipuntos : (List<SetraviPuntos>)javaServiceFacade.getSetraviPuntosFindAll()) {
                printSetraviPuntos(setravipuntos);
            }
            for (SetraviDatos setravidatos : (List<SetraviDatos>)javaServiceFacade.getSetraviDatosFindAll()) {
                printSetraviDatos(setravidatos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printSetraviPuntos(SetraviPuntos setravipuntos) {
        System.out.println("fecha_hora = " + setravipuntos.getFecha_hora());
        System.out.println("licencia = " + setravipuntos.getLicencia());
        System.out.println("motivo = " + setravipuntos.getMotivo());
    }

    private static void printSetraviDatos(SetraviDatos setravidatos) {
        System.out.println("direccion = " + setravidatos.getDireccion());
        System.out.println("licencia = " + setravidatos.getLicencia());
        System.out.println("nombre = " + setravidatos.getNombre());
        System.out.println("num_placa = " + setravidatos.getNum_placa());
    }
}
