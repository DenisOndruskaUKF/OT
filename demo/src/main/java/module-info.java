module sk.ukf.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens sk.ukf.demo to javafx.fxml;
    exports sk.ukf.demo;
}