package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The find command.
 */
public class FindCommand extends Command {

    /**
     * The arguments associated with the command
     **/
    public String arguments;

    /**
     * Constructs the find command.
     *
     * @param arguments The arguments associated with the command.
     */
    public FindCommand(String arguments) {
        super("find");
        this.arguments = arguments;
    }

    /**
     * Executes the main logic of the command.
     *
     * @param tasks   The user's list of tasks.
     * @param ui      The ui interacting with the user.
     * @param storage The location where the list of tasks is stored.
     * @throws DukeException If arguments are invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No matching string was entered.");
        }
        ArrayList<Task> matchedTasks = new ArrayList<>();
        ui.printToUser("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(arguments)) {
                matchedTasks.add(tasks.get(i));
            }
        }
        if (matchedTasks.isEmpty()) {
            ui.printToUser("  There are no tasks that match your query. Try again.");
        } else {
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task currTask = matchedTasks.get(i);
                ui.printToUser((i + 1) + ". " + currTask);
            }
        }
    }

    /**
     * Checks whether command terminate the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
