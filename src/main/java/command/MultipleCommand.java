package command;

import java.util.stream.Stream;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Task;
import ui.Ui;



/**
 * The class that will execute the Delete multipleTask command.
 * This class extends from the Command class.
 */
public class MultipleCommand extends Command {
    private final String indexes;
    private final String commandType;
    /**
     * Constructs the class.
     *
     * @param indexes The index used to determine the class.
     */
    public MultipleCommand(String indexes, String commandType) {
        this.commandType = commandType;
        this.indexes = indexes;
    }

    /**
     * Executes the Command of deleting multiple task from the TaskList.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Result result = getResult();
        int[] reversedValues = result.reversedValues;
        int[] values = result.values;
        checkForDuplicates(reversedValues);
        checkForDuplicates(values);
        if (commandType.equals("del")) {
            for (int reversedValue : reversedValues) {
                Task task = taskList.deleteTask(reversedValue - 1);
                result.newList.add(task);
            }
        }
        if (commandType.equals("mark")) {
            for (int value : values) {
                Task task = taskList.markTask(value);
                result.newList.add(task);
            }
        }
        if (commandType.equals("unmark")) {
            for (int value : values) {
                Task task = taskList.unmarkTask(value);
                result.newList.add(task);
            }
        }
        fileStorage.write(taskList);
        return ui.showNewList(result.newList, taskList, commandType);
    }

    /**
     * Throws an error if there is a duplicate in the list.
     *
     * @param intArray The Int array that will be checked.
     * @throws DukeException If there are duplicates.
     */
    private static void checkForDuplicates(int[] intArray) throws DukeException {
        for (int i = 0; i < intArray.length; i++) {
            for (int j = i + 1; j < intArray.length; j++) {
                if (intArray[i] == intArray[j]) {
                    throw new DukeException("Oh no, do not repeat the indexes");
                }
            }
        }
    }

    /**
     * Reverses the array of indexes.
     *
     * @return A result class that contains the reversed list.
     */
    private Result getResult() {
        String[] individual = indexes.split(",");
        for (int i = 0; i < individual.length; i++) {
            String trimmed = individual[i].trim();
            individual[i] = trimmed;
        }
        TaskList newList = new TaskList();
        int[] values = Stream.of(individual)
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int size = values.length;
        int[] reversedValues = new int[size];
        for (int i = 0; i < size; i++) {
            reversedValues[i] = values[size - 1 - i];
        }
        return new Result(newList, reversedValues, values);
    }

    /**
     * Helper class to reverse the array such that the deletion could be done.
     */
    private static class Result {
        public final TaskList newList;
        public final int[] reversedValues;
        public final int[] values;

        public Result(TaskList newList, int[] reversedValues, int[] values) {
            this.newList = newList;
            this.reversedValues = reversedValues;
            this.values = values;
        }
    }
}
