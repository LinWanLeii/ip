package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;


/**
 * A class that is part of the command, for this particularly it will clear all the past tasks.
 */
public class ClearCommand extends Command {

    /**
     * A method that will excute the clearing of all past cases.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public String excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        fileStorage.write(tasklist.clear());
        return ui.showClearTask();
    }
}
