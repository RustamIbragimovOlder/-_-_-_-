package FinalProject.RBT_var1;

public class RB_Tree<T extends Comparable<T>> {
    protected RB_Node<T> root;
    protected int size = 0;

    public void insert(T item) {
        if (this.root == null) {
            this.root = new RB_Node<T>(item);
        } else {
            this.insert(this.root, item);
        }
        // корень дерева всегда черный
        this.root.color = RB_Node.BLACK;
        ++this.size;
    }

    private void insert(RB_Node<T> node, T item) {
        // если элемент уже есть, то пропускаем
        if (node.data.compareTo(item) == 0) {
            return;
        }
        // если вставляемый элемент меньше текущего - идем налево
        if (node.data.compareTo(item) > 0) {
            // доходим до листов дерева
            if (node.left != null) {
                this.insert(node.left, item);
            } else {
                RB_Node<T> inserted = new RB_Node<T>(item);
                node.setLeft(inserted);
                this.balanceAfterInsert(inserted);
            }
        } else if (node.right != null) {
            this.insert(node.right, item);
        } else {
            // создаем новый элемент
            RB_Node<T> inserted = new RB_Node<T>(item);
            node.setRight(inserted);
            // после вставки балансируем дерево, чтобы выполнились условия RBTree
            this.balanceAfterInsert(inserted);
        }
    }

    private void balanceAfterInsert(RB_Node<T> node) {
        // если вставляем корень или родитель черный, то балансировка не нужна
        if (node == null || node == this.root || RB_Node.isBlack(node.parent)) {
            return;
        }
        // если брат дедушки красный
        if (RB_Node.isRed(node.getUncle())) {
            // меняем цвета: родителя, брата дедушки, дедушки
            RB_Node.swapColor(node.parent);
            RB_Node.swapColor(node.getUncle());
            RB_Node.swapColor(node.getGrandparent());
            // рекусивно повторяем для дедушки
            this.balanceAfterInsert(node.getGrandparent());
        } else if (node.getGrandparent().left == node.parent) {
            // если вставка в левую ветвь дедушки
            if (node.parent.right == node) {
                // если вставка в правую вевь родителя
                node = node.parent;
                // поворачиваем налево
                this.rotateLeft(node);
            }
            RB_Node.setColor(node.parent, RB_Node.BLACK);
            RB_Node.setColor(node.getGrandparent(), RB_Node.RED);
            this.rotateRight(node.getGrandparent());
        } else if (node.getGrandparent().right == node.parent) {
            // this.hasRightParent(node)
            // this.isLeftChild(node)
            if (node.parent.left == node) {
                node = node.parent;
                this.rotateRight(node);
            }
            RB_Node.setColor(node.parent, RB_Node.BLACK);
            RB_Node.setColor(node.getGrandparent(), RB_Node.RED);
            this.rotateLeft(node.getGrandparent());
        }
    }

    private void rotateRight(RB_Node<T> node) {
        if (node.left == null) {
            return;
        }
        RB_Node<T> leftTree = node.left;
        node.setLeft(leftTree.right);
        if (node.parent == null) {
            this.root = leftTree;
        } else if (node.parent.left == node) {
            node.parent.setLeft(leftTree);
        } else {
            node.parent.setRight(leftTree);
        }
        leftTree.setRight(node);
    }

    private void rotateLeft(RB_Node<T> node) {
        // если нет правой ноды, то выходим
        if (node.right == null) {
            return;
        }
        // копируем правую часть
        RB_Node<T> rightTree = node.right;
        node.setRight(rightTree.left);
        if (node.parent == null) {
            this.root = rightTree;
        } else if (node.parent.left == node) {
            node.parent.setLeft(rightTree);
        } else {
            node.parent.setRight(rightTree);
        }
        rightTree.setLeft(node);
    }

    public void inOrder() {
        this.inOrder(this.root);
    }

    private void inOrder(RB_Node<T> node) {
        if (node != null) {
            this.inOrder(node.left);
            for (int i = 0; i < getDepth(node); i++) {
                System.out.print(".");
            }
            System.out.println(" " + node.data + node.color);
            this.inOrder(node.right);
        }
    }

    public RB_Node<T> find(T data) {
        return this.find(this.root, data);
    }

    private RB_Node<T> find(RB_Node<T> root, T data) {
        if (root == null) {
            return null;
        }
        if (root.data.compareTo(data) > 0) {
            return this.find(root.left, data);
        }
        if (root.data.compareTo(data) < 0) {
            return this.find(root.right, data);
        }
        return root;
    }

    public int getDepth() {
        return this.getDepth(this.root);
    }

    private int getDepth(RB_Node<T> node) {
        if (node != null) {
            int right_depth;
            int left_depth = this.getDepth(node.left);
            return left_depth > (right_depth = this.getDepth(node.right)) ? left_depth + 1 : right_depth + 1;
        }
        return 0;
    }

}