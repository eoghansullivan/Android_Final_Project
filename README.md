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

As a user, I want to track the progress of my tasks, so that I know how much I have accomplished.
Acceptance Criteria:
The user can mark tasks as completed.
The app shows the progress of tasks (e.g., a progress bar or percentage).

As a user, I want to search for a task, so that I can quickly find specific tasks.
Acceptance Criteria:
The user can enter search terms to filter the task list.
The app displays tasks that match the search criteria.

As a user, I want to sort tasks, so that I can view them in an order that suits me (e.g., by due
date, creation date, or priority).
Acceptance Criteria:
The user can choose a sorting criterion.
The task list updates to reflect the chosen sorting order.

As a user, I want the app to remember my preferences, so that I don’t have to reset them every time
I use the app.
Acceptance Criteria:
The app saves user preferences like default view or sorting order.
Upon reopening the app, the saved preferences are applied.