package com.xiaobai.polestar.scheduleconsole.models;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-17
 */
public class ScheduleVO {
    String name;
    String cron;
    String command;

    public ScheduleVO(String name, String cron, String command) {
        this.name = name;
        this.cron = cron;
        this.command = command;
    }

    public ScheduleVO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
