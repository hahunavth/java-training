package org.hahunavth.javacore.concurrent;


import java.util.concurrent.*;

public class CallableExample {
    static ExecutorService executor = Executors.newSingleThreadExecutor();
    public static void main (String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        AsyncTask asyncTask = new AsyncTask();
        FutureTask<String> futureTask = new FutureTask<>(asyncTask);
        Thread thread = new Thread(futureTask);
        thread.start();
        String s = futureTask.get(6, TimeUnit.SECONDS);
        System.out.println(s);

        Future<String> future = executor.submit(asyncTask);
        String msg = future.get();
        System.out.println(msg.toString());
    }
}

class AsyncTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "Hello World";
    }
}