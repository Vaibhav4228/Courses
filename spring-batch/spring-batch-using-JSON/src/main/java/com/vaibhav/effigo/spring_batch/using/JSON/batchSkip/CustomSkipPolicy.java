package com.vaibhav.effigo.spring_batch.using.JSON.batchSkip;

import org.springframework.batch.core.step.skip.SkipPolicy;

public class CustomSkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable t, long skipCount) {
        return t instanceof RuntimeException && skipCount < 5;




    }
}
