import { useState, useEffect } from "react";
import { TodoItem } from "./components/TodoItem";

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

  return (
    <div className="container">
      <div className="header">
        <h1>Todo Application</h1>
      </div>
      <div className="todo-grid">
        {todos.map((todo) => (
          <TodoItem key={todo.id} todo={todo} />
        ))}
      </div>
    </div>
  );
}

export default App;
