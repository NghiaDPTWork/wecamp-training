import "./todoItem.css";

interface Todo {
  id: number;
  title: string;
  completed: boolean;
}

interface TodoItemProps {
  todo: Todo;
  onDelete: (id: number) => void;
}

export function TodoItem({ todo, onDelete }: TodoItemProps) {
  return (
    <div className={`todo-item ${todo.completed ? "completed" : ""}`}>
      <div className="todo-content">
        <h3 className="todo-title">{todo.title}</h3>
        <span className="todo-status">
          {todo.completed ? "Completed" : "Pending"}
        </span>
      </div>
      <div className="todo-actions">
        <button
          onClick={() => onDelete(todo.id)}
          className="todo-delete-btn"
        >
          Delete
        </button>
      </div>
    </div>
  );
}
