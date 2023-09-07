package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Deadline;
import ui.Ui;

/**
 * A class that is part of the command, for this in particular this will store a task that has a deadline.
 */
public class DeadlineCommand extends Command {
    private final String task;
    private final String deadDate;

    /**
     * A constructor method to call for the method.
     *
     * @param task the task that is input by the user.
     * @param deadDate the deadline of the task.
     */
    public DeadlineCommand(String task, String deadDate) {
        this.task = task;
        this.deadDate = deadDate;
    }

    /**
     * A method that will take down the deadline task that the user input.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Deadline task = new Deadline(this.task, this.deadDate);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
