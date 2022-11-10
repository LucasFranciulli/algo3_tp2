package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.CriaderoNoDisponibleException;
import edu.fiuba.algo3.modelo.Extractor;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Message;
import edu.fiuba.algo3.modelo.Posicion;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriaderoTest {

    //Comienzo caso de uso 1
    @Test
    public void criaderoSeInicializaConTresLarvas() 
    {
        Criadero criadero = new Criadero(new Posicion(1, 1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());    
    }

    @Test
    public void seEngendraUnZanganoEnCriaderoYDisminuyeLarva() throws CriaderoNoDisponibleException
    {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        assertFalse(criadero.llenoDeLarvas());
    }

    @Test
    public void seRegeneraUnaLarvaLuegoDeUnTiempo() throws CriaderoNoDisponibleException
    {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    @Test
    public void noSePuedeEngendrarMasDeTresZanganos() throws CriaderoNoDisponibleException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        assertThrows(CriaderoNoDisponibleException.class, ()->{ criadero.engendrarZangano(); });
    }

    @Test
    public void seConsumenTodasLasLarvasYSeRegeneranDespsDeTresTurnos() throws CriaderoNoDisponibleException {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.engendrarZangano();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
    }

    // Caso de uso 2

    @Test
    public void seConstruyeEnElCuartoTurno() {
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertFalse(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertTrue(criadero.llenoDeLarvas());
        criadero.pasarTiempo();
        assertEquals(criadero.getLarvas().size(), 3);
    }

    @Test
    public void noSeEngendraZanganoSinConstruirseCriadero() throws CriaderoNoDisponibleException{
        Criadero criadero = new Criadero(new Posicion(2,2), new Mapa());
        assertThrows(CriaderoNoDisponibleException.class, ()->{ criadero.engendrarZangano(); });
    }

    //caso de uso 10
    @Test
    public void seRegeneraTodaLaVidaDespuesDeAlgunosTurnos(){
        //given
        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //when
        criadero.dañar(200);
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //then
        assertTrue(criadero.tieneVidaCompleta());
    }

    @Test
    public void seRegeneraLaVidaParcialmenteDespuesDeUnTurno(){
        //given
        Criadero criadero = new Criadero(new Posicion(1,1), new Mapa());
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();
        criadero.pasarTiempo();

        //when
        criadero.dañar(200);
        criadero.pasarTiempo();

        //then
        assertFalse(criadero.tieneVidaCompleta());
    }

    //Caso de uso 13
    @Test
    public void seDestruyeUnCriaderoYSePuedeConstruirIgualArribaDelMoho() 
    {
        Mapa mapa = new Mapa();
        Criadero criadero = new Criadero(new Posicion(9, 9), mapa);
        mapa.agregarConstruccion(criadero);
        mapa.pasarTiempo();
        mapa.pasarTiempo();
        mapa.pasarTiempo();              
        mapa.pasarTiempo();
        criadero.dañar(550);
        assertTrue(mapa.agregarConstruccion(new Extractor(new Posicion(9, 14), mapa)));
    }
}

