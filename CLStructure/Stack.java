package CLStructure;

// 栈
// 作者：程澜
// 时间：9/25/2023

/**
 * 程澜的栈
 */
public class Stack<T> {
    private LinkList<T> list;
    private int capacity;

    /**
     * 
     */
    public Stack() {
        this.list = new LinkList<T>();
        this.capacity = -1; // 代表容量无限
    }

    public Stack(int capacity) {
        this.list = new LinkList<T>();
        this.capacity = capacity;
    }

    public boolean push(T value) {
        if (this.capacity == list.getLength()) {
            return false;
        }
        this.list.insert(0, value);
        return true;
    }

    public T peak() {
        return list.getValue(0);
    }

    public T pop() {
        return list.removeByIndex(0);
    }

    public void show() {
        int max_length = -1;
        Object[] l = this.list.to_list();
        for (Object integer : l) {
            String s = integer.toString();
            if (s.length() > max_length)
                max_length = s.length();
        }

        if (this.capacity == -1) {
            System.out.println("栈中停留<" + list.getLength() + ">个元素");
        } else {
            System.out.println("栈中停留<" + list.getLength() + ">个元素，剩余空间为<" + (this.capacity - list.getLength()) + ">:");
        }

        System.out.print("top -->");

        for (int i = 0; i < l.length; i++) { // print the stack from the top to the bottom
            String s = l[i].toString();
            String space = "";
            for (int j = 0; j < max_length - s.length(); j++) {
                space += " ";
            }
            System.out.println("\t| " + s + space + " |");
        }
        String line = "";
        for (int i = 0; i < max_length; i++) {
            line += "-";
        }
        line = "-" + line + "-";
        System.out.println("\t+" + line + "+");
    }
}
