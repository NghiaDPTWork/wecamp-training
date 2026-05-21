import { useState } from "react";
import "./todoItem.css";

interface Todo {
  id: number;
  title: string;
  completed: boolean;
}

interface TodoItemProps {
  todo: Todo;
  onDelete: (id: number) => void;
  onUpdate: (id: number, updatedFields: Partial<Todo>) => void;
}

export function TodoItem({ todo, onDelete, onUpdate }: TodoItemProps) {
  const [isEditing, setIsEditing] = useState(false);
  const [editTitle, setEditTitle] = useState(todo.title);

  const handleSave = () => {
    if (!editTitle.trim()) return;
    onUpdate(todo.id, { title: editTitle });
    setIsEditing(false);
  };

  const handleCancel = () => {
    setEditTitle(todo.title);
    setIsEditing(false);
  };

  const handleToggleCompleted = () => {
    onUpdate(todo.id, { completed: !todo.completed });
  };

  return (
    <div className={`todo-item ${todo.completed ? "completed" : ""}`}>
      {isEditing ? (
        <div className="todo-edit-mode">
          <input
            type="text"
            value={editTitle}
            onChange={(e) => setEditTitle(e.target.value)}
            className="todo-edit-input"
          />
          <div className="todo-actions">
            <button onClick={handleSave} className="todo-save-btn">
              Save
            </button>
            <button onClick={handleCancel} className="todo-cancel-btn">
              Cancel
            </button>
          </div>
        </div>
      ) : (
        <>
          <div className="todo-content">
            <div className="todo-header-row">
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={handleToggleCompleted}
                className="todo-checkbox"
              />
              <h3 className={`todo-title ${todo.completed ? "text-strike" : ""}`}>
                {todo.title}
              </h3>
            </div>
            <span onClick={handleToggleCompleted} className="todo-status cursor-pointer">
              {todo.completed ? "Completed" : "Pending"}
            </span>
          </div>
          <div className="todo-actions">
            <button onClick={() => setIsEditing(true)} className="todo-edit-btn">
              Edit
            </button>
            <button onClick={() => onDelete(todo.id)} className="todo-delete-btn">
              Delete
            </button>
          </div>
        </>
      )}
    </div>
  );
}
