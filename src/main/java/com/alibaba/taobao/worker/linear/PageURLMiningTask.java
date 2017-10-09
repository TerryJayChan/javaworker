package com.alibaba.taobao.worker.linear;

import com.alibaba.taobao.worker.WorkerTask;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class PageURLMiningTask extends WorkerTask<HashSet<String>> {
    private static final int NO_PRIORITY = 0;

    private HashSet<String> minedURLs = new HashSet<>();

    private String targetURL;

    public PageURLMiningTask(String targetURL) {
        super(NO_PRIORITY);

        this.targetURL = targetURL;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public synchronized HashSet<String> get() throws InterruptedException {
        if (!isDone()) {
            wait();
        }

        return minedURLs;
    }

    @Override
    public synchronized HashSet<String> get(long timeout, TimeUnit unit) throws InterruptedException {
        if (!isDone()) {
            wait(unit.toMillis(timeout));
        }

        return minedURLs;
    }

    public HashSet<String> getMinedURLs() {
        return minedURLs;
    }

    public void addMinedURL(String url) {
        minedURLs.add(url);
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }
}
