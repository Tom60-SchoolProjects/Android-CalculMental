/**
 * Tom OLIVIER - 2022 : https://github.com/Tom60chat/
 * Under the Unlicense license.
 */

package fr.katycorp.utils.task;

public class TaskFactory  {
    public static Thread startNew(Runnable action) {
        return new Thread(action);
    }
}
