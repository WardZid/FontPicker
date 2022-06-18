package fontpicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FontPickerController implements Initializable {
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;

    @FXML
    private TextField fontTF1;

    @FXML
    private TextField fontTF2;

    @FXML
    private HBox styleHBox1;

    @FXML
    private HBox styleHBox2;

    @FXML
    private ComboBox<FontPosture> postureCB1;

    @FXML
    private ComboBox<FontPosture> postureCB2;

    @FXML
    private Spinner<Integer> sizeSpinner1;

    @FXML
    private Spinner<Integer> sizeSpinner2;

    @FXML
    private ComboBox<FontWeight> weightCB1;

    @FXML
    private ComboBox<FontWeight> weightCB2;

    @FXML
    private TextArea typeTA1;

    @FXML
    private TextArea typeTA2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> fonts=new ArrayList<>();
        fonts.addAll(Font.getFamilies());
        for (String font: fonts) {
            vbox1.getChildren().add(fontItem(font, 1));
            vbox2.getChildren().add(fontItem(font, 2));
        }


        for(FontWeight fw : FontWeight.values()){
            weightCB1.getItems().add(fw);
            weightCB2.getItems().add(fw);
        }
        weightCB1.getSelectionModel().selectFirst();
        weightCB1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println(fontTF1);
            fontTF1.setFont(Font.font(fontTF1.getText(), newValue, postureCB1.getValue(), sizeSpinner1.getValue()));
        });
        weightCB2.getSelectionModel().selectFirst();
        weightCB2.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue==null)
                return;
            fontTF2.setFont(Font.font(fontTF2.getText(), newValue, postureCB2.getValue(), sizeSpinner2.getValue()));
        });


        for(FontPosture fp : FontPosture.values()){
            postureCB1.getItems().add(fp);
            postureCB2.getItems().add(fp);
        }
        postureCB1.getSelectionModel().selectFirst();
        postureCB1.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue==null)
                return;
            fontTF1.setFont(Font.font(fontTF1.getText(), weightCB1.getValue(), newValue, sizeSpinner1.getValue()));
        });
        postureCB2.getSelectionModel().selectFirst();
        postureCB2.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue==null)
                return;
            fontTF2.setFont(Font.font(fontTF2.getText(), weightCB2.getValue(), newValue, sizeSpinner2.getValue()));
        });

    }
    private TextField fontItem(String font,int side){
        TextField tf=new TextField(font);
        tf.setFont(Font.font(font, FontWeight.NORMAL, FontPosture.REGULAR, 20));
        tf.setEditable(false);
        tf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                if(side==1){
                    styleHBox1.setDisable(false);
                    selectFont(font, fontTF1, typeTA1, weightCB1, postureCB1,sizeSpinner1);
                }else if(side==2){
                    styleHBox2.setDisable(false);
                    selectFont(font, fontTF2, typeTA2, weightCB2, postureCB2,sizeSpinner2);
                }
            }
        });
        tf.focusTraversableProperty().set(false);
        return tf;
    }

    private void selectFont(String font, TextField fontTF, TextArea typeTA, ComboBox<FontWeight> weightCB, ComboBox<FontPosture> postureCB,Spinner<Integer> sizeSpinner) {
        fontTF.setText(font);
        fontTF.setFont(Font.font(font, FontWeight.NORMAL, FontPosture.REGULAR, 20));
        typeTA.setFont(Font.font(font, FontWeight.NORMAL, FontPosture.REGULAR, 20));
        weightCB.getSelectionModel().select(FontWeight.NORMAL);
        postureCB.getSelectionModel().select(FontPosture.REGULAR);


    }
}


