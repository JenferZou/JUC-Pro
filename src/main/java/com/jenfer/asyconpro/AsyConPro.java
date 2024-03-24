package com.jenfer.asyconpro;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AsyConPro {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for(int i=0;i<4;i++){
            int id = i;
            new Thread(()->{
                    List<String> response = new LinkedList<>(Arrays.asList("hello world"+id));
                    messageQueue.put(new Message(id,response));
            }, "producer-"+id).start();
        }
        new Thread(()->{
            while (true){
                Message message = messageQueue.take();
                List<String> response = (List<String>) message.getMessage();
                log.info("take message:{}",response);
            }
        }, "consumer").start();


    }


}
@Slf4j
class MessageQueue{
    private LinkedList<Message> queue;
    private int capacity;

    public MessageQueue(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public Message take(){
        synchronized (queue){
            while (queue.isEmpty()){
                log.info("queue is empty,wait for take");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }


    public void put(Message message){
        synchronized (queue){
            while (queue.size() == capacity){
                log.info("queue is full,wait for put");
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }


}


class Message{
    private int id;

    private Object message;

    public Message(int id,Object message){
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Object getMessage(){
        return message;
    }


}