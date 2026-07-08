package coro_practice.coreConcept;

import java.util.Arrays;

class Shared{
    int[] data = new int[16];
    int size = 0;
    int put = 0;
    int take = 0;


    synchronized void put(int v) throws InterruptedException {
        while (size == data.length){
            wait();
        }


        System.out.println("put: "+ v );
        data[put] = v;
        put = (put+1)%16;
        size++;
        notifyAll();
    };

    synchronized int take(int c){
//        System.out.println("takeStart"+c);
        while (size == 0){
            try{
                System.out.println("inner: "+ Arrays.toString(this.data));
                wait();
            }catch (Exception e){}
        };

        int value = data[take];
        take = (take+1)%16;
        size--;
        notifyAll();

        System.out.println(value);
        return value ;

    }


}


public class Wait_notify {

    public static void main(String[] args) throws InterruptedException {
        Shared shared = new Shared();

        Thread t1 = new Thread(()->{
            for (int i=0;i<11;i++){
                shared.take(i);
            }

        });

        Thread t2 = new Thread(()->{
            for (int i=0;i<11;i++){
                 shared.take(i);
            }

        });



        Thread t3 = new Thread(()->{
                for (int i=0; i<22; i++){
                    try {
                        if(i>=20){
                            Thread.sleep(1000);
                        };

                        shared.put(i+1);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                };
        });

        t1.start();
        t2.start();
        t3.start();

    }




}
