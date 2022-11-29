package TestDataFactory;

public abstract class ReadCounter {
    private static int count;

    public static void addCount(){
        count += 1;
    }
    public static int getCount(){
        return count;
    }
}
