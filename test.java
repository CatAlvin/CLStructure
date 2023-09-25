import CLStructure.LinkList;

public class test {
    public static void main(String[] args) {
        Integer arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        LinkList<Integer> l = new LinkList<Integer>(arr);
        LinkList<Integer> l_reversed = l.reversed();
        l_reversed.show();
    }
}
