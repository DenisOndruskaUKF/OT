module sk.ukf.hw1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens sk.ukf.hw1 to javafx.fxml;
    exports sk.ukf.hw1;
}