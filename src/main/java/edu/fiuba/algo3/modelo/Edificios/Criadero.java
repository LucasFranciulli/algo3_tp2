package edu.fiuba.algo3.modelo.Edificios;


import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Exceptions.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Criadero extends Edificio {

    private List<Larva> larvas;
    private static int COSTO_DE_CONSTRUCCION = 50;
    private static int VIDA_COMPLETA = 500;

    public Criadero(Posicion posicion, Mapa mapa) {
        this.posicion = posicion;
        this.estado = new EstadoNoConstruido();
        larvas = new ArrayList<>();
        this.mapa = mapa;
        zona = mapa.getZonaNeutral();
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        if(this.jugador == null){
            crearJugadorPorDefecto();
        }
    }

    public Criadero(Posicion posicion, Jugador jugador) {
        this(posicion, jugador.mapa);
        this.jugador = jugador;
    }

    private List<Larva> inicializarLarvas() {
        List<Larva> listaDeLarvas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Larva unaLarva = new Larva();
            listaDeLarvas.add(unaLarva);
        }
        return listaDeLarvas;
    }

    public List<Larva> getLarvas() {
        return larvas;
    }

    public boolean llenoDeLarvas() {
        return larvas.size() == 3;
    }

    public Zangano engendrarZangano(Mineral mineral) throws CriaderoNoDisponibleException, RequerimientosInsuficientesException, maximaPoblacionAlcanzadaException {
        if (larvas.size() == 0) {
            throw new CriaderoNoDisponibleException();
        }

        if (jugador.unidadesDisponibles()) {
            jugador.añadirUnidad();
            return larvas.remove(0).mutar(mineral);
        }
        throw new maximaPoblacionAlcanzadaException();
    }

    @Override
    public void construir() {
        larvas = inicializarLarvas();
        estado = new EstadoConstruido();
        zona = new ZonaMoho(this.posicion);
        mapa.agregarZona(this.zona);
        jugador.incrementarCapacidadDePoblacion(5);
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException{
        tiempo += 1;
        this.estado = estado.desarrollar(this, 4, tiempo);
    }

    public void actualizar(){
        this.vida.regenerar();
        if (tiempo % 2 == 0)
        zona.propagar();
        if (larvas.size() < 3)
        larvas.add(new Larva());
    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return true;
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return true;
    }


    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(200))
        {
            this.jugador.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    public void destruir()
    {
        this.jugador.decrementarCapacidadDePoblacion(5);
        this.jugador.destruirConstruccion(this);
    }
}
