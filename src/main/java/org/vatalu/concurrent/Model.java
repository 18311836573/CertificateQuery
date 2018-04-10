package org.vatalu.concurrent;

public interface Model {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
