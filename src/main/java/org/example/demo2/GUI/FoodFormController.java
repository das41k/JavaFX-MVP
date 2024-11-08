package org.example.demo2.GUI;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import org.example.demo2.Models.Chocolate;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodFormController implements Initializable {

    public ChoiceBox cmbFoodType;
    public TextField txtFoodTitle;
    public TextField txtFoodKkal;

    public VBox fruitPane;
    public CheckBox chkIsFresh;

    public HBox chocolatePane;
    public ChoiceBox cmbChocolateType;

    public VBox cookiePane;
    public CheckBox chkWithSugar;
    public CheckBox chkWithPoppy;
    public CheckBox chkWithSesame;

    final String FOOD_FRUIT = "Фрукт";
    final String FOOD_CHOCOLATE = "Шоколадка";
    final String FOOD_COOKIE = "Булочка";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbFoodType.setItems(FXCollections.observableArrayList(
                FOOD_FRUIT,
                FOOD_CHOCOLATE,
                FOOD_COOKIE
        ));

        cmbFoodType.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            updatePanes((String) newValue);
        });

        cmbChocolateType.setConverter(new StringConverter <Chocolate.Type>() {
            @Override
            public String toString(Chocolate.Type object) {
                switch (object) {
                    case WHITE:
                        return "Белый";
                    case BLACK:
                        return "Черный";
                    case MILK:
                        return "Молочный";
                }
                return null;
            }

            @Override
            public Chocolate.Type fromString(String string) {
                return null;
            }
        });
        updatePanes(" ");
    }
    public void updatePanes(String newValue) {
        this.fruitPane.setVisible(newValue.equals(FOOD_FRUIT));
        this.fruitPane.setManaged(newValue.equals(FOOD_FRUIT));
        this.chocolatePane.setVisible(newValue.equals(FOOD_CHOCOLATE));
        this.chocolatePane.setManaged(newValue.equals(FOOD_CHOCOLATE));
        this.cookiePane.setVisible(newValue.equals(FOOD_COOKIE));
        this.cookiePane.setManaged(newValue.equals(FOOD_COOKIE));
    }
}
