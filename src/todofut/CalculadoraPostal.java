/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joser
 */
/*
1. Debe de tener un menúque despliegue las secciones y que solicite en cada sección un máximo 5 postales, a excepciónde la sección postalesque el máximoson 20.
2.Cada postal esta identificada como en el álbum.Ejemplo : FWC 1, FWC 7, QAT 16 .. etc
3.Cuando elusuario indiquéque quiere salir del sistema, se debe de preguntar 
si quiere que se lo enviéa domicilio, si la respuesta es sí, debe de solicitarle ladirección del envióy sumarle un costo de 3000 colones a la factura.
4.Hasta que el usuario indique que quiere salir del sistema, sedesplegar el total a pagar. 
5.Recuerde que el programalo debe de realizar en forma individual, y realizar las validaciones y control de excepciones necesarias para que el programa no se cancele abruptamenteen la ejecución

Tomar en cuenta que no se agrega la generación de la lamina de compra al salir, sino que se sale del sistema completamente, me parecio una solución más logica, 
De igual forma si se desea realizar esto el JOption Pane tendría como grid la laminaTabla, y se agregaría la función GenerarTabla al actionListener "Salir"
*/
package todofut;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class CalculadoraPostal extends JFrame {
    //Totas las variables necesarias para la GUI
    private int costoEnvio;
    private int puntero;
    //
    Postal pagPrincipal[];
    Postal estadios[];
    Postal extraSticker[];
    Postal postales[];
    Postal museoFIFA[];
    JLabel labelPagPrincipal[];
    JLabel labelEstadios[];
    JLabel labelExtraSticker[];
    JLabel labelPostales[];
    JLabel labelMuseoFIFA[];
    JPanel cadaSticker[]; 
    JPanel laminaTabla;
    JPanel laminaStickers; 
    JPanel laminaBotones;
    //Botones de anterior siguiente, se utiliza esa cantidad para poder utilizar getsource
    JButton botonAntPagPrincipal;
    JButton botonSigPagPrincipal;
    JButton botonAntEstadios;
    JButton botonSigEstadios;
    JButton botonAntExtraSticker;
    JButton botonSigExtraSticker;
    JButton botonAntPostales;
    JButton botonSigPostales;
    JButton botonAntMuseoFIFA;
    JButton botonSigMuseoFIFA;
    //JText para agregar las cantidades de stickers deseadas    
    JTextField textPagPrincipal[];
    JButton botonPagPrincipal[];
    JMenuBar barra;
    JMenu archivo;
    JMenu secciones;
    JMenuItem menpPrincipal;
    JMenuItem menestadios;
    JMenuItem menextra;
    JMenuItem menpostales;
    JMenuItem menmuseo;
    JMenuItem compra;
    JMenuItem salir;
    JTable tablaFinal;
    //Para poder tener un scroll solución para mostrar las 20 postales, otra solución puede ser hacer el frame más grande y cambiar el Layout del Frame
    JScrollPane panelScroll;
    
    String nombre;
    String cedula;
    
    /*
    Calculadora Postal es el constructor basicamente realiza instancias, y llama las fuinciones necesarias para generar la primera vista de la GUI
    */
    public CalculadoraPostal(){
        try{
            nombre = JOptionPane.showInputDialog("Digite su nombre");
            cedula = JOptionPane.showInputDialog("Digite su cedula");
        } catch (Exception f){
            nombre = "Usuario 1";
            nombre = "XXXXXXXXX";
        }
        costoEnvio = 3000;
        puntero = 5;
        cadaSticker = new JPanel[608];
        
        barra = new JMenuBar();
        archivo =new JMenu("Archivo");
        secciones =new JMenu("Secciones");
        barra.add(archivo);
        barra.add(secciones);
        //Generación de los items del menu secciones
        menpPrincipal = new JMenuItem("Página Principal");
        menestadios = new JMenuItem("Estadios");
        menextra = new JMenuItem("Extra Sticker");
        menpostales = new JMenuItem("Postales");
        menmuseo = new JMenuItem("Museo FIFA");
        //Generación de los items del menu archivo
        compra = new JMenuItem("Generar Compra");
        salir = new JMenuItem("Salir");
        
        //Instancias de la clase postal global para la clase
        pagPrincipal = GenerarPostales("Página Principal",8);
        estadios = GenerarPostales("Estadios", 11);
        postales = GenerarPostales("Postales", 608);
        museoFIFA = GenerarPostales("Museo FIFA", 11);
        extraSticker = GenerarPostales("Extra Sticker", 36);
        //Instancias de los Labels para la impresión de los 
        labelPagPrincipal = GenerarLabels("Página Principal",8);
        labelEstadios = GenerarLabels("Estadios", 11);
        labelPostales = GenerarLabels("Postales", 608);
        labelMuseoFIFA = GenerarLabels("Museo FIFA", 11);
        labelExtraSticker = GenerarLabels("Extra Sticker",36);
        laminaTabla = new JPanel();
        laminaStickers = new JPanel(); 
        laminaBotones = new JPanel();
        //Instnacia de los botones
        botonAntPagPrincipal = new JButton("Anterior");
        botonSigPagPrincipal = new JButton("Siguiente");
        botonAntPagPrincipal.addActionListener(new AccionPagPrincipalS());
        botonSigPagPrincipal.addActionListener(new AccionPagPrincipalS());
        botonAntEstadios = new JButton("Anterior");
        botonSigEstadios = new JButton("Siguiente");
        botonAntEstadios.addActionListener(new AccionPagPrincipalS());
        botonSigEstadios.addActionListener(new AccionPagPrincipalS());
        botonAntExtraSticker = new JButton("Anterior");
        botonSigExtraSticker = new JButton("Siguiente");
        botonAntExtraSticker.addActionListener(new AccionPagPrincipalS());
        botonSigExtraSticker.addActionListener(new AccionPagPrincipalS());
        botonAntPostales = new JButton("Anterior");
        botonSigPostales = new JButton("Siguiente");
        botonAntPostales.addActionListener(new AccionPagPrincipalS());
        botonSigPostales.addActionListener(new AccionPagPrincipalS());
        botonAntMuseoFIFA = new JButton("Anterior");
        botonSigMuseoFIFA = new JButton("Siguiente");
        botonAntMuseoFIFA.addActionListener(new AccionPagPrincipalS());
        botonSigMuseoFIFA.addActionListener(new AccionPagPrincipalS());
        //Instancias de los campos de texto
        textPagPrincipal = new JTextField[608];
        botonPagPrincipal = new JButton[608];
       //panelScroll = new JScrollPane();
        //panelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i =0; i<608; i++){
            botonPagPrincipal[i] = new JButton("Agregar");
            botonPagPrincipal[i].addActionListener(new  Agregar());
        }
        //laminaBotones.add(botonAnt);
        //laminaBotones.add(botonSig);
        setTitle("Calculadora para Postales");
        setBounds(500,300,500,950);
        //LaminaMenu laminaSuperior = new LaminaMenu();
        //add(laminaSuperior);
        GenerarMenu();
        setVisible(true);
        
    }
    /*
    Genera el menú, con la clase menú, por ende va a mostrarse en la parte superior del frame, Esta es una solución aunque tambien puede realizarse con una seleccion, los botones de siguiente y anterior tambien podrían agregarse en esta sección
    */
    private void GenerarMenu(){

        //Agregar los items al menu archivo
        archivo.add(compra);
        archivo.add(salir);
        //Agregar los items al menu seccion
        secciones.add(menpPrincipal);
        secciones.add(menestadios);
        secciones.add(menextra);
        secciones.add(menpostales);
        secciones.add(menmuseo);
        //Agregar los menus a la barra de menus
        barra.add(archivo);
        barra.add(secciones);
        //Agregar la barra al Panel
        setJMenuBar(barra);
        //Colocar los botones en modo escuha
        salir.addActionListener(new AccionSalir());
        menpPrincipal.addActionListener(new AccionPagPrincipal());
        menestadios.addActionListener(new AccionPagPrincipal());
        menextra.addActionListener(new AccionPagPrincipal());
        menpostales.addActionListener(new AccionPagPrincipal());
        menmuseo.addActionListener(new AccionPagPrincipal());
        compra.addActionListener(new GenerarCompra()) ;
    }
    /*
    Genera la lamina principal
    Basicamente es la selección de los sticker según la selección de sección del menú, contiene un panel para los botones de anterior y siguiente, 
    y otro para donde se imprimen los stickers, son su identificador, JtextField para ingresar la cantidad y un botón para agregar la cantidad escrita.
    Las entradas son desde donde puede empezar y terminar la cantidad de postales, además la sección a la cual pertenecen los stickers, podría generar un error de identación, pero se agregaron los if suficientes para que esto no suceda, 
    además que el usuario solo puede entrar por medio de botones
    */
    private void GenerarLaminaPrincipal(int min, int max, int seccion){
        remove(laminaTabla);
        //Se deben eliminar los componenres y redibujar para que no se sobrepongan las vistas
        if(laminaStickers.getComponentCount()!=0){
            laminaBotones.removeAll();
            laminaStickers.removeAll();
            panelScroll.removeAll();
            laminaStickers.repaint();
            remove(panelScroll);
            //laminaBotones.repaint();
        }
        revalidate();
        //En esta sección se realiza para que cambien de lamina con el botón siguiente y anterior
        if (seccion == 3){
            if (puntero == max){
                puntero = puntero - 20;
                if (puntero < 20){puntero = 20;}
            } else if (puntero == min){
                puntero = puntero + 20;
            }
        }else{
            if (puntero == max){
                puntero = puntero - 5;
                if (puntero < 5){puntero = 5;}
            } else if (puntero == min){
                puntero = puntero + 5;
            }
        }
        if (seccion == 0){
            if (max > labelPagPrincipal.length){
                puntero = min;
                max = labelPagPrincipal.length;
                
            } else if(min < 5 ){
                min = 0;
            }
            for(int i = min; i<max;i++){
                cadaSticker[i] = new JPanel();
                textPagPrincipal[i] = new JTextField("0");
                textPagPrincipal[i].setPreferredSize(new Dimension(50,20));
                textPagPrincipal[i].addKeyListener(new SoloNumeros());
                

                //cadaSticker[i].removeAll();
                //cadaSticker[i].revalidate();
                
                cadaSticker[i].add(labelPagPrincipal[i]);
                cadaSticker[i].add(textPagPrincipal[i]);
                cadaSticker[i].add(botonPagPrincipal[i]);
                laminaStickers.add(cadaSticker[i]);
                
                
                laminaBotones.add(botonAntPagPrincipal);
                laminaBotones.add(botonSigPagPrincipal);
            }
        } else if (seccion == 1){
            if (max > labelEstadios.length){
                puntero = min;
                max = labelEstadios.length;
                
            } else if(min < 5 ){
                min = 0;
            }
            for(int i = min; i<max;i++){
                cadaSticker[i] = new JPanel();
                textPagPrincipal[i] = new JTextField("0");
                textPagPrincipal[i].setPreferredSize(new Dimension(50,20));
                textPagPrincipal[i].addKeyListener(new SoloNumeros());

                //cadaSticker[i].removeAll();        
                //cadaSticker[i].revalidate();
                
                cadaSticker[i].add(labelEstadios[i]);
                cadaSticker[i].add(textPagPrincipal[i]);
                cadaSticker[i].add(botonPagPrincipal[i]);
                laminaStickers.add(cadaSticker[i]);
               
                
                
                laminaBotones.add(botonAntEstadios);
                laminaBotones.add(botonSigEstadios);
            } 
        } else if (seccion == 2){
            if (max > labelExtraSticker.length){
                puntero = min;
                max = labelExtraSticker.length;
                
            } else if(min < 5 ){
                min = 0;
            }
            for(int i = min; i<max;i++){
                cadaSticker[i] = new JPanel();
                textPagPrincipal[i] = new JTextField("0");
                textPagPrincipal[i].setPreferredSize(new Dimension(50,20));
                textPagPrincipal[i].addKeyListener(new SoloNumeros());
                
                laminaBotones.add(botonAntExtraSticker);
                laminaBotones.add(botonSigExtraSticker);
                
                //cadaSticker[i].removeAll();
                //cadaSticker[i].revalidate();
                
                cadaSticker[i].add(labelExtraSticker[i]);
                cadaSticker[i].add(textPagPrincipal[i]);
                cadaSticker[i].add(botonPagPrincipal[i]);
                laminaStickers.add(cadaSticker[i]);
            }
        } else if (seccion == 3){
            if (max > labelPostales.length){
                puntero = min;
                max = labelPostales.length;
                
            } else if(min < 20 ){
                min = 0;
            }
            for(int i = min; i<max;i++){
                cadaSticker[i] = new JPanel();
                textPagPrincipal[i] = new JTextField("0");
                textPagPrincipal[i].setPreferredSize(new Dimension(50,20));
                textPagPrincipal[i].addKeyListener(new SoloNumeros());
                
                
                laminaBotones.add(botonAntPostales);
                laminaBotones.add(botonSigPostales);
                
                //cadaSticker[i].removeAll();
                //cadaSticker[i].revalidate();
                
                cadaSticker[i].add(labelPostales[i]);
                cadaSticker[i].add(textPagPrincipal[i]);
                cadaSticker[i].add(botonPagPrincipal[i]);
                laminaStickers.add(cadaSticker[i]);
            }
        } else if (seccion == 4){
            if (max > labelMuseoFIFA.length){
                puntero = min;
                max = labelMuseoFIFA.length;
                
            } else if(min < 5 ){
                min = 0;
            }
            for(int i = min; i<max;i++){
                cadaSticker[i] = new JPanel();
                textPagPrincipal[i] = new JTextField("0");
                textPagPrincipal[i].setPreferredSize(new Dimension(50,20));
                textPagPrincipal[i].addKeyListener(new SoloNumeros());
                
                laminaBotones.add(botonAntMuseoFIFA);
                laminaBotones.add(botonSigMuseoFIFA);

                //cadaSticker[i].removeAll();
                //cadaSticker[i].revalidate();
                
                cadaSticker[i].add(labelMuseoFIFA[i]);
                cadaSticker[i].add(textPagPrincipal[i]);
                cadaSticker[i].add(botonPagPrincipal[i]);
                laminaStickers.add(cadaSticker[i]);
                
            }
        }
        laminaStickers.setLayout(new BoxLayout(laminaStickers, BoxLayout.PAGE_AXIS));
        //laminaStickers.setBounds(20,50,350, 800);
        laminaStickers.setSize(new Dimension(350, 800));
        //panelScroll.add(laminaStickers);
        panelScroll = new JScrollPane(laminaStickers);
        //panelScroll.setSize(new Dimension(350, 800));
        
        add(panelScroll);
        //add(new JScrollPane(laminaStickers), BorderLayout.CENTER);
        
        panelScroll.setBounds(20,50,400, 800);

        add(laminaBotones);
        
        revalidate();
    }
    /*
    Se genera la tabla final, se usa un objeto tipo tabla 
    */
    private void GenerarTabla(){
        if(laminaTabla.getComponentCount()!=0){
            laminaTabla.removeAll();
            laminaTabla.repaint();
        }
        remove(panelScroll);
        remove(laminaBotones);
        remove(laminaStickers);
        
        String [] nombreColumnas = {"Sección", "Cantidad", "Costo", "Postales" };
        int cantidadPagPrincipal = 0, cantidadEstadios = 0, cantidadExtra = 0, cantidadPostales = 0, cantidadMuseoFIFA = 0;
        int costoPagPrincipal = 0, costoEstadios=0, costoExtra = 0, costoPostales = 0, costoMuseoFIFA = 0;
        String idenPagPrincipal = "", idenEstadios="", idenExtra="", idenPostales="", idenMuseoFIFA="";
        //String [] primerColumna = {"Página Principal", "Estadios", "Extra Sticker", "Postales", "Museo FIFA"};
        for(int i = 0; i<608; i++){
            if(i<8){
                if (pagPrincipal[i].getCantidad()>0){
                    cantidadPagPrincipal = pagPrincipal[i].getCantidad() + cantidadPagPrincipal;
                    costoPagPrincipal = pagPrincipal[i].CostoTotal() + costoPagPrincipal;
                    idenPagPrincipal = idenPagPrincipal + ", " + pagPrincipal[i].getIdentificador();
                }
            }
            if (i<11){
                if (estadios[i].getCantidad()>0){
                    cantidadEstadios = estadios[i].getCantidad() + cantidadEstadios;
                    costoEstadios = estadios[i].CostoTotal() + costoEstadios;
                    idenEstadios = idenEstadios + ", " + estadios[i].getIdentificador();
                }
                if (museoFIFA[i].getCantidad()>0){
                    cantidadMuseoFIFA = museoFIFA[i].getCantidad() + cantidadMuseoFIFA;
                    costoMuseoFIFA = museoFIFA[i].CostoTotal() + costoMuseoFIFA;
                    idenMuseoFIFA = idenMuseoFIFA + ", " + museoFIFA[i].getIdentificador();
                }
            
            }
            if(i<36){
                if(extraSticker[i].getCantidad()>0){
                    cantidadExtra = extraSticker[i].getCantidad() + cantidadExtra;
                    costoExtra = extraSticker[i].CostoTotal() + costoExtra;
                    idenExtra = idenExtra + ", " + extraSticker[i].getIdentificador();
                }
            }
            if (postales[i].getCantidad()>0){
                cantidadPostales = postales[i].getCantidad() + cantidadPostales;
                costoPostales = postales[i].CostoTotal() + costoPostales;
                idenPostales = idenPostales + ", " + postales[i].getIdentificador();
            }
            
        }
        Object [ ] [ ] datosFilas ={
            {"Página Principal", cantidadPagPrincipal, costoPagPrincipal, idenPagPrincipal},
            {"Estadios", cantidadEstadios, costoEstadios, idenEstadios},
            {"Extra Sticker", cantidadExtra, costoExtra, idenExtra},
            {"Postales", cantidadPostales, costoPostales, idenPostales},
            {"Museo FIFA", cantidadMuseoFIFA, costoMuseoFIFA, idenMuseoFIFA},
        };
        tablaFinal = new JTable(datosFilas, nombreColumnas);
        
        tablaFinal.setRowHeight(100);
        tablaFinal.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaFinal.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablaFinal.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablaFinal.getColumnModel().getColumn(3).setPreferredWidth(400);
        setBounds(500,300,600,900);
        int confirmacion;
        try{
            confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea que se envíe hasta el domicilio? ");
        } catch (Exception y){
            confirmacion = 1;
        }
        int costoTotal = costoPagPrincipal + costoEstadios + costoExtra + costoPostales + costoMuseoFIFA ; 
        if (confirmacion==0){
            costoTotal = costoTotal + costoEnvio;
        }
        //laminaTabla.add(tablaFinal);
        laminaTabla.add(new JLabel("Nombre:       "+nombre));
        laminaTabla.add(new JLabel("Cedula:        "+cedula));
        laminaTabla.add(new JScrollPane(tablaFinal), BorderLayout.CENTER);
        laminaTabla.add(new JLabel("Costo Total:       "+costoTotal));
        
        
        laminaTabla.setLayout(new BoxLayout(laminaTabla, BoxLayout.PAGE_AXIS));
        add(laminaTabla);
        
        revalidate();
        
    }
    //Genera los labels para cada Sticker con su respectivo identificador e imagen 
    private JLabel [] GenerarLabels(String seccion, int cantidadLabels){
        JLabel labelAyuda[] = new JLabel[cantidadLabels];
        ImageIcon imagenes [] = new ImageIcon[cantidadLabels];
        if (seccion == "Página Principal"){
            for(int j = 0; j<cantidadLabels; j++){
                imagenes[j] = new ImageIcon(new ImageIcon(getClass().getResource(pagPrincipal[j].getImagen())).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                labelAyuda[j] = new JLabel(pagPrincipal[j].getIdentificador(), imagenes[j], SwingConstants.LEFT);
            }
        
        } else if (seccion == "Estadios"){
            for(int j = 0; j<cantidadLabels; j++){
                imagenes[j] = new ImageIcon(new ImageIcon(getClass().getResource(estadios[j].getImagen())).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                labelAyuda[j] = new JLabel(estadios[j].getIdentificador(), imagenes[j], SwingConstants.LEFT);
            }
        } else if (seccion == "Museo FIFA"){
            for(int j = 0; j<cantidadLabels; j++){
                imagenes[j] = new ImageIcon(new ImageIcon(getClass().getResource(museoFIFA[j].getImagen())).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                labelAyuda[j] = new JLabel(museoFIFA[j].getIdentificador(), imagenes[j], SwingConstants.LEFT);
            }
        } else if (seccion == "Extra Sticker"){//Esta linea se debe arreglar
            for(int j = 0; j<cantidadLabels; j++){
                imagenes[j] = new ImageIcon(new ImageIcon(getClass().getResource(extraSticker[j].getImagen())).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                labelAyuda[j] = new JLabel(extraSticker[j].getIdentificador(), imagenes[j], SwingConstants.LEFT);
            }
        } else if (seccion == "Postales"){
            for(int j =0; j < cantidadLabels; j++){
                imagenes[j] = new ImageIcon(new ImageIcon(getClass().getResource(postales[j].getImagen())).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
                labelAyuda[j] = new JLabel(postales[j].getIdentificador(), imagenes[j], SwingConstants.LEFT);
            }
        
        }
        return labelAyuda;
    }
    //Se instancias las clases postales y depende la cantidad de postales que se quieran y la seccion a la que pertenecen 
    private Postal [] GenerarPostales(String seccion, int cantidadPostales){
        Postal postalAyuda[] = new Postal[cantidadPostales];
        if(seccion == "Página Principal"){
            for(int i =0; i < cantidadPostales; i++) {
                postalAyuda[i] = new Postal(seccion, "FWC"+String.valueOf(i) , 150, "/todofut/stickers/FWC"+ String.valueOf(i)+".jpg");
            }
        }else if(seccion == "Estadios"){
            for(int i =0; i < cantidadPostales; i++){
                postalAyuda[i] = new Postal(seccion, "FWC"+String.valueOf(i+8) , 100, "/todofut/stickers/FWC"+ String.valueOf(i+8)+".jpg");
            }
        
        }else if(seccion == "Museo FIFA"){
            for(int i =0; i < cantidadPostales; i++){
                postalAyuda[i] = new Postal(seccion, "FWC"+String.valueOf(i+19) , 150, "/todofut/stickers/FWC"+ String.valueOf(i+19)+".jpg");
            }
        
        }else if(seccion == "Extra Sticker"){
            for(int i =0; i < 12; i++){
                postalAyuda[i] = new Postal(seccion, "BRONZE" , 150, "/todofut/stickers/GOLD"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+12] = new Postal(seccion, "SILVER" , 150, "/todofut/stickers/SILVER"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+24] = new Postal(seccion, "GOLD" , 150, "/todofut/stickers/GOLD"+ String.valueOf(i+1)+".jpg");
            }
        
        }else if(seccion == "Postales"){
            for(int i =0; i < 19; i++){
                postalAyuda[i] = new Postal(seccion, "QAT"+String.valueOf(i+1) , 200, "/todofut/stickers/QAT"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19] = new Postal(seccion, "ECU"+String.valueOf(i+1) , 200, "/todofut/stickers/ECU"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*2] = new Postal(seccion, "ENG"+String.valueOf(i+1) , 200, "/todofut/stickers/ENG"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*3] = new Postal(seccion, "IRN"+String.valueOf(i+1) , 200, "/todofut/stickers/IRN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*4] = new Postal(seccion, "USA"+String.valueOf(i+1) , 200, "/todofut/stickers/USA"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*5] = new Postal(seccion, "WAL"+String.valueOf(i+1) , 200, "/todofut/stickers/WAL"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*6] = new Postal(seccion, "ARG"+String.valueOf(i+1) , 200, "/todofut/stickers/ARG"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*7] = new Postal(seccion, "KSA"+String.valueOf(i+1) , 200, "/todofut/stickers/KSA"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*8] = new Postal(seccion, "MEX"+String.valueOf(i+1) , 200, "/todofut/stickers/MEX"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*9] = new Postal(seccion, "POL"+String.valueOf(i+1) , 200, "/todofut/stickers/POL"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*10] = new Postal(seccion, "FRA"+String.valueOf(i+1) , 200, "/todofut/stickers/FRA"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*11] = new Postal(seccion, "AUS"+String.valueOf(i+1) , 200, "/todofut/stickers/AUS"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*12] = new Postal(seccion, "DEN"+String.valueOf(i+1) , 200, "/todofut/stickers/DEN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*13] = new Postal(seccion, "TUN"+String.valueOf(i+1) , 200, "/todofut/stickers/TUN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*14] = new Postal(seccion, "ESP"+String.valueOf(i+1) , 200, "/todofut/stickers/ESP"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*15] = new Postal(seccion, "GER"+String.valueOf(i+1) , 200, "/todofut/stickers/GER"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*16] = new Postal(seccion, "JPN"+String.valueOf(i+1) , 200, "/todofut/stickers/JPN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*17] = new Postal(seccion, "BEL"+String.valueOf(i+1) , 200, "/todofut/stickers/BEL"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*18] = new Postal(seccion, "CAN"+String.valueOf(i+1) , 200, "/todofut/stickers/CAN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*19] = new Postal(seccion, "MAR"+String.valueOf(i+1) , 200, "/todofut/stickers/MAR"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*20] = new Postal(seccion, "CRO"+String.valueOf(i+1) , 200, "/todofut/stickers/CRO"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*21] = new Postal(seccion, "BRA"+String.valueOf(i+1) , 200, "/todofut/stickers/BRA"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*22] = new Postal(seccion, "SRB"+String.valueOf(i+1) , 200, "/todofut/stickers/SRB"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*23] = new Postal(seccion, "SUI"+String.valueOf(i+1) , 200, "/todofut/stickers/SUI"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*24] = new Postal(seccion, "CMR"+String.valueOf(i+1) , 200, "/todofut/stickers/CMR"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*25] = new Postal(seccion, "POR"+String.valueOf(i+1) , 200, "/todofut/stickers/POR"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*26] = new Postal(seccion, "GHA"+String.valueOf(i+1) , 200, "/todofut/stickers/GHA"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*27] = new Postal(seccion, "URU"+String.valueOf(i+1) , 200, "/todofut/stickers/URU"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*28] = new Postal(seccion, "KOR"+String.valueOf(i+1) , 200, "/todofut/stickers/KOR"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*29] = new Postal(seccion, "SEN"+String.valueOf(i+1) , 200, "/todofut/stickers/SEN"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*30] = new Postal(seccion, "NED"+String.valueOf(i+1) , 200, "/todofut/stickers/NED"+ String.valueOf(i+1)+".jpg");
                postalAyuda[i+19*31] = new Postal(seccion, "CRC"+String.valueOf(i+1) , 200, "/todofut/stickers/CRC"+ String.valueOf(i+1)+".jpg");
            }
        
        }
        return postalAyuda;
    }
    //Se accesa desde el menú y agrega la tabla con los resultados de la compra
    private class GenerarCompra implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //
            GenerarTabla();
        }
    }
    //Sale de la GUI y finaliza el programa
    private class AccionSalir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //
            System.exit(WIDTH);
        }
    }
    //LLama a los diferentes panaeles según la selección de menú
    private class AccionPagPrincipal implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //
            if (e.getSource() == menpPrincipal){
                puntero = 5;
                GenerarLaminaPrincipal(0 , 5, 0);
            } else if(e.getSource() == menextra){
                puntero = 5;
                GenerarLaminaPrincipal(0 , 5, 2);
            } else if(e.getSource() == menestadios){
                puntero = 5;
                GenerarLaminaPrincipal(0 , 5, 1);
            } else if(e.getSource() == menpostales){
                puntero = 20;
                GenerarLaminaPrincipal(0 , 20, 3);
            } else if(e.getSource() == menmuseo){
                puntero = 5;
                GenerarLaminaPrincipal(0 , 5, 4);
            }
        }
    }
    //Sirve para ir a la anterior o siguiente vista del panel
    private class AccionPagPrincipalS implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //
            if (e.getSource() == botonSigPagPrincipal){
                GenerarLaminaPrincipal(5 , 8 , 0);
            } else if (e.getSource() == botonSigEstadios){
                GenerarLaminaPrincipal(puntero , puntero + 5 , 1);
            } else if(e.getSource() == botonSigExtraSticker){
                GenerarLaminaPrincipal(puntero , puntero + 5 , 2);
            } else if(e.getSource() == botonSigPostales){
                GenerarLaminaPrincipal(puntero , puntero + 20 , 3);
            } else if (e.getSource() == botonSigMuseoFIFA){
                GenerarLaminaPrincipal(puntero , puntero + 5 , 4);
            } else if (e.getSource() == botonAntPagPrincipal){
                GenerarLaminaPrincipal(0 , 5 , 0);
            } else if (e.getSource() == botonAntEstadios){
                GenerarLaminaPrincipal(puntero - 5 , puntero, 1);
            }  else if (e.getSource() == botonAntExtraSticker){
                GenerarLaminaPrincipal(puntero - 5 , puntero, 2);
            }  else if (e.getSource() == botonAntPostales){
                GenerarLaminaPrincipal(puntero - 20 , puntero, 3);
            }  else if (e.getSource() == botonAntMuseoFIFA){
                GenerarLaminaPrincipal(puntero - 5 , puntero, 4);
            } 
            
        }
    }
    //Para que en los campos de texto solo se puedan ingresar números
    private class SoloNumeros implements KeyListener{

        @Override
        //Hay que verificar si la tecla pulsada es un digito, esto hace que no se pueda utilizar letras, pero el problema es que no agrega copy, cut, past y eventos con Enter
        public void keyTyped(KeyEvent a) {
            char caracter = a.getKeyChar();
            if ((caracter < '0') || (caracter > '9') &&  (caracter != '\b') ){// \b es la tecla de borrar
                a.consume();
            }
            
        }

        @Override
        public void keyPressed(KeyEvent a) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent a) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
    //Agrega la cantidad escrita en el campo de texto a la clase postal que se encuentra en el mismo subpanel llamado cadaSticker[]
    private class Agregar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //
            int calculo;
            if(cadaSticker[0]. getComponent(0) == labelPagPrincipal[0]){
                for (int i = 0; i < 8; i++){
                    if (e.getSource() == botonPagPrincipal[i]){
                          calculo = Integer.valueOf(textPagPrincipal[i].getText()) + pagPrincipal[i].getCantidad();
                          pagPrincipal[i].setCantidad(calculo);
                          
                    }
                }
            } else if(cadaSticker[0]. getComponent(0) == labelEstadios[0]){
                for (int i = 0; i < 11; i++){
                    if (e.getSource() == botonPagPrincipal[i]){
                          calculo = Integer.valueOf(textPagPrincipal[i].getText()) + estadios[i].getCantidad();
                          estadios[i].setCantidad(calculo);
                    }
                }
            } else if(cadaSticker[0]. getComponent(0) == labelExtraSticker[0]){
                for (int i = 0; i < 8; i++){
                    if (e.getSource() == botonPagPrincipal[i]){
                          calculo = Integer.valueOf(textPagPrincipal[i].getText()) + extraSticker[i].getCantidad();
                          extraSticker[i].setCantidad(calculo);
                    }
                }
            
            } else if(cadaSticker[0]. getComponent(0) == labelPostales[0]){
                for (int i = 0; i < 608; i++){
                    if (e.getSource() == botonPagPrincipal[i]){
                          calculo = Integer.valueOf(textPagPrincipal[i].getText()) + postales[i].getCantidad();
                          postales[i].setCantidad(calculo);
                    }
                }
            
            } else if(cadaSticker[0]. getComponent(0) == labelMuseoFIFA[0]){
                for (int i = 0; i < 11; i++){
                    if (e.getSource() == botonPagPrincipal[i]){
                          calculo = Integer.valueOf(textPagPrincipal[i].getText()) + museoFIFA[i].getCantidad();
                          museoFIFA[i].setCantidad(calculo);
                    }
                }
            }
            
        }
    }
    
}
