package email.com.gmail.ttsai0509.escpos.command;

import java.io.IOException;
import java.io.OutputStream;

public enum FeedLines implements Command {

    Instance;

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(0x1B);
        out.write(0x64);
        out.write(1);
    }

    public void write(OutputStream out, int lines) throws IOException {
        out.write(0x1B);
        out.write(0x64);
        out.write(lines);
    }
}
