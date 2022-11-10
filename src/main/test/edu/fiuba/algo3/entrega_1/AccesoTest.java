package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccesoTest {

    //Caso de uso 12
    @Test
    public void recibeDañoYElEscudoYSeRecuperaConElTiempoHastaEstarCompleto() {
        Acceso acceso = new Acceso(new Posicion(1, 1), new Mapa());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.dañar(500);
        assertFalse(acceso.tieneEscudoCompleto());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
    }

    //Caso de uso 12
    @Test
    public void recibeDañoElEscudoYSeRecuperaPeroLaVidaNo() {
        Acceso acceso = new Acceso(new Posicion(1, 1), new Mapa());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.dañar(550);
        assertFalse(acceso.tieneEscudoCompleto());
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        acceso.pasarTiempo();
        assertTrue(acceso.tieneEscudoCompleto());
        assertFalse(acceso.tieneVidaCompleta());
    }

}