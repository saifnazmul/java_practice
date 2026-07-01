package coro_practice.coreConcept;

class Counter{
//    static int count = 0;
    static int count = 0;

    // if the data is static,must use synchronized methods, otherwise there will be a race condition.

//    synchronized void increment(){
//        count = count + 1;
//    }

   static synchronized void increment(){
        count = count + 1;
    }

}

public class Synchronization {

    public static void main (String[] args) throws InterruptedException {
//        if there are tow separate Object, there will be problems even if we use synchronized because our data is static.

//         Counter counter = new Counter();
//         Counter counter2 = new Counter();

    Thread t  = new Thread(()->{
        for (int i=1; i<=10000; i++){
//            counter.increment();
            Counter.increment();
        }
    });

    Thread t2  = new Thread(()->{
        for (int i=1; i<=10000; i++){
//            counter2.increment();
            Counter.increment();
        }

        });

    t.start();
    t2.start();

    t.join();
    t2.join();

    System.out.println(Counter.count);



    }
}
