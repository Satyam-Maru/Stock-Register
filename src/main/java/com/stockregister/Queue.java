package com.stockregister;

public class Queue {
    private Items[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue() {
        this.capacity = 10;  // Default capacity
        arr = new Items[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public Queue(int capacity) {
        this.capacity = capacity;
        arr = new Items[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(Items obj) {
        if (isFull()) {
            grow();  // Grow the array if the queue is full
        }

        rear = (rear + 1) % capacity;  // circular queue working
        arr[rear] = obj;
        size++;
    }

    public Items dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }

        Items obj = arr[front];
        front = (front + 1) % capacity;  // circular queue working
        size--;
        return obj;
    }

    public Items peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    // grow the queue by doubling its capacity
    private void grow() {
        int newCapacity = capacity * 2;
        Items[] newArr = new Items[newCapacity];

        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(front + i) % capacity];
        }

        arr = newArr;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }

    public void printQueue() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}