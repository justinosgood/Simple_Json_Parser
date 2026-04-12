public class Pointer {
    public String string;
    public char c;
    public int i;

    public Pointer(String string) {
        this.string = string;
    }

    public void nextChar() {
        c = string.charAt(i);
        i++;
    }

    public boolean hasNext() {
        return i < string.length();
    }
}
