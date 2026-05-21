import { useState, useEffect } from "react";

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
    <div>
      <h1>Todo Application</h1>
      <div>
        {todos.map((todo) => (
          <div key={todo.id}>
            <span>{todo.title}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
