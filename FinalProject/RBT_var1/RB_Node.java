package FinalProject.RBT_var1;

public class RB_Node<T extends Comparable<T>> {

    // false - красный, true - черный
    public static boolean RED = false;
    public static boolean BLACK = true;

    // элемент по умолчанию красный
    public boolean color = RED;
    public RB_Node<T> left;
    public RB_Node<T> right;
    public RB_Node<T> parent;
    public T data;

    public RB_Node(T data) {
        this.data = data;
    }

    // удалить ссылку на текущий элемент у родителя
    public void removeFromParent() {
        
        if (parent == null) {
            return;
        }

        if (parent.left == this) {
            parent.left = null;
        }
        else if (parent.right == this) {
            parent.right = null;
        }
        
        // у самого элемента удалим ссылку на родителя
        this.parent = null;
    }

    // сделать child левым потомком у parent
    public void setLeft(RB_Node<T> child) {

        // отсоединяем текущего левого от родителя
        if (left != null)
            left.parent = null;

        if (child != null) {
            child.removeFromParent();
            child.parent = this;
        }

        this.left = child;
    }

    public void setRight(RB_Node<T> child) {
        if (right != null) {
            right.parent = null;
        }

        if (child != null) {
            child.removeFromParent();
            child.parent = this;
        }

        this.right = child;
    }

    public static boolean isRed(RB_Node<?> node) {
        return getColor(node) == RED;
    }

    public static boolean isBlack(RB_Node<?> node) {
        return !isRed(node);
    }

    public static void setColor(RB_Node<?> node, boolean color) {
        if (node == null)
            return;
        node.color = color;
    }

    public static boolean getColor(RB_Node<?> node) {
        // As null node is considered to be black
        return node == null ? BLACK : node.color;
    }

    public static void swapColor(RB_Node<?> node) {
        if (node == null)
            return;

        node.color = !node.color;
    }

    public RB_Node<T> getGrandparent() {
        return (parent == null) ? null : parent.parent;
    }

    public static RB_Node<?> getGrandparent(RB_Node<?> node) {
        return (node == null) ? null : node.getGrandparent();
    }

    // брат
    public RB_Node<T> getSibling() {
        if (parent != null) { // No sibling of root node
            if (this == parent.right)
                return (RB_Node<T>) parent.left;
            else
                return (RB_Node<T>) parent.right;
        }
        return null;
    }

    public static RB_Node<?> getSibling(RB_Node<?> node) {
        return (node == null) ? null : node.getSibling();
    }

    public RB_Node<T> getUncle() {
        if (parent != null) { // No uncle of root
            return parent.getSibling();
        }
        return null;
    }

    // дядя текущего узла: противоположный потомок дедушки (брат родителя)
    public static RB_Node<?> getUncle(RB_Node<?> node) {
        return (node == null) ? null : node.getUncle();
    }

}
