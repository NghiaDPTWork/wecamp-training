import { useState, useEffect } from "react";
import { TodoItem } from "./components/TodoItem";
import { TodoForm } from "./components/TodoForm";

interface Todo {
  id: number;
  title: string;
  completed: boolean;
}

function App() {
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/todos?_limit=20")
      .then((response) => response.json())
      .then((data) => setTodos(data));
  }, []);

  const handleAddTodo = (title: string) => {
    const newTodo: Todo = {
      id: Date.now(),
      title,
      completed: false,
    };
    setTodos([newTodo, ...todos]);
  };

  const handleDeleteTodo = (id: number) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const handleUpdateTodo = (id: number, updatedFields: Partial<Todo>) => {
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, ...updatedFields } : todo
      )
    );
  };

  return (
    <div className="container">
      <div className="header">
        <h1>Todo Application</h1>
      </div>
      <TodoForm onAdd={handleAddTodo} />
      <div className="todo-grid">
        {todos.map((todo) => (
          <TodoItem
            key={todo.id}
            todo={todo}
            onDelete={handleDeleteTodo}
            onUpdate={handleUpdateTodo}
          />
        ))}
      </div>
    </div>
  );
}

export default App;
