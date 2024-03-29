package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Edificios.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Pilon;
import edu.fiuba.algo3.modelo.Exceptions.*;
import edu.fiuba.algo3.modelo.Individuos.AmoSupremo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    // Caso de uso 25
    
    @Test
    public void testSeCreanLosDosJugadoresCorrectamente() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "protoss", posicionDos, mapa, 200);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertDoesNotThrow(()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElNombreDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorUno", "rojo", "protoss", posicionDos, mapa, 200);
        
        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorElColorDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorDos", "azul", "protoss", posicionDos, mapa, 200);
        
        assertDoesNotThrow(()->jugadorUno.validarAtributos(null));
        assertThrows(AtributoInvalidoException.class, ()->jugadorDos.validarAtributos(jugadorUno));
    }
    
    @Test
    public void testSeLanzaExcepcionPorLaRazaDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(100,100);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", posicionDos, mapa, 200);

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }

    @Test
    public void testSeLanzaExcepcionPorLaPosicionDelJugadorDos() throws AtributoInvalidoException {
        Posicion posicionUno = new Posicion(1,1);
        Posicion posicionDos = new Posicion(99,99);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Jugador jugadorDos = new Jugador("jugadorDos", "rojo", "zerg", posicionDos, mapa, 200);

        assertThrows(AtributoInvalidoException.class, () -> jugadorDos.validarAtributos(jugadorUno));
    }

    // caso 26 para los zergs

    @Test
    public void seCreanUnidadesAlTenerCapacidadPorCriadero() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 0);
        Criadero criadero = new Criadero(new Posicion(2,2), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral = new Mineral(125);
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
    }

    @Test
    public void seCreanUnidadesAlTenerCapacidadPorAmoSupremoYCriadero() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 0);
        Criadero criadero = new Criadero(new Posicion(5,1), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        jugadorUno.incrementarMineral(100);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(4,4), jugadorUno);
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        Mineral mineral = new Mineral(250);
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
    }

    @Test
    public void alAlcanzarLaCapacidadMaximaNoSeCreaUnidadNueva() throws AtributoInvalidoException, NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 0);
        Criadero criadero = new Criadero(new Posicion(1,1), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        jugadorUno.incrementarMineral(100);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(4,4), jugadorUno);
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        Mineral mineral = new Mineral(300);
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> criadero.engendrarZangano(mineral));
    }

    @Test
    public void seCreanUnidadesAlTenerCapacidadPorPilon() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "protos", posicionUno, mapa, 0);
        Mineral mineral = new Mineral(500);
        Pilon pilon = new Pilon(new Posicion(3,3), jugadorUno);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        jugadorUno.incrementarMineral(1000);
        Acceso acceso = new Acceso(new Posicion(1,1), jugadorUno);
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));

    }

    @Test
    public void noSeCreanUnidadesAlAlcanzarElMaximoConPilon() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "protos", posicionUno, mapa, 0);
        Mineral mineral = new Mineral(600);
        GasVespeno gas = new GasVespeno(0);
        Pilon pilon = new Pilon(new Posicion(3,3), jugadorUno);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        jugadorUno.incrementarMineral(1000);
        Acceso acceso = new Acceso(new Posicion(1,1), jugadorUno);
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertNotNull(acceso.crearZealot(mineral));
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> acceso.crearZealot(mineral));

    }
    // caso 31
    @Test
    public void seDestruyePilonYLaCapacidadDisminuye() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "protos", posicionUno, mapa, 0);
        Mineral mineral = new Mineral(600);
        GasVespeno gas = new GasVespeno(0);
        Pilon pilon = new Pilon(new Posicion(3,3), jugadorUno);
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        pilon.pasarTiempo();
        jugadorUno.incrementarMineral(1000);
        Acceso acceso = new Acceso(new Posicion(1,1), jugadorUno);
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        pilon.destruir();
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> acceso.crearZealot(mineral));

    }

    @Test
    public void seDestruyeCriaderoYLaCapacidadDisminuye() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 0);
        Criadero criadero = new Criadero(new Posicion(2,2), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral1 = new Mineral(25);
        criadero.destruir();
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> criadero.engendrarZangano(mineral1));
    }

    @Test
    public void seDestruyeAmoSupremoYLaCapacidadDisminuye() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 0);
        Mineral mineralAmo = new Mineral(50);
        GasVespeno gas = new GasVespeno(0);
        AmoSupremo amoSupremo = new AmoSupremo(new Posicion(4,4), jugadorUno);
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.pasarTiempo();
        amoSupremo.destruir();
        jugadorUno.incrementarMineral(50);
        Criadero criadero = new Criadero(new Posicion(2,2), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral = new Mineral(150);
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        assertNotNull(criadero.engendrarZangano(mineral));
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertNotNull(criadero.engendrarZangano(mineral));
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> criadero.engendrarZangano(mineral));
    }
    // caso 29 y 30
    @Test
    public void alAlcanzarCapacidadMaximaNoSeSumaMasCapacidadConEdificio() throws NoExisteEdificioCorrelativoException, CriaderoNoDisponibleException, RecursosInsuficientesException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        Posicion posicionUno = new Posicion(1,1);
        Mapa mapa = new Mapa();
        Jugador jugadorUno = new Jugador("jugadorUno", "azul", "zerg", posicionUno, mapa, 200);
        Criadero criadero = new Criadero(new Posicion(2,2), jugadorUno);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        Mineral mineral = new Mineral(5000);

        for (int i = 0; i < 66; i++) {
            criadero.engendrarZangano(mineral);
            criadero.engendrarZangano(mineral);
            criadero.engendrarZangano(mineral);
            criadero.pasarTiempo();
            criadero.pasarTiempo();
            criadero.pasarTiempo();
        }

        criadero.engendrarZangano(mineral);
        criadero.engendrarZangano(mineral);
        // ya hay 200 unidades creadas y al intentar crear la 201 devuelve null
        assertThrows(maximaPoblacionAlcanzadaException.class, () -> criadero.engendrarZangano(mineral));

    }
}
