import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text {
    private int value;

    public Score() {
        value = 0;
        setFont(new Font("serif", 20));
        setFill(Color.ROYALBLUE);
        updateDisplay();
    }

    void updateDisplay() {
        setText(value + "");
    }

    int getValue() {
        return value;
    }

    void setValue(int newVal) {
        value = newVal;
        updateDisplay();
    }
}
