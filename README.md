Tasks:

- Setup Project: Create a new Android project in Android Studio with a basic activity template.
- Add Dependencies: Include necessary dependencies in your build.gradle file, especially for Room,
  ViewModel, LiveData, and RecyclerView.
- Database Setup: Define the Room database, entities (like Task and Category), and DAO interfaces.
- Repository Implementation: Implement the TaskRepository that interacts with the database.
- ViewModel Setup: Create TaskViewModel and CategoryViewModel to interact between the repository and
  UI.
- UI Development: Start designing and developing the main activity and fragments, focusing on layout
  and navigation.
- Functionality Implementation: Gradually implement the core features, starting with task
  management, followed by reminders, categories, and progress tracking.
- Testing and Debugging: Regularly test the application for bugs and performance issues.
- Polishing and Final Touches: Refine the user interface, add animations or transitions for a better
  user experience, and conduct final testing.

## User Stories

- As a user, I want to create a new task, so that I can keep track of my to-dos.
  Acceptance Criteria:
  The user can enter a task name, description, due date, and category.
  The app provides a simple and intuitive interface to add a task.

- As a user, I want to view all my tasks, so I can see what I have to do.
  Acceptance Criteria:
  The user can see a list of all tasks.
  Tasks are displayed with essential details like name, due date, and completion status.

- As a user, I want to edit an existing task, so that I can update task details as needed.
  Acceptance Criteria:
  The user can select a task and edit its details.
  Changes are saved and reflected immediately in the task list.

- As a user, I want to delete a task, so that I can remove tasks that are no longer needed.
  Acceptance Criteria:
  The user can delete a task.
  Once deleted, the task is removed from the list and the database.

- As a user, I want to set reminders for tasks, so that I don’t forget important deadlines.
  Acceptance Criteria:
  The user can set a reminder for a task.
  The app sends a notification at the specified reminder time.


## In depth task list

User Story 1: Creating a New Task

As a user, I want to create a new task, so that I can keep track of my to-dos.
Tasks

    Design the Task Input Form:
        Create a layout for the task creation form.
        Include input fields for task name, description, due date, and category.
        Design a 'Save' button to submit the task.

    Develop Task Creation Logic:
        Implement the functionality to capture user input from the form.
        Validate the input data to ensure all required fields are filled.
        Create a method in the TaskDao interface for inserting a new task into the database.

    Integrate Room Database:
        Define a Task entity in Room with fields for name, description, due date, and category.
        Update the RoomDatabase class to include the Task entity.

    Handle Data Submission:
        On clicking the 'Save' button, trigger the data submission process.
        Use the TaskViewModel to insert the task data into the database.

    User Feedback:
        Display a confirmation message or toast once a task is successfully created.
        Clear the input form after submission.

    Testing:
        Test the form for different user inputs, including edge cases.
        Ensure that the data is correctly inserted into the database.

User Story 2: Viewing All Tasks

As a user, I want to view all my tasks, so I can see what I have to do.
Tasks

    Design Task List UI:
        Create a layout for displaying tasks in a list format.
        Each list item should show the task name, due date, and completion status.

    Fetch Tasks from Database:
        Implement a method in TaskDao to retrieve all tasks.
        Use TaskViewModel to get tasks and observe changes using LiveData.

    Display Tasks in UI:
        Use a RecyclerView to display the list of tasks.
        Create a custom adapter for the RecyclerView to bind task data.

    Update UI Dynamically:
        Ensure the task list updates when new tasks are added or existing tasks are modified.

    Testing:
        Test the display of tasks with various amounts and types of tasks.
        Check the responsiveness of the UI to database changes.

User Story 3: Editing an Existing Task

As a user, I want to edit an existing task, so that I can update task details as needed.
Tasks

    UI for Task Editing:
        Create an edit option in the task list (e.g., an edit button in each list item).
        Reuse the task creation form for editing, populating it with the existing task data.

    Implement Edit Functionality:
        On selecting edit, open the task form with pre-filled data of the selected task.
        Allow the user to modify the task name, description, due date, and category.

    Update Task in Database:
        Implement a method in TaskDao for updating an existing task.
        Use the TaskViewModel to handle the update operation.

    Validation and Feedback:
        Validate the updated data before saving.
        Provide user feedback (e.g., a message or toast) upon successful update.

    Testing:
        Test the editing functionality with various scenarios and inputs.
        Ensure that updates are correctly reflected in the database and UI.

User Story 4: Deleting a Task

As a user, I want to delete a task, so that I can remove tasks that are no longer needed.
Tasks

    UI for Task Deletion:
        Add a delete option for each task in the task list (e.g., a delete icon or swipe-to-delete gesture).
        Implement confirmation dialog to prevent accidental deletions.

    Implement Delete Functionality:
        Create a method in TaskDao for deleting a task.
        In the task list, link the delete option with the delete functionality.

    Update Task List After Deletion:
        Upon deletion, update the RecyclerView to remove the deleted task.
        Ensure the database is updated accordingly.

    User Feedback:
        Provide a brief notification or toast message confirming the deletion.

    Testing:
        Test the delete functionality for different tasks.
        Ensure the task list and database accurately reflect the deletion.

User Story 5: Setting Reminders for Tasks

As a user, I want to set reminders for tasks, so that I don’t forget important deadlines.
Tasks

    Design Reminder Functionality:
        Add an option to set a reminder when creating or editing a task.
        Include a date and time picker for selecting the reminder time.

    Implement Reminder Logic:
        Store the reminder time in the Task entity.
        Use AlarmManager or a similar service to schedule reminders.

    Notification System:
        Develop a system to trigger notifications at the set reminder time.
        Ensure notifications contain relevant task information.

    Testing:
        Test setting, editing, and removing reminders.
        Verify that reminders trigger notifications at the correct time.