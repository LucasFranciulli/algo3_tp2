package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Estados.EstadoConstruido;
import edu.fiuba.algo3.modelo.Estados.EstadoNoConstruido;
import edu.fiuba.algo3.modelo.Exceptions.RequerimientosInsuficientesException;
import edu.fiuba.algo3.modelo.Exceptions.maximaPoblacionAlcanzadaException;
import edu.fiuba.algo3.modelo.Individuos.Dragon;
import edu.fiuba.algo3.modelo.Individuos.Hidralisco;
import edu.fiuba.algo3.modelo.Individuos.Mutalisco;
import edu.fiuba.algo3.modelo.Individuos.Scout;
import edu.fiuba.algo3.modelo.Individuos.Zangano;
import edu.fiuba.algo3.modelo.Individuos.Zealot;
import edu.fiuba.algo3.modelo.Individuos.Zerling;
import edu.fiuba.algo3.modelo.Recursos.GasVespeno;
import edu.fiuba.algo3.modelo.Recursos.Mineral;
import edu.fiuba.algo3.modelo.Exceptions.NoExisteEdificioCorrelativoException;
import edu.fiuba.algo3.modelo.Exceptions.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Zonas.ZonaEnergia;
import edu.fiuba.algo3.modelo.Zonas.ZonaMoho;
import edu.fiuba.algo3.modelo.Zonas.ZonaNeutral;

public class Espiral extends Edificio{
    private static int VIDA_COMPLETA = 1250;

    public Espiral(Posicion posicion, Mapa mapa)
    {
        this.posicion = posicion;
        estado = new EstadoNoConstruido();
        this.mapa = mapa;
        this.zona = new ZonaMoho(this.posicion);
        tiempo = 0;
        this.vida = new VidaZerg(VIDA_COMPLETA);
        // if(this.jugador == null){
        //     crearJugadorPorDefecto();
        // }
    }

    public Espiral(Posicion posicion, Jugador jugador) throws NoExisteEdificioCorrelativoException, RecursosInsuficientesException {
        this(posicion, jugador.getMapa());
        this.jugador = jugador;
        this.jugador.verificarEdificacionCorrelativa(this);
        if(!this.jugador.agregarConstruccion(this) || !this.mapa.agregarOcupable(this, posicion)){
            throw new RecursosInsuficientesException();
        }
    }

    public void pasarTiempo() throws NoExisteEdificioCorrelativoException {
        tiempo += 1;
        this.estado = this.estado.desarrollar(this,10, tiempo);
    }

    @Override
    public void construir() throws NoExisteEdificioCorrelativoException {
        // revisar correlativa
        // if (!this.mapa.verificarEdificacionCorrelativa(jugador, new Guarida(new Posicion(0,0), new Mapa()))){
        //     throw new NoExisteEdificioCorrelativoException();
        // }
        estado = new EstadoConstruido();

    }

    @Override
    public boolean habita(ZonaNeutral zona) {
        return false;
    }

    @Override
    public boolean habita(ZonaMoho zona) {
        return zona.abarca(posicion);
    }

    @Override
    public boolean habita(ZonaEnergia zona) {
        return false;
    }

    public boolean tieneVidaCompleta(){
        return this.vida.tieneVidaCompleta();
    }

    @Override
    public boolean agregarAlMapa(Mineral mineral, GasVespeno gas) {
        if(mineral.invertir(150) && gas.invertir(100))
        {
            this.jugador.agregarEnListaConstruccion(this);
            return true;
        }
        return false;
    }

    @Override
    public void actualizar() {
        this.vida.regenerar();
    }

    public Mutalisco generarMutalisco(Mineral mineral, GasVespeno gas, Larva larva) throws RequerimientosInsuficientesException {
        return new Mutalisco(mineral, gas, new Posicion(3, 3), this.mapa);
    }

    public Mutalisco generarMutalisco(Mineral mineral, GasVespeno gasVespeno, Larva larva, Posicion inputUsuario) throws maximaPoblacionAlcanzadaException, RequerimientosInsuficientesException {
        if (jugador.unidadesDisponibles()) {
            Mutalisco mutalisco =  new Mutalisco(mineral, gasVespeno, inputUsuario, this.mapa);
            this.jugador.agregarIndividuo(mutalisco);
            jugador.añadirUnidad();
            return mutalisco;
        }
        throw new maximaPoblacionAlcanzadaException();

    }

    @Override
    public boolean verificarCorrelativa(Espiral espiral) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Guarida guarida) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(PuertoEstelar puertoEstelar) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Zangano zangano) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Zerling zerling) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Hidralisco hidralisco) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Mutalisco mutalisco) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean verificarCorrelativa(Zealot zealot) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Dragon dragon) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verificarCorrelativa(Scout scout) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getSpray(){
        return "/imagenes/espiral.png";
    }
}
