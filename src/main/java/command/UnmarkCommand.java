package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * A class that will call for the unmark command
 */
public class UnmarkCommand extends Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that will mark the task as unMarked.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public String excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.unmarkTask(this.index);
        fileStorage.write(tasklist);
        return ui.showUnMarkedTask(task);
    }
}
