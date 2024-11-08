package org.example.demo2.GUI;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.demo2.Models.Chocolate;
import org.example.demo2.Models.Cookie;
import org.example.demo2.Models.Food;
import org.example.demo2.Models.Fruit;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodFormController implements Initializable {
    private Boolean modalResult = false;
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

        cmbChocolateType.getItems().setAll(
                Chocolate.Type.WHITE,
                Chocolate.Type.BLACK,
                Chocolate.Type.MILK
        );

        cmbChocolateType.setConverter(new StringConverter <Chocolate.Type>() {
            @Override
            public String toString(Chocolate.Type object) {
                if (object == null) {
                    return "";
                }
                switch (object) {
                    case WHITE:
                        return "Белый";
                    case BLACK:
                        return "Черный";
                    case MILK:
                        return "Молочный";
                }
                return "";
            }

            @Override
            public Chocolate.Type fromString(String string) {
                return null;
            }
        });
        updatePanes("");
    }
    public void updatePanes(String newValue) {
        this.fruitPane.setVisible(newValue.equals(FOOD_FRUIT));
        this.fruitPane.setManaged(newValue.equals(FOOD_FRUIT));
        this.chocolatePane.setVisible(newValue.equals(FOOD_CHOCOLATE));
        this.chocolatePane.setManaged(newValue.equals(FOOD_CHOCOLATE));
        this.cookiePane.setVisible(newValue.equals(FOOD_COOKIE));
        this.cookiePane.setManaged(newValue.equals(FOOD_COOKIE));
    }

    public void onSaveClick(ActionEvent actionEvent) {
        this.modalResult = true; // ставим результат модального окна на true
        // закрываем окно к которому привязана кнопка
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onCancelClick(ActionEvent actionEvent) {
        this.modalResult = false; // ставим результат модального окна на false
        // закрываем окно к которому привязана кнопка
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    // геттер для результата модального окна
    public Boolean getModalResult() {
        return modalResult;
    }

    public void setFood(Food food) {
        // делаем так что если объект редактируется, то нельзя переключать тип
        this.cmbFoodType.setDisable(food != null);
        if (food != null) {
            // ну а тут стандартное заполнение полей в соответствии с переданной едой
            this.txtFoodKkal.setText(String.valueOf(food.getKkal()));
            this.txtFoodTitle.setText(food.getTitle());

            if (food instanceof Fruit) { // если фрукт
                this.cmbFoodType.setValue(FOOD_FRUIT);
                this.chkIsFresh.setSelected(((Fruit) food).isFresh);
            } else if (food instanceof Cookie) { // если булочка
                this.cmbFoodType.setValue(FOOD_COOKIE);
                this.chkWithSugar.setSelected(((Cookie) food).withSugar);
                this.chkWithPoppy.setSelected(((Cookie) food).withPoppy);
                this.chkWithSesame.setSelected(((Cookie) food).withSesame);
            } else if (food instanceof Chocolate) { // если шоколад
                this.cmbFoodType.setValue(FOOD_CHOCOLATE);
                this.cmbChocolateType.setValue(((Chocolate) food).type);
            }
        }
    }
    public Food getFood() {
        Food result = null;
        int kkal = Integer.parseInt(this.txtFoodKkal.getText());
        String title = this.txtFoodTitle.getText();
        switch ((String)this.cmbFoodType.getValue()) {
            case FOOD_CHOCOLATE:
                result = new Chocolate(kkal, title, (Chocolate.Type)this.cmbChocolateType.getValue());
                break;
            case FOOD_COOKIE:
                result = new Cookie(
                        kkal,
                        title,
                        this.chkWithSugar.isSelected(),
                        this.chkWithPoppy.isSelected(),
                        this.chkWithSesame.isSelected()
                );
                break;
            case FOOD_FRUIT:
                result = new Fruit(kkal, title, this.chkIsFresh.isSelected());
                break;
        }
        return result;
    }
}
