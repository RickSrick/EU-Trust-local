module com.broject.eutrustlocal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires org.json;

    opens com.broject.eutrustlocal to javafx.fxml;
    exports com.broject.eutrustlocal;
    exports com.broject.eutrustlocal.Command;
    opens com.broject.eutrustlocal.Command to javafx.fxml;
}