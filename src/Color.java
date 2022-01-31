import java.util.Objects;

public class Color {

    public int r;
    public int g;
    public int b;

    public Color(int r, int g, int b) {

        this.r = r;
        this.g = g;
        this.b = b;

    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return r == color.r && g == color.g && b == color.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
}
