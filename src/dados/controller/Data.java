package dados.controller;

import javafx.beans.property.SimpleStringProperty;

public class Data {
	    private final SimpleStringProperty column1;
	    private final SimpleStringProperty column2;
	    private final SimpleStringProperty column3;
	    // ...

	    Data(String column1, String column2, String column3) {
	        this.column1 = new SimpleStringProperty(column1);
	        this.column2 = new SimpleStringProperty(column2);
	        this.column3 = new SimpleStringProperty(column3);
	        // ...
	    }

	    public String getColumn1() {
	        return column1.get();
	    }

	    public SimpleStringProperty column1Property() {
	        return column1;
	    }

	    public void setColumn1(String column1) {
	        this.column1.set(column1);
	    }

	    public String getColumn2() {
	        return column2.get();
	    }

	    public SimpleStringProperty column2Property() {
	        return column2;
	    }

	    public void setColumn2(String column2) {
	        this.column2.set(column2);
	    }

	    public String getColumn3() {
	        return column3.get();
	    }

	    public SimpleStringProperty column3Property() {
	        return column3;
	    }

	    public void setColumn3(String column3) {
	        this.column3.set(column3);
	    }
	    
	


}
