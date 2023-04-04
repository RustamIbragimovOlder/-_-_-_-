package lection3;

public class LinkedList {
    private Node head;
    private Node tail;

    // функция поиска узла в связном списке
    public Node findNode(int value) {
        Node node = head;
        while (node.nextNode != null) {
            node = node.nextNode;
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }

    // функция добавления в конец связного списка
    public void addLast(int value) {
        Node node = new Node();
        node.value = value;
        tail.nextNode = node;
        node.previousNode = tail;
        tail = node;
    }

    // функция добавления элемента в связный список
    public void add(int value) {
        Node node = new Node();
        node.value = value;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.nextNode = node;
            node.previousNode = tail;
            tail = node;
        }
    }

    // функция добавления элемента в нужное место связного списка
    public void add(int value, Node node) {
        Node next = node.next;
        Node newNode = new Node();
        newNode.value = value;
        node.next = newNode;
        newNode.previous = node;
        if (next == null) {
            tail = newNode;
        } else {
            next.previous = newNode;
            newNode.next = next;
        }
    }

    // функция удаления из связного списка
    public void delete(Node node) {
        Node previous = node.previous;
        Node next = node.next;
        if (previous == null) {       // если начало списка
            next.previous = null;
            head = next;
        } else {
            if (next == null) {       // если конец списка
                previous.next = null;
                tail = previous;
            } else {                  // если середина списка
                previous.next = next;
                next.previous = previous;
            }
        }
    }
    
    // функция разворота для двунаправленного связного списка
    public void revert2() {
        Node currentNode = head;
        while (currentNode != null) {
            Node next = currentNode.next;
            Node previous = currentNode.previous;
            currentNode.next = previous;
            currentNode.previous = next;
            if (previous == null) {
                tail = currentNode;
            }
            if (next == null) {
                head = currentNode;
            }
            currentNode = next;
        }
    }

    // функция разворота для однонаправленного связного списка

    // базовая функция
    public void revert1() {
        if (head != null && head.next != null) {
            Node temp = head;
            revert(head.next, head);
            temp.next = null;
        }
    }

    // собственно функция разворота
    private void revert(Node currentNode, Node previousNode) {
        if (currentNode.next == null) {
            head = currentNode;
        } else {
            revert(currentNode.next, currentNode);
        }
        currentNode.next = previousNode;
    }

    // ФУНКЦИИИ ДЛЯ СТЕКА
    // функция добавления элемента в стек (LIFO) (то есть в начало)
    public void pushLIFO(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }
    // функция извлечения элемента из стека (LIFO) (то есть из начала)
    public Integer pop() {
        Integer result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
        }
        return result;
    }

    // ФУНКЦИИИ ДЛЯ ОЧЕРЕДИ
    // функция добавления элемента в очередь (FIFO) (то есть в начало)
    public void pushFIFO(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head.previous = node;
        head = node;
    }
    // функция извлечения элемента из очереди (FIFO) (то есть из начала)
    public Integer peek() {
        Integer result = null;
        if (tail != null) {
            result = tail.value;
            tail.previous.next = null;
            tail = tail.previous;
        }
        return result;
    }

    public class Node {
        public Node previous;
        public Node next;
        private int value;
        private Node nextNode;
        private Node previousNode;
    }
}