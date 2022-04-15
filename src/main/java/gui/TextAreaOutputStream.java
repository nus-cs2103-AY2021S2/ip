package gui;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.OutputStream;

public class TextAreaOutputStream extends OutputStream {
    public TextArea textArea;

    public TextAreaOutputStream() {
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setMaxHeight(9999999);
        textArea.setFont(Font.font("monospaced"));
    }

    @Override
    public void write(int b) throws IOException {
        textArea.appendText(new String(new char[]{(char)b}));
    }

}
