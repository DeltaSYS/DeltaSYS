package deltasys.model;

import java.util.List;

public class JavaServiceFacadeClient {
    public static void main(String[] args) {
        try {
            final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
            for (DsUsuarios dsusuarios : (List<DsUsuarios>)javaServiceFacade.getDsUsuariosFindAll()) {
                printDsUsuarios(dsusuarios);
            }
            for (DsSectores dssectores : (List<DsSectores>)javaServiceFacade.getDsSectoresFindAll()) {
                printDsSectores(dssectores);
            }
            for (DsInfracciones dsinfracciones : (List<DsInfracciones>)javaServiceFacade.getDsInfraccionesFindAll()) {
                printDsInfracciones(dsinfracciones);
            }
            for (DsIntervalo dsintervalo : (List<DsIntervalo>)javaServiceFacade.getDsIntervaloFindAll()) {
                printDsIntervalo(dsintervalo);
            }
            for (DsSectorOficial dssectoroficial :
                 (List<DsSectorOficial>)javaServiceFacade.getDsSectorOficialFindAll()) {
                printDsSectorOficial(dssectoroficial);
            }
            for (DsPerfiles dsperfiles : (List<DsPerfiles>)javaServiceFacade.getDsPerfilesFindAll()) {
                printDsPerfiles(dsperfiles);
            }
            for (DsFotos dsfotos : (List<DsFotos>)javaServiceFacade.getDsFotosFindAll()) {
                printDsFotos(dsfotos);
            }
            for (DsCancelaciones dscancelaciones :
                 (List<DsCancelaciones>)javaServiceFacade.getDsCancelacionesFindAll()) {
                printDsCancelaciones(dscancelaciones);
            }
            for (DsPerfilesReglamento dsperfilesreglamento :
                 (List<DsPerfilesReglamento>)javaServiceFacade.getDsPerfilesReglamentoFindAll()) {
                printDsPerfilesReglamento(dsperfilesreglamento);
            }
            for (DsUbicaciones dsubicaciones : (List<DsUbicaciones>)javaServiceFacade.getDsUbicacionesFindAll()) {
                printDsUbicaciones(dsubicaciones);
            }
            for (DsReglamento dsreglamento : (List<DsReglamento>)javaServiceFacade.getDsReglamentoFindAll()) {
                printDsReglamento(dsreglamento);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printDsUsuarios(DsUsuarios dsusuarios) {
        System.out.println("nombre = " + dsusuarios.getNombre());
        System.out.println("oid = " + dsusuarios.getOid());
        System.out.println("password = " + dsusuarios.getPassword());
        System.out.println("dsUbicacionesList = " + dsusuarios.getDsUbicacionesList());
        System.out.println("dsSectorOficialList = " + dsusuarios.getDsSectorOficialList());
        System.out.println("dsIntervaloList = " + dsusuarios.getDsIntervaloList());
        System.out.println("dsPerfiles = " + dsusuarios.getDsPerfiles());
    }

    private static void printDsSectores(DsSectores dssectores) {
        System.out.println("id_setor = " + dssectores.getId_setor());
        System.out.println("sector = " + dssectores.getSector());
    }

    private static void printDsInfracciones(DsInfracciones dsinfracciones) {
        System.out.println("descripcion = " + dsinfracciones.getDescripcion());
        System.out.println("domicilio = " + dsinfracciones.getDomicilio());
        System.out.println("fecha_hora = " + dsinfracciones.getFecha_hora());
        System.out.println("id_folio = " + dsinfracciones.getId_folio());
        System.out.println("id_oid = " + dsinfracciones.getId_oid());
        System.out.println("latitud = " + dsinfracciones.getLatitud());
        System.out.println("licencia = " + dsinfracciones.getLicencia());
        System.out.println("longitud = " + dsinfracciones.getLongitud());
        System.out.println("monto = " + dsinfracciones.getMonto());
        System.out.println("nom_infractor = " + dsinfracciones.getNom_infractor());
        System.out.println("num_placa = " + dsinfracciones.getNum_placa());
        System.out.println("dsFotosList = " + dsinfracciones.getDsFotosList());
        System.out.println("dsReglamento = " + dsinfracciones.getDsReglamento());
        System.out.println("dsCancelacionesList = " + dsinfracciones.getDsCancelacionesList());
    }

    private static void printDsIntervalo(DsIntervalo dsintervalo) {
        System.out.println("fecha_hora_modificado = " + dsintervalo.getFecha_hora_modificado());
        System.out.println("id_intervalo = " + dsintervalo.getId_intervalo());
        System.out.println("intervalo = " + dsintervalo.getIntervalo());
        System.out.println("dsUsuarios = " + dsintervalo.getDsUsuarios());
    }

    private static void printDsSectorOficial(DsSectorOficial dssectoroficial) {
        System.out.println("id_sector = " + dssectoroficial.getId_sector());
        System.out.println("oid = " + dssectoroficial.getOid());
        System.out.println("dsUsuarios = " + dssectoroficial.getDsUsuarios());
    }

    private static void printDsPerfiles(DsPerfiles dsperfiles) {
        System.out.println("id_perfil = " + dsperfiles.getId_perfil());
        System.out.println("perfil = " + dsperfiles.getPerfil());
        System.out.println("dsUsuariosList = " + dsperfiles.getDsUsuariosList());
        System.out.println("dsPerfilesReglamentoList = " + dsperfiles.getDsPerfilesReglamentoList());
    }

    private static void printDsFotos(DsFotos dsfotos) {
        System.out.println("id_folio = " + dsfotos.getId_folio());
        System.out.println("id_foto = " + dsfotos.getId_foto());
        System.out.println("url = " + dsfotos.getUrl());
        System.out.println("dsInfracciones = " + dsfotos.getDsInfracciones());
    }

    private static void printDsCancelaciones(DsCancelaciones dscancelaciones) {
        System.out.println("fecha = " + dscancelaciones.getFecha());
        System.out.println("folio_oficio = " + dscancelaciones.getFolio_oficio());
        System.out.println("id_folio = " + dscancelaciones.getId_folio());
        System.out.println("dsInfracciones = " + dscancelaciones.getDsInfracciones());
    }

    private static void printDsPerfilesReglamento(DsPerfilesReglamento dsperfilesreglamento) {
        System.out.println("id_articulo = " + dsperfilesreglamento.getId_articulo());
        System.out.println("id_fraccion = " + dsperfilesreglamento.getId_fraccion());
        System.out.println("id_inciso = " + dsperfilesreglamento.getId_inciso());
        System.out.println("id_perfil = " + dsperfilesreglamento.getId_perfil());
        System.out.println("dsReglamento = " + dsperfilesreglamento.getDsReglamento());
        System.out.println("dsPerfiles = " + dsperfilesreglamento.getDsPerfiles());
    }

    private static void printDsUbicaciones(DsUbicaciones dsubicaciones) {
        System.out.println("fecha_hora = " + dsubicaciones.getFecha_hora());
        System.out.println("latitud = " + dsubicaciones.getLatitud());
        System.out.println("longitud = " + dsubicaciones.getLongitud());
        System.out.println("oid = " + dsubicaciones.getOid());
        System.out.println("dsUsuarios = " + dsubicaciones.getDsUsuarios());
    }

    private static void printDsReglamento(DsReglamento dsreglamento) {
        System.out.println("descripcion = " + dsreglamento.getDescripcion());
        System.out.println("id_articulo = " + dsreglamento.getId_articulo());
        System.out.println("id_fraccion = " + dsreglamento.getId_fraccion());
        System.out.println("id_inciso = " + dsreglamento.getId_inciso());
        System.out.println("num_salarios = " + dsreglamento.getNum_salarios());
        System.out.println("dsPerfilesReglamentoList = " + dsreglamento.getDsPerfilesReglamentoList());
        System.out.println("dsInfraccionesList = " + dsreglamento.getDsInfraccionesList());
    }
}
