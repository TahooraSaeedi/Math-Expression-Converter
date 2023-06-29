import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Scene scene;
    public static Timeline t;

    public static void menu() {
        Pane pane = new Pane();
        pane.getStyleClass().add("menu-pane");
        Label label1 = new Label("Converter Math");
        label1.getStyleClass().add("menu-label");
        label1.setLayoutY(30);
        Label label2 = new Label("Expressions Application!");
        label2.getStyleClass().add("menu-label");
        label2.setLayoutY(80);
        t = new Timeline(new KeyFrame(Duration.seconds(0.6), new EventHandler<ActionEvent>() {
            int i = 0;

            @Override
            public void handle(ActionEvent event) {
                if (i % 2 == 0) {
                    label1.getStyleClass().add("menu-label-flash");
                    label2.getStyleClass().add("menu-label-flash");
                } else {
                    label1.getStyleClass().removeAll("menu-label-flash");
                    label2.getStyleClass().removeAll("menu-label-flash");
                }
                i++;
            }
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        Button start = new Button("Start Converting");
        start.getStyleClass().add("menu-button");
        start.setLayoutX(150);
        start.setLayoutY(177.5);
        start.setOnMouseClicked(e -> {
            startConverting();
        });
        start.setOnMouseEntered(e -> {
            start.getStyleClass().add("menu-buttonIn");
        });
        start.setOnMouseExited(e -> {
            start.getStyleClass().removeAll("menu-buttonIn");
        });
        Button history = new Button("History");
        history.getStyleClass().add("menu-button");
        history.setLayoutX(150);
        history.setLayoutY(285);
        history.setOnMouseClicked(e -> {
            history();
        });
        history.setOnMouseEntered(e -> {
            history.getStyleClass().add("menu-buttonIn");
        });
        history.setOnMouseExited(e -> {
            history.getStyleClass().removeAll("menu-buttonIn");
        });
        Button exit = new Button("Exit");
        exit.getStyleClass().add("menu-button");
        exit.setLayoutX(150);
        exit.setLayoutY(392.5);
        exit.setOnMouseClicked(e -> {
            System.exit(0);
        });
        exit.setOnMouseEntered(e -> {
            exit.getStyleClass().add("menu-buttonIn");
        });
        exit.setOnMouseExited(e -> {
            exit.getStyleClass().removeAll("menu-buttonIn");
        });
        pane.getChildren().addAll(label1, label2, start, history, exit);
        scene.setRoot(pane);
    }

    public static void startConverting() {
        Pane pane = new Pane();
        pane.getStyleClass().add("startConverting-pane");
        Label label1 = new Label("Instructions");
        label1.getStyleClass().add("startConverting-label-1");
        label1.setLayoutX(400);
        label1.setLayoutY(50);
        Label label2 = new Label("1- Each operator or operand should be separated by one space.\n\n" +
                "2- In the input in addition to numbers, you can use variables link x, y, z, etc.\n\n" +
                "3- In the input, you can use +, -, *, /, and ^ operators.\n\n" +
                "4- To use unary negative operator you should use _ character and - character to use binary negative operator.\n\n" +
                "5- In the input you can use these mathematical functions: sin, cos, tan and cot.");
        label2.getStyleClass().add("startConverting-label-2");
        label2.setWrapText(true);
        label2.setLayoutX(410);
        label2.setLayoutY(100);
        TextField input = new TextField();
        input.getStyleClass().add("startConverting-input");
        input.setPromptText("Please enter your expression...");
        input.setLayoutY(50);
        input.setLayoutX(25);
        ComboBox comboBox = new ComboBox();
        comboBox.getStyleClass().add("startConverting-comboBox");
        comboBox.setPromptText("Type of conversion");
        comboBox.setButtonCell(new ListCell() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("-fx-text-fill: rgba(0, 0, 0, 0.3)");
                } else {
                    setStyle("-fx-text-fill: #000000");
                    setText(item.toString());
                }
            }
        });
        comboBox.getItems().add("Prefix To Infix");
        comboBox.getItems().add("Prefix To Postfix");
        comboBox.getItems().add("Infix To Prefix");
        comboBox.getItems().add("Infix To Postfix");
        comboBox.getItems().add("Postfix To Prefix");
        comboBox.getItems().add("Postfix To Infix");
        comboBox.setLayoutY(125);
        comboBox.setLayoutX(25);
        Button convert = new Button("Convert");
        convert.getStyleClass().add("startConverting-button");
        convert.setLayoutX(50);
        convert.setLayoutY(225);
        convert.setOnMouseClicked(e -> {
            String expression = "";
            String type = "Prefix To Infix";
            if (input.getText() == null || input.getText().length() == 0 || comboBox.getValue() == null) {
                error(2);
            } else {
                expression = input.getText();
                type = (String) comboBox.getValue();
                int typeNumber = 1;
                switch (type) {
                    case "Prefix To Infix": {
                        typeNumber = 1;
                        break;
                    }
                    case "Prefix To Postfix": {
                        typeNumber = 2;
                        break;
                    }
                    case "Infix To Prefix": {
                        typeNumber = 3;
                        break;
                    }
                    case "Infix To Postfix": {
                        typeNumber = 4;
                        break;
                    }
                    case "Postfix To Prefix": {
                        typeNumber = 5;
                        break;
                    }
                    case "Postfix To Infix": {
                        typeNumber = 6;
                        break;
                    }
                }
                convert(expression, typeNumber);
            }
        });
        convert.setOnMouseEntered(e -> {
            convert.getStyleClass().add("startConverting-buttonIn");
        });
        convert.setOnMouseExited(e -> {
            convert.getStyleClass().removeAll("startConverting-buttonIn");
        });
        Button menu = new Button("Back To Menu");
        menu.getStyleClass().add("startConverting-button");
        menu.setLayoutX(50);
        menu.setLayoutY(330);
        menu.setOnMouseClicked(e -> {
            menu();
        });
        menu.setOnMouseEntered(e -> {
            menu.getStyleClass().add("startConverting-buttonIn");
        });
        menu.setOnMouseExited(e -> {
            menu.getStyleClass().removeAll("startConverting-buttonIn");
        });
        pane.getChildren().addAll(label1, label2, comboBox, input, menu, convert);
        scene.setRoot(pane);
    }

    public static void history() {
        Pane pane = new Pane();
        pane.getStyleClass().add("history-pane");
        Converter[] ascendingArray = new Sort().bubbleSortAscending(Converter.getArray());
        String ascending = "";
        for (Converter converter : ascendingArray) {
            if (converter != null) {
                ascending += converter;
            }
        }
        Converter[] descendingArray = new Sort().bubbleSortDescending(Converter.getArray());
        String descending = "";
        for (Converter converter : descendingArray) {
            if (converter != null) {
                descending += converter;
            }
        }
        Label label1 = new Label("Ascending");
        label1.getStyleClass().add("history-label-1");
        label1.setLayoutX(27.5);
        label1.setLayoutY(27.5);
        Label label2 = new Label("Descending");
        label2.getStyleClass().add("history-label-1");
        label2.setLayoutX(313.75);
        label2.setLayoutY(27.5);
        Label label3 = new Label(ascending);
        label3.getStyleClass().add("history-label-2");
        label3.setLayoutX(27.5);
        label3.setLayoutY(105);
        Label label4 = new Label(descending);
        label4.getStyleClass().add("history-label-2");
        label4.setLayoutX(313.75);
        label4.setLayoutY(105);
        Button menu = new Button("Back To Menu");
        menu.getStyleClass().add("startConverting-button");
        menu.setLayoutX(150);
        menu.setLayoutY(392.5);
        menu.setOnMouseClicked(e -> {
            menu();
        });
        menu.setOnMouseEntered(e -> {
            menu.getStyleClass().add("startConverting-buttonIn");
        });
        menu.setOnMouseExited(e -> {
            menu.getStyleClass().removeAll("startConverting-buttonIn");
        });
        pane.getChildren().addAll(label1, label2, label3, label4, menu);
        scene.setRoot(pane);
    }

    public static void convert(String expression, int type) {
        Pane pane = new Pane();
        pane.getStyleClass().add("convert-pane");
        Label label1 = new Label("Input");
        label1.getStyleClass().add("convert-label");
        label1.setLayoutY(10);
        label1.setLayoutX(50);
        Label label2 = new Label("Output");
        label2.getStyleClass().add("convert-label");
        label2.setLayoutY(130);
        label2.setLayoutX(50);
        TextArea input = new TextArea(expression);
        input.getStyleClass().add("convert-input");
        input.editableProperty().setValue(false);
        input.setLayoutY(70);
        input.setLayoutX(50);
        String result = "";
        boolean check = true;
        try {
            result = new Converter(type).convert(expression);
        } catch (InvalidExpressionException e) {
            check = false;
        }
        if (!check) {
            error(1);
        } else {
            TextArea output = new TextArea(result);
            output.getStyleClass().add("convert-output");
            output.editableProperty().setValue(false);
            output.setLayoutY(190);
            output.setLayoutX(50);
            Button menu = new Button("Back To Menu");
            menu.getStyleClass().add("startConverting-button");
            menu.setLayoutX(150);
            menu.setLayoutY(410);
            menu.setOnMouseClicked(e -> {
                menu();
            });
            menu.setOnMouseEntered(e -> {
                menu.getStyleClass().add("startConverting-buttonIn");
            });
            menu.setOnMouseExited(e -> {
                menu.getStyleClass().removeAll("startConverting-buttonIn");
            });
            pane.getChildren().addAll(label1, label2, input, output, menu);
            scene.setRoot(pane);
        }
    }

    public static void error(int type) {
        String string = "";
        switch (type) {
            case 1: {
                string = "Invalid Input Entered!\nPlease Try Again!";
                break;
            }
            case 2: {
                string = "All Fields Should Be Filled!\nPlease Try Again!";
                break;
            }
        }
        Pane pane = new Pane();
        pane.getStyleClass().add("error-pane");
        Rectangle r = new Rectangle(300, 250);
        r.getStyleClass().add("error-rectangle");
        r.setLayoutX(150);
        r.setLayoutY(125);
        Label label = new Label(string);
        label.getStyleClass().add("error-label");
        label.setWrapText(true);
        label.setLayoutX(150);
        label.setLayoutY(150);
        Button ok = new Button("OK");
        ok.getStyleClass().add("error-button");
        ok.setLayoutX(200);
        ok.setLayoutY(270);
        ok.setOnMouseClicked(e -> {
            startConverting();
        });
        ok.setOnMouseEntered(e -> {
            ok.getStyleClass().add("error-buttonIn");
        });
        ok.setOnMouseExited(e -> {
            ok.getStyleClass().removeAll("error-buttonIn");
        });
        pane.getChildren().addAll(r, label, ok);
        scene.setRoot(pane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        scene = new Scene(pane, 600, 500);
        scene.getStylesheets().add("file:style//style.css");
        menu();
        stage.setTitle("Converter Application");
        stage.getIcons().add(new Image("file:image//icon.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}