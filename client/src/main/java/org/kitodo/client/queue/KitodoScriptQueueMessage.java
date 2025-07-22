package org.kitodo.client.queue;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

public class KitodoScriptQueueMessage {

    // from https://github.com/kitodo/kitodo-production/pull/6013
    // "ID string used with Active MQ logging. Must be present, but is meaningless"
    private final String taskId;
    // for example "action:export exportImages:true"
    private final String script;
    // "can be a single integer, or string representing an integer, or a list of either, or string with several integers separated by non-digit character(s)."
    private final String processes;

    public String getQueueName() {
        return "KitodoProduction.KitodoScript.Queue";
    }

    public KitodoScriptQueueMessage(String taskId, String script, String processes) {
        this.taskId = taskId;
        this.script = script;
        this.processes = processes;
    }

    public MapMessage createMessage(Session session ) throws JMSException {
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("id", taskId);
        mapMessage.setString("script", script);
        mapMessage.setString("processes", processes);
        return mapMessage;
    }

}
