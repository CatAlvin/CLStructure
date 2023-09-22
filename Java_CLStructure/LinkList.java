package Java_CLStructure;

// 链表
// 作者：程澜
// 时间：9/21/2023

/**
 * 程澜的链表类
 */
public class LinkList<T> {
    private LinkListNode<T> head;
    private int length;

    /**
     * 创建一个空链表
     * 
     * @return 一个空链表
     */
    public LinkList() {
        this.head = null;
    }

    /**
     * 将传入的列表转换为链表
     * 
     * @param list 包含T类型的数组
     * @return 一个由列表转换的链表
     */
    public LinkList(T[] list) {
        if (list == null) {
            return;
        }
        this.head = new LinkListNode<T>(list[0]);
        int n = list.length;
        LinkListNode<T> cur = this.head;
        for (int i = 1; i < n; i++) {
            cur.setNext(new LinkListNode<T>(list[i]));
            cur = cur.getNext();
        }
        this.length = n;
    }

    /**
     * 创建一个大小为size, val为null的链表
     * 
     * @param size 包含size个结点
     * @return 一个大小为size的默认链表
     */
    public LinkList(int size) {
        if (size <= 0) {
            return;
        }
        this.head = new LinkListNode<T>();
        LinkListNode<T> cur = this.head;
        for (int i = 1; i < size; i++) {
            cur.setNext(new LinkListNode<T>());
            cur = cur.getNext();
        }
        this.length = size;
    }

    /**
     * 创建一个大小为size, val为输入值的链表
     * 
     * @param size 包含size个结点
     * @param val  结点值初始化为val
     * @return 一个大小为size，结点值都为val的链表
     */
    public LinkList(int size, T val) {
        if (size <= 0) {
            return;
        }
        this.head = new LinkListNode<T>(val);
        LinkListNode<T> cur = this.head;
        for (int i = 1; i < size; i++) {
            cur.setNext(new LinkListNode<T>(val));
            cur = cur.getNext();
        }
        this.length = size;
    }

    /**
     * 创建一个以head为头节点，长度为length的链表
     * 
     * @param head   头节点
     * @param length 链表长度
     * @return 一个以传入结点为头节点的链表
     */
    private LinkList(LinkListNode<T> head, int length) {
        this.head = head;
        this.length = length;
    }

    /**
     * 获取链表元素长度
     * 
     * @return 链表的长度
     */
    public int getLength() {
        return this.length;
    }

    /**
     * 获取链表元素长度
     * 
     * @return 链表的长度
     */
    public int getSize() {
        return this.length;
    }

    /**
     * 设置在index位置的结点的值
     * 
     * @param index 要设置值的结点在链表中的下标位置
     * @param val   要设置的结点值
     * @return 设置是否成功
     */
    public boolean setValue(int index, T val) {
        LinkListNode<T> cur = findNode(index);
        if (cur == null)
            return false;
        cur.setValue(val);
        return true;
    }

    /**
     * 返回在链表中下标位置为index的结点值
     * 
     * @param index 要返回值的结点在链表中的下标位置
     * @return index位置的结点的值，如果没有则返回null
     */
    public T getValue(int index) {
        LinkListNode<T> cur = findNode(index);
        return cur == null ? null : cur.getValue();
    }

    /**
     * 链表切片
     * 
     * @param begin 将在链表中切片的起始位置
     * @param end   将在链表中切片的结束位置（不包含）
     * @return 如果成功则返回链表切片的头节点，失败则返回空链表
     */
    public LinkList<T> getSlice(int begin, int end) {
        begin = getRealIndex(begin);
        end = getRealIndex(end);
        if (begin >= end)
            return new LinkList<T>();
        if (begin != -1 && end != -1) {
            LinkListNode<T> cur = this.head;
            int tempSize = end - begin;
            int interval = tempSize - 1;

            while (begin != 0) {
                begin--;
                cur = cur.getNext();
            }
            LinkListNode<T> newHead = new LinkListNode<>(cur.getValue());
            LinkListNode<T> newCur = newHead;
            while (true) {
                if (interval == 0)
                    return new LinkList<T>(newHead, tempSize);
                cur = cur.getNext();
                newCur.setNext(new LinkListNode<T>(cur.getValue()));
                newCur = newCur.getNext();
                interval--;
            }

        }
        return new LinkList<T>();
    }

