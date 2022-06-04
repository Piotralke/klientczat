module com.example.klientczat {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.hateoas;
    requires spring.web;
    requires com.google.gson;
    requires retrofit2;
    requires retrofit2.converter.gson;


    opens com.example.klientczat to javafx.fxml;
    exports com.example.klientczat;
}

