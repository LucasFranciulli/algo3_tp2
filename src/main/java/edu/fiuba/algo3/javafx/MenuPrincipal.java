package edu.fiuba.algo3.javafx;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MenuPrincipal extends BorderPane{
    static Scene pantallaDeInicio;
    static String respuesta;
    String botonAntesDeSerPresionado = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(243, 202, 76, 0.5); -fx-text-fill: #BDB69C; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String botonNormal = "-fx-border-width: 2px; -fx-border-color: #B4DBE2; -fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #42B0D3; -fx-shape: \"M 100 350 A 50 50 0 1 1 100 250 L 300 250 A 50 50 0 1 1 300 350 Z\";";
    String formatoTexto = "-fx-border-width: 0px; -fx-border-color: #80CEB9; -fx-background-color: transparent; -fx-text-fill: #42B0D3";


	private Image logo;
	private Image logoTitulo;
	private Image logoFondo;
	private Image fondoJugar;
    private Image fondoSalir;
    private Image fondoComoJugar;
    private Image fondoAcercaDe;


    public MenuPrincipal (Stage stage) {
		this.cargarImagenes();
        this.setMenu(stage);
        stage.setMaximized(true);
        stage.getIcons().add(this.logo);
        this.setStyle(" -fx-padding: 70 100 0 100");
        
        BackgroundImage imagenDeFondo = new BackgroundImage(this.logoFondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.setBackground(new Background(imagenDeFondo));
	}


	private void cargarImagenes() {
		String pathFondoLogo = this.getClass().getResource("/imagenes/fondoDePantalla.jpg").toString();
		this.logoFondo = new Image(pathFondoLogo);

        String pathTituloFoto = this.getClass().getResource("/imagenes/titulo.png").toString();
		this.logoTitulo = new Image(pathTituloFoto); 
		
		String pathIconoJugar = this.getClass().getResource("/imagenes/jugar.png").toString();
		this.fondoJugar = new Image(pathIconoJugar);
		
		String pathIconoSalir = this.getClass().getResource("/imagenes/salir.png").toString();
		this.fondoSalir = new Image(pathIconoSalir);

        String pathIconoComoJugar = this.getClass().getResource("/imagenes/comoJugar.png").toString();
		this.fondoComoJugar = new Image(pathIconoComoJugar);

        String pathIconoAcercaDe = this.getClass().getResource("/imagenes/acercaDe.png").toString();
		this.fondoAcercaDe = new Image(pathIconoAcercaDe);

        String pathLogo = this.getClass().getResource("/imagenes/icono.png").toString();
		this.logo = new Image(pathLogo);
	}


    private void setMenu(Stage stage){
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        //BOTON DE JUGAR
        Button jugar = new Button("");
		
        jugar.setGraphic(new ImageView(this.fondoJugar));
        jugar.setStyle(botonNormal);
        jugar.setOnMouseEntered(e -> jugar.setStyle(botonAntesDeSerPresionado));
        jugar.setOnMouseExited(e -> jugar.setStyle(botonNormal));

        HBox titulo = new HBox();

        //TITULO DEL JUEGO
        ImageView nombreDelJuego = new ImageView(this.logoTitulo);
        nombreDelJuego.fitHeightProperty();
        nombreDelJuego.fitWidthProperty();
        titulo.getChildren().add(nombreDelJuego);
        titulo.setAlignment(Pos.CENTER);
        
        //BOTON DE SALIR
        Button salir = new Button("");
        salir.setGraphic(new ImageView(this.fondoSalir));
        salir.setStyle(botonNormal);
        salir.setOnMouseEntered(e -> salir.setStyle(botonAntesDeSerPresionado));
        salir.setOnMouseExited(e -> salir.setStyle(botonNormal));

        salir.setOnAction(e-> {
            String a = cerrarJuego(stage);
            if(a =="Salir"){
                stage.close();
            }
        });
        
        //BOTON COMO JUGAR
        Button comoJugar = new Button("");
        comoJugar.setStyle(botonNormal);
        comoJugar.setGraphic(new ImageView(this.fondoComoJugar));
        comoJugar.setOnMouseEntered(e -> comoJugar.setStyle(botonAntesDeSerPresionado));
        comoJugar.setOnMouseExited(e -> comoJugar.setStyle(botonNormal));
        comoJugar.setOnAction(e -> comoJugar());

        //BOTON ACERCA DE
        Button acercaDe = new Button("");
        acercaDe.setStyle(botonNormal);
        acercaDe.setGraphic(new ImageView(this.fondoAcercaDe));
        acercaDe.setOnMouseEntered(e -> acercaDe.setStyle(botonAntesDeSerPresionado));
        acercaDe.setOnMouseExited(e -> acercaDe.setStyle(botonNormal));
        acercaDe.setOnAction(e -> acercaDe());

        VBox botonesPrincipales = new VBox();
        botonesPrincipales.getChildren().add(jugar);
        botonesPrincipales.getChildren().add(comoJugar);
        botonesPrincipales.getChildren().add(acercaDe);
        botonesPrincipales.getChildren().add(salir);
        botonesPrincipales.setAlignment(Pos.CENTER);
        botonesPrincipales.setSpacing(30);
        this.setCenter(botonesPrincipales);
        this.setTop(titulo);

        
        pantallaDeInicio = new Scene(this, screenSize.getWidth(), screenSize.getHeight(), Color.rgb(47, 52, 58));
        
        //BOTON JUGAR FUNCION
        MenuPreguntas menuPreguntas = new MenuPreguntas(stage, pantallaDeInicio);
        jugar.setOnAction(e -> stage.setScene(menuPreguntas.getMenuPreguntas()));
    }
    
    public Scene getMenu(){
        return pantallaDeInicio;
    }

    private void acercaDe(){
        //BOTON ACERCA DE
        Stage ventanaAcercaDe = new Stage();
        ventanaAcercaDe.setResizable(false);
        ventanaAcercaDe.setTitle("Acerca De");
        ventanaAcercaDe.getIcons().add(this.logo);

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);
        
        //BOTON OK
        Button botonOK = new Button("OK");
        botonOK.setStyle(botonNormal);
        botonOK.setFont(fuente);
        botonOK.setOnMouseEntered(e -> botonOK.setStyle(botonAntesDeSerPresionado));
        botonOK.setOnMouseExited(e -> botonOK.setStyle(botonNormal));
        
        VBox menuSalir = new VBox(20);
        
        Text parrafoAcercaDe = new Text("Somos el grupo 4 de la materia\n\nALGORITMOS Y PROGRAMACION III FIUBA.\n"
        + "\nEste es nuestro proyecto para el Trabajo Practico N2\n"+ "\n"
        + "\nSi queres ver el codigo de este proyecto podes ir a:\n"+ "\n"
        + "\nhttps://github.com/Chuleeta/algo3_tp2\n");

        Button link = new Button("IR A REPOSITORIO");
        link.setStyle(botonNormal);
        link.setFont(fuente);
        link.setOnMouseEntered(e -> link.setStyle(botonAntesDeSerPresionado));
        link.setOnMouseExited(e -> link.setStyle(botonNormal));
        link.setOnAction(e -> {
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/Chuleeta/algo3_tp2";
            try{
                rt.exec("rundll32 url.dll, FileProtocolHandler " + url);
            } catch(IOException r){
                ((Throwable) r).printStackTrace();
            }
        });
        
        parrafoAcercaDe.setFont(fuente);
        parrafoAcercaDe.setFill(Color.rgb(66,176,211));
        parrafoAcercaDe.setTextAlignment(TextAlignment.CENTER);
        
        menuSalir.getChildren().addAll(parrafoAcercaDe, link, botonOK);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonOK.setOnAction(e-> {
            ventanaAcercaDe.close();
        });

        Scene  escenaSalir = new Scene(menuSalir , 800 , 400);
		
        ventanaAcercaDe.setScene(escenaSalir);
        ventanaAcercaDe.showAndWait();
    }

    private void comoJugar(){
        
        Stage ventanaComoSalir = new Stage();
        ventanaComoSalir.getIcons().add(this.logo);
        ventanaComoSalir.setResizable(false);
        ventanaComoSalir.setTitle("Como se Juega");
        
        ScrollPane scroll = new ScrollPane();
        VBox menuSalir = new VBox( );

        Text parrafoComoJugar = new Text(
                "Este juego consiste en la confrontacion entre 2 razas\n"+
                "que luchan para sobrevivir. En esta lucha es clave la\n"+
                "obtencion de recursos que se deben recolectar para la\n"+
                "supervivencia. Los nodos minerales y los volcanes de\n" +
                "gas vespeno estarán distribuidos por el mapa.\n"+
                "\n"+
                "ZERG\n"+
                "Insectoides alienigenas que buscan la perfeccion\n"+
                "y la asimilacion de otras razas.Estos seres recuperan\n"+
                "su vida con el paso del tiempo. Todas las unidades se\n"+
                "crean a partir de larvas. Las larvas en los criaderos\n"+
                "evolucionan en zanganos que mutan en forma de\n"+
                "construccion. Los edificios habilitan a larvas a\n"+
                "tranformarse en nuevas unidades segun cada edificio\n"+
                "Las construcciones se crean sobre el moho que se\n"+
                "expande progresivamente desde los criaderos. Este\n"+
                "tiene un radio de moho de 5 que se exparse en 1 cada\n"+
                "2 turnos. Al destruirse el criadero NO desaparece el\n"+
                "moho que dejo en su rango previo a la destruccion.\n"+
                "\n"+
                "PROTOSS\n"+
                "Humanoides con alta tecnologia y habilidades\n"+
                "psionicas para preservar su civilizacion. Sus edificios\n"+
                "tienen vida y escudo. El escudo se regenera de forma\n"+
                "automatica hasta completarse. La vida atacada\n"+
                "no se recupera y queda debilitada, solo se recupera el\n"+
                "escudo. Construyen en el radio de un pilon, su rango\n"+
                "es de 3 y al ser destruido deja sin energia su zona.\n"+
                "\n"+
                "JUGADORES\n"+
                "Se juega con dos jugadores en simultaneo. Iniciando\n"+
                "se consulta los datos y la raza de cada jugador.\n"+
                "\n"+
                "TURNOS\n"+
                "El juego es por turnos, cada jugador elige sus\n"+
                "acciones y luego pasa el turno al contrincante.\n"+
                "\n"+
                "COMIENZO Y FIN\n"+
                "Cada uno empieza en su base con 200 de mineral y en\n"+
                "lados opuestos del mapa. Para ganar, un jugdaor debe\n"+
                "destruirle todos los edificios al jugador contrario.\n"+
                "\n"+
                "MAPA\n"+
                "Los mapas deben tener areas de tierra y areas\n"+
                "especiales, solo las unidades voladores circulan\n"+
                "libremente por cualquier tipo de superficie.\n"+
                "\n"+
                "POBLACION\n"+
                "La capacidad de poblacion es de 200. Esta capacidad se\n"+
                "ve afectada de forma distinta para cada raza. Para los\n"+
                "Protoss un pilon contruido aumenta en 5 la capacidad, y\n"+
                "la disminuye al ser destruido. Los Zerg poseen la misma\n"+
                "ventaja pero con criaderos y su unidad Amo Supremo.\n");
        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);
        parrafoComoJugar.setFont(fuente);
        parrafoComoJugar.setFill(Color.rgb(66,176,211));
        parrafoComoJugar.setTextAlignment(TextAlignment.LEFT);
        
        Button botonOK = new Button("OK");
        botonOK.setStyle(botonNormal);
        botonOK.setFont(fuente);
        botonOK.setOnMouseEntered(e -> botonOK.setStyle(botonAntesDeSerPresionado));
        botonOK.setOnMouseExited(e -> botonOK.setStyle(botonNormal));
        
        menuSalir.getChildren().addAll(parrafoComoJugar , botonOK);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonOK.setOnAction(e-> {
            ventanaComoSalir.close();
        });

        scroll.setContent(menuSalir);
        Scene  escenaSalir = new Scene(scroll , 830 , 700);

        ventanaComoSalir.setScene(escenaSalir);
        ventanaComoSalir.showAndWait();
    }
    
    private String cerrarJuego(Stage stage){
        Stage ventanaSalir = new Stage();
        ventanaSalir.setResizable(false);
        ventanaSalir.getIcons().add(this.logo);
        ventanaSalir.setTitle("Salir del Juego");

        InputStream is = getClass().getResourceAsStream("/fonts/Starcraft-Normal.ttf");
        Font fuente = Font.loadFont(is, 20);
        
        Button botonSalir = new Button("SI");
        botonSalir.setStyle(botonNormal);
        botonSalir.setFont(fuente);
        botonSalir.setOnMouseEntered(e -> botonSalir.setStyle(botonAntesDeSerPresionado));
        botonSalir.setOnMouseExited(e -> botonSalir.setStyle(botonNormal));
        
        Button botonQuedarse = new Button("NO");
        botonQuedarse.setStyle(botonNormal);
        botonQuedarse.setFont(fuente);
        botonQuedarse.setOnMouseEntered(e -> botonQuedarse.setStyle(botonAntesDeSerPresionado));
        botonQuedarse.setOnMouseExited(e -> botonQuedarse.setStyle(botonNormal));
        
        VBox menuSalir = new VBox(20);
        
        Label preguntaSalir = new Label("Estas seguro que queres salir ?");
        preguntaSalir.setStyle(formatoTexto);
        preguntaSalir.setFont(fuente);
        
        menuSalir.getChildren().addAll(preguntaSalir , botonSalir ,botonQuedarse);
        menuSalir.setAlignment(Pos.CENTER);
        menuSalir.setStyle("-fx-border-color: #131E28; -fx-background-color: #131E28");
        
        botonSalir.setOnAction(e-> {
            respuesta = "Salir";
            ventanaSalir.close();
        });
        
        botonQuedarse.setOnAction(e->{
            respuesta  = "Quedarse";
            ventanaSalir.close();
        });

        Scene  escenaSalir = new Scene(menuSalir , 530 , 200);

        ventanaSalir.setScene(escenaSalir);
        ventanaSalir.showAndWait();
        return(respuesta);
    }
}