    /**
     * 在链表尾部添加一个带有val值的结点
     * 
     * @param val 要添加的结点的值
     * @return null
     */
    public void append(T val) {
        LinkListNode<T> newNode = new LinkListNode<T>(val);
        if (this.head == null) {
            this.head = newNode;
            length++;
            return;
        }
        LinkListNode<T> cur = this.head;
        while (cur.hasNext()) {
            cur = cur.getNext();
        }
        cur.setNext(newNode);
        this.length++;
    }

    /**
     * 在链表下标为index的位置上插入一个带有val值的结点
     * 
     * @param index 要插入的结点下标位置
     * @param val   要插入的结点的值
     * @return 插入操作成功与否
     */
    public boolean insert(int index, T val) {
        LinkListNode<T> newNode = new LinkListNode<T>(val);
        if (index == 0 || index + this.length == -1) {
            LinkListNode<T> temp = this.head;
            this.head = newNode;
            this.head.setNext(temp);
            length++;
            return true;
        } else if (index < 0) {
            index += 1;
        }
        LinkListNode<T> cur = this.findNode(index - 1);
        if (cur != null) {
            newNode.setNext(cur.getNext());
            cur.setNext(newNode);
            length++;
            return true;
        }
        return false;
    }

    /**
     * 按照指定链表下标index移除节点，并返回该节点的值
     * 
     * @param index 要移除的结点下标位置
     * @return 如果成功，返回被移除的结点的值，否则为null
     */
    public T removeByIndex(int index) {
        if ((index == 0 || index + this.length == 0) && this.length != 0) {
            LinkListNode<T> temp = this.head;
            this.head = this.head.getNext();
            this.length--;
            return temp.getValue();
        } else {
            LinkListNode<T> cur = this.findNode(index - 1);
            if (cur != null) {
                LinkListNode<T> temp = cur.getNext();
                if (removeNextNode(cur) != null) {
                    length--;
                    return temp.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 移除传入结点的下一个结点
     * 
     * @param cur 即将要被移除的结点的上一个结点
     * @return 如果成功，返回被移除的结点，否则为null
     */
    private LinkListNode<T> removeNextNode(LinkListNode<T> cur) {
        if (cur == null) {
            return null;
        }
        if (cur.hasNext()) {
            LinkListNode<T> temp = cur.getNext();
            cur.setNext(cur.getNext().getNext());
            return temp;
        }
        return null;
    }

    /**
     * 遍历打印链表的值
     * 
     * @return null
     */
    public void show() {
        // 遍历打印链表
        if (this.head == null) {
            System.out.println("<空链表>");
            return;
        }
        LinkListNode<T> cur = this.head;
        for (int i = 0; i < this.length; i++) {
            System.out.print(cur.getValue());
            if (i != this.length - 1)
                System.out.print(" -> ");
            cur = cur.getNext();
        }
        System.out.println();
    }

    /**
     * 找到位于index的结点
     * 
     * @param index 要查找的结点在链表中的下标位置（支持负数下标，即反向查找）
     * @return 如果成功，返回下标所指向的结点，否则为null
     */
    private LinkListNode<T> findNode(int index) {
        index = getRealIndex(index);
        LinkListNode<T> cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    /**
     * 将反向索引转换为正向
     * 
     * @param index 下标位置
     * @return 如果成功，返回下标所指向的结点，否则为-1
     */
    private int getRealIndex(int index) {
        if (index < 0) {
            index = this.length + index;
        }
        if (index < 0 || index >= this.length) {
            // System.err.println("寻找下标为'" + index + "'的节点时发生错误");
            return -1;
        }
        return index;
    }
}

class LinkListNode<T> {
    // 链表节点类
    private T val;
    private LinkListNode<T> next;

    LinkListNode() {
        // val: null; next = null;
        this.val = null;
        this.next = null;
    }

    LinkListNode(T val) {
        // val = input; next = null
        this.val = val;
        this.next = null;
    }

    void setValue(T val) {
        this.val = val;
    }

    void setNext(LinkListNode<T> newNode) {
        this.next = newNode;
    }

    T getValue() {
        return this.val;
    }

    LinkListNode<T> getNext() {
        return this.next;
    }

    boolean hasNext() {
        return this.next == null ? false : true;
    }
}
