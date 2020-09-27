package com.scd.erp.listener;

import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;

public class FlowableListener implements FlowableEventListener{

    @Override
    public void onEvent(FlowableEvent e) {
        if (e.getType().equals(FlowableEngineEventType.JOB_EXECUTION_SUCCESS)) {

            System.out.println("一个任务执行成功");

        } else if (e.getType().equals(FlowableEngineEventType.JOB_EXECUTION_FAILURE)) {

            System.out.println("一个任务执行失败");

        } else  {

            System.out.println(e.getType());

        }

    }

    @Override
    public boolean isFailOnException() {
        return false;
    }

    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    @Override
    public String getOnTransaction() {
        return null;
    }
}