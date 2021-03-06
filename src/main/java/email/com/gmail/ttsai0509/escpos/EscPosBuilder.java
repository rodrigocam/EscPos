package email.com.gmail.ttsai0509.escpos;

import email.com.gmail.ttsai0509.escpos.command.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

public class EscPosBuilder {

    private final ByteArrayOutputStream out;

    public EscPosBuilder() {
        this.out = new ByteArrayOutputStream();
    }

    public EscPosBuilder raw(int val) {
        try {
            Raw.Instance.write(out, val);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return this;
    }

    public EscPosBuilder raw(byte val) {
        try {
            Raw.Instance.write(out, val);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return this;
    }

    public EscPosBuilder raw(byte... vals) {
        if (vals != null)
            try {
                Raw.Instance.write(out, vals);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        return this;
    }

    public EscPosBuilder text(String text) {
        if (text != null)
            try {
                Raw.Instance.write(out, text);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        return this;
    }

    public EscPosBuilder initialize() {
        Initialize.Instance.uncheckedWrite(out);
        return this;
    }

    public EscPosBuilder feed() {
        Feed.Instance.uncheckedWrite(out);
        return this;
    }

    public EscPosBuilder feed(int lines) {
        try {
            FeedLines.Instance.write(out, lines <= 0 ? 1 : lines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return this;
    }

    public EscPosBuilder font(Font font) {
        if (font != null)
            font.uncheckedWrite(out);
        return this;
    }

    public EscPosBuilder align(Align align) {
        if (align != null)
            align.uncheckedWrite(out);
        return this;
    }

    public EscPosBuilder cut(Cut cut) {
        if (cut != null)
            cut.uncheckedWrite(out);
        return this;
    }

    public EscPosBuilder kick(DrawerKick kick) {
        if (kick != null)
            try {
                kick.write(out);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        return this;
    }

    public EscPosBuilder kick(DrawerKick kick, int t1Pulse, int t2Pulse) {
        if (kick != null)
            try {
                kick.write(out, t1Pulse <= 0 ? 0 : t1Pulse, t2Pulse <= 0 ? 0 : t2Pulse);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        return this;
    }

    public byte[] getBytes() {
        return out.toByteArray();
    }

    public EscPosBuilder reset() {
        out.reset();
        return this;
    }

    @Override
    public String toString() {
        return out.toString();
    }

}