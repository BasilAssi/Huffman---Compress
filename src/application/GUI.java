//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.stage.FileChooser;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//
//import java.io.File;
//
//public class GUI extends Application {
//
//    Stage stage;
//    GridPane gridPane;
//    double width, height;
//    TextArea displayTextArea = new TextArea();
//    Label msg = new Label("");
//
//    Compress compressFile;
//
//    /**
//     * Menu components
//     */
//    MenuItem compressFileMenuItem = new MenuItem("Compress File");
//    MenuItem decompressFileMenuItem = new MenuItem("Decompress File");
//    MenuItem exitMenuItem = new MenuItem("Exit");
//    MenuItem statsMenuItem = new MenuItem("Show Statistics");
//    MenuItem headerMenuItem = new MenuItem("Display Header");
//    MenuItem hoffmanMenuItem = new MenuItem("Display Hoffman Code");
//    MenuItem frequencyMenuItem = new MenuItem("Display Frequency");
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        stage = primaryStage;
//        gridPane = setupScreen();
//        setupBindings();
//        Scene scene = new Scene(gridPane, width, height);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Compress/Decompress File");
//        primaryStage.show();
//
//    }
//
//    private GridPane setupScreen() {
//        GridPane root = new GridPane();
//
//        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//        width = 600;
//        height = 350;
//
//        GridPane mainGrid = new GridPane();
//
//        mainGrid.setVgap(5);
//        mainGrid.setHgap(5);
//        GridPane.setMargin(mainGrid, new Insets(10, 10, 10, 10));
//
//
//        /** Grid for editor section **/
//        GridPane displayGrid = new GridPane();
//        displayGrid.setVgap(5);
//        displayGrid.setHgap(5);
//        displayGrid.setMinHeight(300);
//        displayTextArea.setMinHeight(250);
//        displayTextArea.setEditable(false);
//        displayTextArea.setWrapText(true);
//        displayGrid.add(msg, 0, 1);
//        displayGrid.add(displayTextArea, 0, 2);
//        mainGrid.add(setupMenus(), 0, 0, 2, 1);
//        mainGrid.add(displayGrid, 0, 1);
//
//        root.add(mainGrid, 0, 0);
//        return root;
//    }
//
//    MenuBar setupMenus() {
//        MenuBar menuBar = new MenuBar();
//        Menu fileMenu = new Menu("File");
//        Menu displayMenu = new Menu("Display");
//
//        fileMenu.getItems().addAll(compressFileMenuItem, decompressFileMenuItem, new SeparatorMenuItem(), exitMenuItem);
//        displayMenu.getItems().addAll(statsMenuItem, headerMenuItem, hoffmanMenuItem, frequencyMenuItem);
//        menuBar.getMenus().addAll(fileMenu, displayMenu);
//        statsMenuItem.setDisable(true);
//        hoffmanMenuItem.setDisable(true);
//        headerMenuItem.setDisable(true);
//        frequencyMenuItem.setDisable(true);
//        return menuBar;
//    }
//
//    void setupBindings() {
//
//        FileChooser fileChooser = new FileChooser();
//
//        compressFileMenuItem.setOnAction(e -> {
//            File selectedFile = fileChooser.showOpenDialog(stage);
//            msg.setText("");
//            statsMenuItem.setDisable(false);
//            headerMenuItem.setDisable(true);
//            hoffmanMenuItem.setDisable(true);
//            frequencyMenuItem.setDisable(true);
//            String File = selectedFile.getPath();
//            compressFile = new Compress(File);
//
//            try {
//                compressFile.readFile();
//                compressFile.createHeap();
//                compressFile.writeHuffmanCode();
//                msg.setText("Compression Done ");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//
//        decompressFileMenuItem.setOnAction(e -> {
//            File selectedFile = fileChooser.showOpenDialog(stage);
//            msg.setText("");
//            statsMenuItem.setDisable(true);
//            headerMenuItem.setDisable(true);
//            hoffmanMenuItem.setDisable(true);
//            frequencyMenuItem.setDisable(true);
//
//            String File = selectedFile.getAbsolutePath().replace("\\", "\\\\");
//            if (File.endsWith(".huf")) {
//                msg.setText("In progress... ");
//                Decompress decompress = new Decompress(File);
//                try {
//                    decompress.readHuffFile();
//                    msg.setText("Decompression Done ");
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            } else {
//                msg.setText("Selected file should be (.huf) file, choose another one.");
//            }
//        });
//
//        exitMenuItem.setOnAction(e->{
//            System.exit(1);
//        });
//
//        statsMenuItem.setOnAction(e -> {
//            frequencyMenuItem.setDisable(false);
//            hoffmanMenuItem.setDisable(false);
//            headerMenuItem.setDisable(false);
//            displayTextArea.setText( "\nOrginal size :"+compressFile.numOfByte +  "\ncompress file Size: " + compressFile.HuffnumOfByte
//                    +"\nRatio : "+compressFile.ratio+ "%" );
//        });
//
//        headerMenuItem.setOnAction(e->{
//            Header header = compressFile.getHeader();
//            displayTextArea.setText("File Name: " + header.getFileName()
//                    + "\nASCII " + "  " + "Count \n" );
//            //view on screen
//            for (int i=0 ; i<header.getBytes().length ; i++)
//                displayTextArea.setText(displayTextArea.getText() + header.getBytes()[i]
//                        + "  " + header.getCount()[i] + "\n");
//
//        });
//
//        hoffmanMenuItem.setOnAction(e->{
//            displayTextArea.setText("ASCII \t Huffman Code \n");
//            Huffman table[] = compressFile.getHuffTable();
//            for (int i =0 ; i <table.length ; i++)
//                displayTextArea.setText(displayTextArea.getText() + table[i].byteCount + " \t " +table[i].Huffman + "\n");
//
//        });
//
//        frequencyMenuItem.setOnAction(e->{
//            displayTextArea.setText("ASCII \t frequency \n");
//            Counter counter[] = compressFile.counter;
//            for (int i =0 ; i <counter.length ; i++)
//                displayTextArea.setText(displayTextArea.getText() + counter[i].byteCount + " \t " +counter[i].intCount + "\n");
//        });
//
//    }
//}
