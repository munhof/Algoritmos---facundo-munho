package aed;

public class MaxHeap {

    private Router[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int length) {
        this.capacity = length;
        this.size = 0;
        this.heap = new Router[length];
    }

    public void insert(Router router, int i) {
        if (size == capacity) {
            // El heap está lleno, puedes manejarlo de alguna manera (por ejemplo, redimensionar el array).
            return;
        }

        // Agregar el nuevo router al final del array.
        heap[size] = router;
        size++;

        // Restaurar la propiedad de MaxHeap.
        heapifyUp();
    }

    public Router extractMax() {
        if (size == 0) {
            return null; // El heap está vacío.
        }

        // Extraer el elemento máximo (raíz del MaxHeap).
        Router maxRouter = heap[0];

        // Colocar el último elemento en la raíz y restaurar la propiedad de MaxHeap.
        heap[0] = heap[size - 1];
        size--;

        heapifyDown();

        return maxRouter;
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && getParent(index).compareTo(heap[index]) < 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int largerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) > 0) {
                largerChildIndex = getRightChildIndex(index);
            }

            if (heap[index].compareTo(heap[largerChildIndex]) > 0) {
                break;
            } else {
                swap(index, largerChildIndex);
            }

            index = largerChildIndex;
        }
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private Router getParent(int index) {
        return heap[getParentIndex(index)];
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private Router getLeftChild(int index) {
        return heap[getLeftChildIndex(index)];
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private Router getRightChild(int index) {
        return heap[getRightChildIndex(index)];
    }

    private void swap(int index1, int index2) {
        Router temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}

