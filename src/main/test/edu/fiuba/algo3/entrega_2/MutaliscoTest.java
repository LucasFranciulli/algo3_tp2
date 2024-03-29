package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Edificios.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Espiral;
import edu.fiuba.algo3.modelo.Edificios.Guarida;
import edu.fiuba.algo3.modelo.Edificios.NexoMineral;
import edu.fiuba.algo3.modelo.Edificios.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Exceptions.EspiralNoDisponibleException;
import edu.fiuba.algo3.modelo.Exceptions.MenaOcupadaException;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.ReservaDeReproduccionNoDisponibleException;
import edu.fiuba.algo3.modelo.Individuos.Guardian;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mena;
import edu.fiuba.algo3.modelo.Recursos.Mineral;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutaliscoTest {

    // caso 22
    @Test
    public void mutaliscoNoGeneraDañoPorNoEstarConstruidoAun() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, EspiralNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,1), new Mena(new Posicion(2,1)), jugador);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(2,3), jugador);
        for(int i=0; i<20; i++)
            guarida.pasarTiempo();
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for(int i=0; i<20; i++)
            espiral.pasarTiempo();
        Mutalisco mutalisco = new Mutalisco(new Posicion(3, 4), jugador);
        // EL tiempo de construccion es 4, con un solo tiempo no esta construido
        jugador.pasarTiempo();

        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        mutalisco.atacar(nexo);
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    // caso 18
    @Test
    public void mutaliscoAtacaNexoMineral23VecesYGenera207UnidadesDeDaño() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, EspiralNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,1), new Mena(new Posicion(2,1)), jugador);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(2,3), jugador);
        for(int i=0; i<20; i++)
            guarida.pasarTiempo();
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for(int i=0; i<20; i++)
            espiral.pasarTiempo();
        Mutalisco mutalisco = new Mutalisco(new Posicion(3,1), jugador);
        // tiempo de construccion
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        // Su unidad de ataque es de 9, con 23 ataques son 207 de daño
        for (int i = 0; i < 23; i++)
            mutalisco.atacar(nexo);


        //escudo dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //sigue dañado
        assertFalse(nexo.tieneEscudoCompleto());
        nexo.pasarTiempo();
        //sigue dañado
        for (int i = 0;  i < 100; i++)
            nexo.pasarTiempo();
        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());

    }
    // caso 23
    @Test
    public void mutaliscoNoAtacaNexoMineralPorqueEstaFueraDeRango() throws MenaOcupadaException, RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, EspiralNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        NexoMineral nexo = new NexoMineral(new Posicion(2,1), new Mena(new Posicion(2,1)), jugador);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(2,3), jugador);
        for(int i=0; i<20; i++)
            guarida.pasarTiempo();
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for(int i=0; i<20; i++)
            espiral.pasarTiempo();
        Mutalisco mutalisco = new Mutalisco(new Posicion(6,3), jugador);
        jugador.agregarIndividuo(mutalisco);
        // tiempo de construccion
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();
        jugador.pasarTiempo();

        // Su unidad de ataque es de 9, con 23 ataques son 207 de daño
        for (int i = 0; i < 23; i++)
            mutalisco.atacar(nexo);

        //escudo completo
        assertTrue(nexo.tieneEscudoCompleto());
    }

    @Test
    public void mutaliscoPuedeAtacarUnidadVoladoraYTerrestre() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException, RecursosInsuficientesException, EspiralNoDisponibleException, ReservaDeReproduccionNoDisponibleException {
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("jugadorUno", "azul", "zerg", new Posicion(1,1), mapa, 200);
        jugador.incrementarMineral(1000);
        jugador.incrementarGas(1000);
        Criadero criadero = new Criadero(new Posicion(1, 1), jugador);
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(new Posicion(2, 2), jugador);
        for(int i=0; i<20; i++)
            reservaDeReproduccion.pasarTiempo();
        Guarida guarida = new Guarida(new Posicion(2,3), jugador);
        for(int i=0; i<20; i++)
            guarida.pasarTiempo();
        Espiral espiral = new Espiral(new Posicion(3,3), jugador);
        for(int i=0; i<20; i++)
            espiral.pasarTiempo();
        Zerling zerling = new Zerling(new Posicion(1,2), jugador);
        Mutalisco mutaltemporal = new Mutalisco(new Posicion(1,3), jugador);
        Guardian guardian = new Guardian(new Posicion(1,3), jugador);
        Mutalisco mutal = new Mutalisco(new Posicion(1,4), jugador);
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        mutal.pasarTiempo();
        assertTrue(zerling.tieneVidaCompleta());
        assertTrue(guardian.tieneVidaCompleta());
        for (int i = 0; i < 10; i++)
        {
            mutal.atacar(zerling);
            mutal.atacar(guardian);
        }


        assertFalse(zerling.tieneVidaCompleta());
        assertFalse(guardian.tieneVidaCompleta());
    }

    @Test
    public void mutaliscoPuedeMoverseAZonaEspacialYAOtroLado() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {

        Mapa mapa = new Mapa();
        mapa.agregarAreaEspacial(new AreaEspacial(0, 0, 10, 10));
        GasVespeno gas = new GasVespeno(10000);
        Mineral mineral = new Mineral(10000);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(11,11), mapa);
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        mutalisco.pasarTiempo();
        assertTrue(mutalisco.mover(new Posicion(12, 12)));
        assertTrue(mutalisco.mover(new Posicion(3, 3)));
    }

    @Test
    public void mutaliscoNoPuedeEvolucionarAGuardianSinRecursos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(11,11), mapa);
        assertThrows(RequerimientosInsuficientesException.class, ()->{ mutalisco.evolucionarAGurdian(mineral, gas); });
    }

    @Test
    public void mutaliscoPuedeEvolucionarAGuardianConRecursos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Mineral mineral = new Mineral(1000);
        GasVespeno gas = new GasVespeno(1000);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(11,11), mapa);
        assertTrue(mutalisco.evolucionarAGurdian(mineral, gas) != null);
    }

    //Caso de uso 27
    @Test
    public void mutaliscoNoPuedeEvolucionarADevoradorSinRecursos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Mineral mineral = new Mineral(100);
        GasVespeno gas = new GasVespeno(100);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(11,11), mapa);
        assertThrows(RequerimientosInsuficientesException.class, ()->{ mutalisco.evolucionarADevorador(mineral, gas); });
    }

    @Test
    public void mutaliscoPuedeEvolucionarADevoradorConRecursos() throws RequerimientosInsuficientesException, NoExisteEdificioCorrelativoException {
        Mapa mapa = new Mapa();
        Mineral mineral = new Mineral(1000);
        GasVespeno gas = new GasVespeno(1000);
        Mutalisco mutalisco = new Mutalisco(mineral, gas, new Posicion(11,11), mapa);
        assertTrue(mutalisco.evolucionarADevorador(mineral, gas) != null);
    }

}
