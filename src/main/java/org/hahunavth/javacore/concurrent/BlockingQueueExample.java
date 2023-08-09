package org.hahunavth.javacore.concurrent;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Các hàm ném một ngoại lệ
 * - add()- Chèn một phần tử vào BlockingQueue ở cuối queue. Ném một ngoại lệ nếu queue đã đầy.
 * - element()- Trả về phần tử đầu của BlockingQueue. Ném một ngoại lệ nếu queue trống.
 * - remove()- Loại bỏ một phần tử khỏi BlockingQueue. Ném một ngoại lệ nếu queue trống.
 * Các hàm trả về một số giá trị
 * - offer()- Chèn phần tử được chỉ định vào BlockingQueue ở cuối queue. Trả về false nếu queue đầy.
 * - peek()- Trả về phần tử đầu của BlockingQueue. Trả về null nếu queue trống.
 * - poll()- Loại bỏ một phần tử khỏi BlockingQueue. Trả về null nếu queue trống.
 *   Các hàm offer()và poll() có thể được sử dụng với thời gian chờ. Đó là, chúng ta có thể vượt qua các đơn vị thời gian như một tham số.
 * Các hàm để chặn thao tác
 * - put()- Chèn một phần tử vào BlockingQueue. Nếu queue đầy, nó sẽ đợi cho đến khi queue có khoảng trống để chèn một phần tử.
 * - take()- Loại bỏ và trả về một phần tử từ BlockingQueue. Nếu queue trống, nó sẽ đợi cho đến khi queue có các phần tử để có thể xóa.
 */
public class BlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(() -> {
            try {
                producer.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(5000);
        new Thread(() -> {
            try {
                consumer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

class Producer {
    private final ArrayBlockingQueue<Integer> queue;

    Producer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void produce() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Produced: " + i);
            queue.put(i);
            Thread.sleep(1000);
        }
    }
}

class Consumer {
    private final ArrayBlockingQueue<Integer> queue;

    Consumer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void consume() throws InterruptedException {
        while (true) {
            Integer number = queue.take();
            System.out.println("Consumed: " + number);
            Thread.sleep(1000);
        }
    }
}