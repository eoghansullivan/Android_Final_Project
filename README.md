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

- As a user, I want to categorize tasks, so that I can organize my tasks more effectively.
  Acceptance Criteria:
  The user can assign categories to each task.
  The user can view tasks filtered by categories.

- As a user, I want to set reminders for tasks, so that I don’t forget important deadlines.
  Acceptance Criteria:
  The user can set a reminder for a task.
  The app sends a notification at the specified reminder time.

- As a user, I want to track the progress of my tasks, so that I know how much I have accomplished.
  Acceptance Criteria:
  The user can mark tasks as completed.
  The app shows the progress of tasks (e.g., a progress bar or percentage).

- As a user, I want to search for a task, so that I can quickly find specific tasks.
  Acceptance Criteria:
  The user can enter search terms to filter the task list.
  The app displays tasks that match the search criteria.

- As a user, I want to sort tasks, so that I can view them in an order that suits me (e.g., by due
  date, creation date, or priority).
  Acceptance Criteria:
  The user can choose a sorting criterion.
  The task list updates to reflect the chosen sorting order.

- As a user, I want the app to remember my preferences, so that I don’t have to reset them every
  time
  I use the app.
  Acceptance Criteria:
  The app saves user preferences like default view or sorting order.
  Upon reopening the app, the saved preferences are applied.

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

User Story 5: Categorizing Tasks

As a user, I want to categorize tasks, so that I can organize my tasks more effectively.
Tasks

    Design Category Functionality:
        Add an option to create and manage categories.
        Include functionality to assign a category when creating or editing a task.

    Database Integration for Categories:
        Define a Category entity and create a CategoryDao in the Room database.
        Update the Task entity to include a category relationship.

    UI for Category Assignment:
        In the task creation/editing form, include a dropdown or selection list for categories.
        Display the assigned category in the task list view.

    Implement Category Filtering:
        Allow users to filter tasks based on categories.
        Update the task list view to reflect the selected category filter.

    Testing:
        Test creating, editing, and deleting categories.
        Ensure tasks are correctly categorized and filters work as expected.

User Story 6: Setting Reminders for Tasks

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

    Integrate Reminder with UI:
        Display set reminders in the task list.
        Allow users to edit or remove reminders from existing tasks.

    Testing:
        Test setting, editing, and removing reminders.
        Verify that reminders trigger notifications at the correct time.

User Story 7: Tracking Progress of Tasks

As a user, I want to track the progress of my tasks, so that I know how much I have accomplished.
Tasks

    Design Progress Tracking Feature:
        Add a visual indicator (e.g., progress bar or checkmark) for each task in the task list to show completion status.
        Implement a functionality to mark tasks as completed/incomplete.

    Database Updates:
        Modify the Task entity to include a field for tracking completion status.

    UI Integration for Task Completion:
        Enable users to change the completion status of a task directly from the task list (e.g., via a toggle or checkbox).
        Update the task list UI to reflect the completion status visually.

    Overall Progress Overview:
        Implement a feature to display overall progress (e.g., percentage of tasks completed, tasks remaining).

    Testing:
        Test the ability to mark tasks as completed/incomplete.
        Ensure the progress overview accurately reflects the current status of tasks.

User Story 8: Searching for a Task

As a user, I want to search for a task, so that I can quickly find specific tasks.
Tasks

    Implement Search Functionality:
        Add a search bar to the task list UI.
        Implement a method in TaskDao to query tasks based on search terms.

    UI Integration for Search:
        Ensure the search bar is easily accessible in the task list view.
        Implement real-time filtering of tasks as the user types in the search bar.

    Handling No Results:
        Display a message or indication when no tasks match the search criteria.

    Testing:
        Test search functionality with various keywords.
        Validate the performance of the search feature, especially with a large number of tasks.

User Story 9: Sorting Tasks

As a user, I want to sort tasks, so that I can view them in an order that suits me (e.g., by due date, creation date, or priority).
Tasks

    Design Sorting Options:
        Add UI elements to allow users to select sorting criteria (e.g., dropdown menu or buttons).

    Implement Sorting Logic:
        Create methods in TaskDao for different sorting queries.
        Allow dynamic sorting without needing to reload the entire task list.

    UI Integration for Sorting:
        Ensure that the sorting options are intuitive and easy to access.
        Update the task list view based on the selected sorting criteria.

    Testing:
        Test each sorting option to ensure it organizes tasks correctly.
        Validate that sorting is efficient and responsive.

User Story 10: Remembering User Preferences

As a user, I want the app to remember my preferences, so that I don’t have to reset them every time I use the app.
Tasks

    Identify Preferences to Store:
        Determine which user preferences should be stored (e.g., default sorting order, view settings).

    Implement Preference Storage:
        Use Android's SharedPreferences to store user preferences.
        Ensure preferences are stored securely and efficiently.

    Apply Preferences on App Start:
        Retrieve stored preferences when the app launches.
        Apply these preferences to the relevant UI elements and functionalities.

    Settings UI:
        Create a settings page where users can modify their preferences.
        Implement logic to update preferences both in the UI and storage upon changes.

    Testing:
        Test storing, retrieving, and applying user preferences.
        Ensure preferences persist across app restarts and are applied correctly.
