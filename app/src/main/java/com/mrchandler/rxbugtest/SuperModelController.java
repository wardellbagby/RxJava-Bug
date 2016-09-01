package com.mrchandler.rxbugtest;

/**
 * @author Wardell
 */

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.processors.FlowableProcessor;

/**
 * @author Wardell Bagby
 */
public class SuperModelController {

    SuperModel model;
    FlowableProcessor<List<String>> rssFeedsCache;

    public SuperModelController(SuperModel model) {
        this.model = model;
    }

    public Flowable<List<String>> getFlowable() {
        if (rssFeedsCache != null) {
            return rssFeedsCache;
        }
        return Flowable.defer(new Callable<Flowable<List<String>>>() {
            @Override
            public Flowable<List<String>> call() {
                try {
                    //Use an AsyncSubject so that the result will be reemitted to new subscribers automatically.
                    rssFeedsCache = AsyncProcessor.create();
                    rssFeedsCache.onNext(model.makeNetworkCall());
                    //Call onCompleted because AsyncSubjects only emit to subscribers after completion
                    rssFeedsCache.onComplete();
                    return rssFeedsCache;
                } catch (IOException e) {
                    rssFeedsCache = AsyncProcessor.create();
                    rssFeedsCache.onError(e);
                    return rssFeedsCache;
                }
            }
        });
    }

    public void reset() {
        rssFeedsCache = null;
    }
}
