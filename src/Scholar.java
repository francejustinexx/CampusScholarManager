import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Scholar {
    private IntegerProperty id;
    private StringProperty fullName;
    private StringProperty program;
    private StringProperty yearLevel;
    private StringProperty status;

    public Scholar(int id, String fullName, String program, String yearLevel, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.program = new SimpleStringProperty(program);
        this.yearLevel = new SimpleStringProperty(yearLevel);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public String getFullName() {
        return fullName.get();
    }

    public String getProgram() {
        return program.get();
    }

    public String getYearLevel() {
        return yearLevel.get();
    }

    public String getStatus() {
        return status.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty programProperty() {
        return program;
    }

    public StringProperty yearLevelProperty() {
        return yearLevel;
    }

    public StringProperty statusProperty() {
        return status;
    }
}
