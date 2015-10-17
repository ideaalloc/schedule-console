package com.xiaobai.polestar.scheduleconsole.jobs;

import com.xiaobai.polestar.scheduleconsole.constants.Constants;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-10-17
 */
public class CommandJob implements Job {
    static final Logger LOGGER = LoggerFactory.getLogger(CommandJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO: execute shell script
        final String script = (String) context.getJobDetail().getJobDataMap().get(Constants.COMMAND);
        LOGGER.info("executing shell script {}", script);
    }
}
