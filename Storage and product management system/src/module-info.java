module vittutoimi {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	//requires mysql.connector.java;
	
	opens application to javafx.graphics, javafx.fxml;
}
