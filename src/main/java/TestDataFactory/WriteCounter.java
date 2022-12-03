package TestDataFactory;

public abstract class WriteCounter {
    private static int count;

    public static void addCount(){
        count += 1;
    }
    public static int getCount(){
        return count;
    }
}
