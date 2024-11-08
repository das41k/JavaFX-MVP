package org.example.demo2.GUI;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.demo2.Models.Chocolate;
import org.example.demo2.Models.Cookie;
import org.example.demo2.Models.Food;
import org.example.demo2.Models.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TableView mainTable;
    ObservableList<Food> foodList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        foodList.add(new Fruit(100, "Яблоко", true));
        foodList.add(new Chocolate(200, "шоколад Аленка", Chocolate.Type.MILK));
        foodList.add(new Cookie(300, "сладкая булочка с Маком", true, true, false));

        mainTable.setItems(foodList);

        TableColumn<Food, String> titleColumn = new TableColumn<>("Название");
        // указываем какое поле брать у модели Food
        // в данном случае title, кстати именно в этих целях необходимо было создать getter и setter для поля title
        //   titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(cellData -> {
            // плюс надо обернуть возвращаемое значение в обертку свойство
            return new SimpleStringProperty(cellData.getValue().getTitle());
        });


        // тоже самое для калорийности
        TableColumn<Food, String> kkalColumn = new TableColumn<>("Калорийность");
        // kkalColumn.setCellValueFactory(new PropertyValueFactory<>("kkal"));
//v-1
        kkalColumn.setCellValueFactory(cellData -> {
            // плюс надо обернуть возвращаемое значение в обертку свойство
            return new SimpleStringProperty(String.valueOf(cellData.getValue().getKkal()));
        });

        // добавляем столбец с описанием
        TableColumn<Food, String> descriptionColumn = new TableColumn<>("Описание");
        // если хотим что-то более хитрое выводить, то используем лямбда выражение
        descriptionColumn.setCellValueFactory(cellData -> {
            // плюс надо обернуть возвращаемое значение в обертку свойство
            return new SimpleStringProperty(cellData.getValue().getDescription());
        });

        // добавляем сюда descriptionColumn
        mainTable.getColumns().addAll(titleColumn, kkalColumn, descriptionColumn);

    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/example/demo2/FoodForm.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());
        stage.showAndWait();

        FoodFormController controller = loader.getController();

        if (controller.getModalResult()) {
            Food newFood = controller.getFood();
            this.foodList.add(newFood);
        }
    }
}