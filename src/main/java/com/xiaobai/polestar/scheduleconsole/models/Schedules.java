package com.xiaobai.polestar.scheduleconsole.models;

import java.util.Date;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-17
 */
public class Schedules {
    Long id;
    String name;
    String cron;
    String command;
    Boolean enabled;
    Date createTime;

    public Schedules(Long id, String name, String cron, String command, Boolean enabled, Date createTime) {
        this.id = id;
        this.name = name;
        this.cron = cron;
        this.command = command;
        this.enabled = enabled;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCron() {
        return cron;
    }

    public String getCommand() {
        return command;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
