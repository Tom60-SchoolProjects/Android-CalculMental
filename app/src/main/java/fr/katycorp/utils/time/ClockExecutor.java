/**
 * Tom OLIVIER - 2022 : https://github.com/Tom60chat/
 * Under the Unlicense license.
 */

package fr.katycorp.utils.time;

import fr.katycorp.utils.task.TaskFactory;

public class ClockExecutor {
    Thread clock;
    long startTimestamp;

    public ClockExecutor(TimerCallback action) {
        this(action, 1000);
    }

    public ClockExecutor(TimerCallback action, long sleepMillis) {
        reset();
        clock = TaskFactory.startNew(() -> {
            while (true) {
                action.onTick(getCurrentMillis());
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) { }
            }
        });
        clock.start();
    }

    public void reset() {
        startTimestamp = System.currentTimeMillis();
    }

    public long getCurrentMillis() {
        return System.currentTimeMillis() - startTimestamp;
    }
}

