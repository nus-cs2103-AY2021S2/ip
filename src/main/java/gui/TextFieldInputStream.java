package gui;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.InputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class TextFieldInputStream extends InputStream {
    public TextField field;

    ArrayBlockingQueue<Byte> buffer;

    public TextFieldInputStream() {
        super();
        field = new TextField();
        buffer = new ArrayBlockingQueue<Byte>(1000);

        field.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    buffer.addAll(Arrays.asList(toObject((field.getText()+"\n").getBytes()).clone()));
                    field.setText("");
                }
            }
        });
    }

    public static Byte[] toObject(byte[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new Byte[0];
        }
        final Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public int read() throws IOException {
        try {
            return buffer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
