package edu.fiuba.algo3.javafx.Eventos;

import edu.fiuba.algo3.javafx.JuegoVista;
import edu.fiuba.algo3.modelo.Exceptions.*;
import edu.fiuba.algo3.modelo.Individuos.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.InputStream;

public class AtacarHandler extends BorderPane implements EventHandler<ActionEvent>{

    private final Individuo individuo;
    JuegoVista juegoVista;
    static Scene preguntaDatos;
    private Image logoFondo;
    private Image icono;
    private final Jugador oponente;
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #F3CA4C";

    public AtacarHandler(JuegoVista juegoVista, Jugador oponente, Individuo individuo) {

        cargarImagenes();
        this.juegoVista = juegoVista;
        this.oponente = oponente;
        this.individuo = individuo;
    }

    private void cargarImagenes() {
        // String pathLogoFondo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
        // this.logoFondo = new Image(pathLogoFondo);

        String pathicono = this.getClass().getResource("/imagenes/icono.png").toString();
        this.icono = new Image(pathicono);
    }

    private Posicion cargarPosicion(){
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        Label ingresarPosicion = new Label("Ingresar Posicion Buscada \n");
        ingresarPosicion.setFont(fuente);
        ingresarPosicion.setStyle(formatoTexto);

        ChoiceBox<String> posicionDeseadaX = new ChoiceBox();
        posicionDeseadaX.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22");
        posicionDeseadaX.setMinWidth(200);
        posicionDeseadaX.setMinHeight(100);
        posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        posicionDeseadaX.setOnMouseEntered(e -> posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        posicionDeseadaX.setOnMouseExited(e -> posicionDeseadaX.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        ChoiceBox<String> posicionDeseadaY = new ChoiceBox();
        posicionDeseadaY.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18");
        posicionDeseadaY.setMinWidth(200);
        posicionDeseadaY.setMinHeight(100);
        posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C");
        posicionDeseadaY.setOnMouseEntered(e -> posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));
        posicionDeseadaY.setOnMouseExited(e -> posicionDeseadaY.setStyle("-fx-border-width: 0px; -fx-border-color: #2F343A; -fx-background-color: #717D8C; -fx-text-fill: #BDB69C; -fx-font-family: Impact; -fx-font-size: 40; -fx-color: #BDB69C"));

        Label X = new Label("EN X: \n");
        X.setFont(fuente);
        X.setStyle(formatoTexto);

        Label Y = new Label("EN Y: \n");
        Y.setFont(fuente);
        Y.setStyle(formatoTexto);

        Button botonConfirmar = new Button("Confirmar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonAntesDeSerPresionado));
        botonConfirmar.setOnMouseExited(e -> botonConfirmar.setStyle(botonNormal));

        VBox enX = new VBox();
        VBox enY = new VBox();
        enX.getChildren().addAll(X, posicionDeseadaX);
        enX.setSpacing(20);
        enX.setAlignment(Pos.CENTER);

        enY.getChildren().addAll(Y, posicionDeseadaY);
        enY.setSpacing(20);
        enY.setAlignment(Pos.CENTER);

        VBox inputPosicion = new VBox();
        HBox posicionDeseada = new HBox();
        posicionDeseada.getChildren().addAll(enX, enY);
        posicionDeseada.setSpacing(20);
        posicionDeseada.setAlignment(Pos.CENTER);
        inputPosicion.getChildren().addAll(ingresarPosicion, posicionDeseada, botonConfirmar);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));

        Scene sc = new Scene(inputPosicion, 500, 300);
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Insertar Una Posicion");
        s.getIcons().add(this.icono);
        botonConfirmar.setOnAction(e-> {
            s.close();
        });
        s.setScene(sc);
        s.showAndWait();
        int valorX = 0;
        int valorY = 0;
        if(posicionDeseadaX.getValue() != null){
            valorX = Integer.valueOf(posicionDeseadaX.getValue());
        }else{
            return null;
        }

        if(posicionDeseadaY.getValue() != null){
            valorY = Integer.valueOf(posicionDeseadaY.getValue());
        }else{
            return null;
        }

        return (new Posicion(valorX, valorY));
    }

    private void noSePuedeConstruir(String text) {
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);

        Label ingresarPosicion = new Label(text);
        ingresarPosicion.setFont(fuente);
        ingresarPosicion.setStyle(formatoTexto);
        ingresarPosicion.setAlignment(Pos.CENTER);

        Button botonConfirmar = new Button("Confirmar");
        botonConfirmar.setFont(fuente);
        botonConfirmar.setStyle(botonNormal);
        botonConfirmar.setOnMouseEntered(e -> botonConfirmar.setStyle(botonAntesDeSerPresionado));
        botonConfirmar.setOnMouseExited(e -> botonConfirmar.setStyle(botonNormal));


        VBox inputPosicion = new VBox();
        inputPosicion.getChildren().addAll(ingresarPosicion, botonConfirmar);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));

        Scene sc = new Scene(inputPosicion, 500, 300);
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("No se puede atacar");
        s.getIcons().add(this.icono);
        botonConfirmar.setOnAction(e-> {
            s.close();
        });
        s.setScene(sc);
        s.showAndWait();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Stage s = new Stage();

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 25);

        InputStream is2 = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente2 = Font.loadFont(is2, 25);

        Label seleccionarIndividuo = new Label("Seleccionar Individuo\n");
        seleccionarIndividuo.setFont(fuente);
        seleccionarIndividuo.setStyle(formatoTexto);

        HBox opciones = new HBox();

        Button botonAtacar = new Button("Selecciona la posicion del individuo que quieres atacar");
        botonAtacar.setFont(fuente2);
        botonAtacar.setStyle(botonNormal);
        botonAtacar.setOnMouseEntered(e -> botonAtacar.setStyle(botonAntesDeSerPresionado));
        botonAtacar.setOnMouseExited(e -> botonAtacar.setStyle(botonNormal));
        botonAtacar.setOnAction(e-> {
            Posicion inputUsuario = this.cargarPosicion();
            if (inputUsuario != null) {
                try {
                    oponente.atacarUnidad(inputUsuario, this.individuo);
                } catch (NoSeEncuentraAlIndividuoException ex) {
                    noSePuedeConstruir("No se encontro ningun edificio o individuo en esa posicion");
                }
            }
        });

        VBox inputPosicion = new VBox();
        VBox opciones1 = new VBox();
        opciones1.getChildren().addAll(botonAtacar);
        opciones1.setSpacing(10);
        opciones1.setAlignment(Pos.CENTER);
        opciones.getChildren().addAll(opciones1);
        opciones.setSpacing(20);
        opciones.setAlignment(Pos.CENTER);
        inputPosicion.getChildren().addAll(seleccionarIndividuo, opciones);
        inputPosicion.setSpacing(30);
        inputPosicion.setAlignment(Pos.CENTER);
        inputPosicion.setBackground(new Background(new BackgroundFill(Color.rgb(47, 52, 58), new CornerRadii(0), Insets.EMPTY)));
        Scene sc = new Scene(inputPosicion, 800, 300, Color.rgb(47, 52, 58));
        sc.setFill(Color.RED);
        s.initModality(Modality.APPLICATION_MODAL);
        s.setTitle("Tienda Individuos Zerg");
        s.getIcons().add(this.icono);
        s.setResizable(false);
        s.setScene(sc);
        s.showAndWait();

    }

}