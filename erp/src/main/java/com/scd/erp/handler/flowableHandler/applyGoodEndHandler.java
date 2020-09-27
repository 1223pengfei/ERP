package com.scd.erp.handler.flowableHandler;

import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.utils.extraUtil.ConsoleMsg;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

public class applyGoodEndHandler extends EndHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        String description = delegateTask.getDescription();
        ConsoleMsg.log(description);
        String eventName = delegateTask.getEventName();
        ConsoleMsg.log(eventName);
        ConsoleMsg.log("GoodEnd");

        Annex annex = delegateTask.getVariable("annex", Annex.class);
        handlethis(annex,3);
    }
}
